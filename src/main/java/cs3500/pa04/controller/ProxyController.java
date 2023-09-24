package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ReportDamageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.ShipAdapter;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.json.TakeShotsJson;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.player.AiPlayer;
import cs3500.pa04.model.ship.Ship;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a proxy controller handling server communication
 */
public class ProxyController implements Controller {

  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if
   */
  public ProxyController(Socket server, AiPlayer player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  @Override
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        System.out.println(message);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.out.println("Server closing: " + e.getMessage());
    }
  }

  protected void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin(arguments);
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  protected void handleJoin(JsonNode arguments) {
    JoinJson joinUpdate = new JoinJson("pa04-bruh", "SINGLE");

    // Serialize JoinJson object to JSON and put it into a MessageJson
    JsonNode joinOut = mapper.valueToTree(joinUpdate);
    MessageJson response = new MessageJson("join", joinOut);
    JsonNode messageOut = JsonUtils.serializeRecord(response);
    out.println(messageOut);
    out.flush();
  }

  protected void handleEndGame(JsonNode arguments) {
    EndGameJson endGame = this.mapper.convertValue(arguments, EndGameJson.class);
    String reason = arguments.path("reason").toString();
    // gets reason and result of endgame
    player.endGame(endGame.getGameResult(), reason);
    MessageJson messageJson = new MessageJson("end-game", arguments);
    JsonNode messageOut = JsonUtils.serializeRecord(messageJson);
    out.println(messageOut);
  }

  protected void handleTakeShots() {
    List<Coord> myShots = player.takeShots();
    CoordJson[] coordJson = new CoordJson[myShots.size()];

    for (int i = 0; i < myShots.size(); i++) {
      Coord currentShot = myShots.get(i);
      coordJson[i] = new CoordJson(currentShot.getX(), currentShot.getY());
    }

    TakeShotsJson shotsIn = new TakeShotsJson(coordJson);

    JsonNode shotsOut = JsonUtils.serializeRecord(shotsIn);
    MessageJson messageJson = new MessageJson("take-shots", shotsOut);
    JsonNode messageOut = JsonUtils.serializeRecord(messageJson);
    out.println(messageOut);
    out.flush();
  }


  protected void handleSuccessfulHits(JsonNode arguments) {
    MessageJson messageJson = new MessageJson("successful-hits",
        JsonNodeFactory.instance.objectNode());
    JsonNode messageOut = JsonUtils.serializeRecord(messageJson);
    out.println(messageOut);
  }

  protected void handleReportDamage(JsonNode arguments) {
    List<Coord> opponentShots = new ArrayList<>();
    JsonNode coordinates = arguments.path("coordinates");

    for (JsonNode coordinateNode : coordinates) {
      int x = coordinateNode.get("x").asInt();
      int y = coordinateNode.get("y").asInt();
      GameCoord c = new GameCoord(x, y);
      opponentShots.add(c);
    }

    ReportDamageJson damageIn = new ReportDamageJson(opponentShots);
    List<Coord> hitShots = player.reportDamage(damageIn.getShots());

    ReportDamageJson damageOut = new ReportDamageJson(hitShots);

    JsonNode jsonResponse = JsonUtils.serializeRecord(damageOut);
    MessageJson messageJson = new MessageJson("report-damage", jsonResponse);
    JsonNode jsonNode = JsonUtils.serializeRecord(messageJson);
    out.println(jsonNode);
  }

  protected void handleSetup(JsonNode arguments) {
    SetupJson setupIn = mapper.convertValue(arguments, SetupJson.class);
    List<Ship> ships = player.setup(setupIn.height(), setupIn.width(), setupIn.fleetSpec());

    // convert the Ship list to a ShipJson array
    ShipJson[] shipArray = ships.stream()
        .map(ship -> new ShipAdapter(ship).toShipJson())
        .toArray(ShipJson[]::new);

    // Create PlayerSetupJson object to hold the ships
    FleetJson setupOut = new FleetJson(shipArray);

    JsonNode jsonResponse = JsonUtils.serializeRecord(setupOut);

    MessageJson messageJson = new MessageJson("setup", jsonResponse);
    JsonNode messageOut = JsonUtils.serializeRecord(messageJson);
    out.println(messageOut);
    out.flush();
  }
}