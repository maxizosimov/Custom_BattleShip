package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.player.AiPlayer;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.model.state.Direction;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for proxyController class
 */
public class ProxyControllerTest {
  private AiPlayer player;
  private ProxyController controller;
  private ByteArrayOutputStream serverOutputContent;

  /**
   * Setups up before each test
   *
   * @throws IOException the correct exception if there is an I/O exception
   */
  @BeforeEach
  public void setup() throws IOException {
    Socket socket = mock(Socket.class);
    player = mock(AiPlayer.class);

    InputStream serverInput = new ByteArrayInputStream("test message".getBytes());
    serverOutputContent = new ByteArrayOutputStream();
    PrintStream serverOutput = new PrintStream(serverOutputContent);

    when(socket.getInputStream()).thenReturn(serverInput);
    when(socket.getOutputStream()).thenReturn(serverOutput);

    controller = new ProxyController(socket, player);
  }

  @Test
  public void testRunDoesNotThrow() {
    // Act
    assertDoesNotThrow(() -> controller.run());
  }

  @Test
  public void testDelegateMessage() {
    // Arrange
    MessageJson messageJson = new MessageJson("join", null);

    // Act
    controller.delegateMessage(messageJson);

    // Assert that the join message was written to the server's output stream
    assert (serverOutputContent.toString().contains("join"));
  }

  @Test
  public void testDelegateMessageHandleTakeShots() {
    // Arrange
    MessageJson messageJson = new MessageJson("take-shots", null);

    // Act
    controller.delegateMessage(messageJson);

    // Assert that the take-shots message was written to the server's output stream
    assert (serverOutputContent.toString().contains("take-shots"));
  }

  @Test
  public void testHandleJoin() {
    // Arrange
    ObjectNode objectNode = JsonNodeFactory.instance.objectNode();

    // Act
    controller.handleJoin(objectNode);

    // Assert that the join message was written to the server's output stream
    assert (serverOutputContent.toString().contains("join"));
  }

  @Test
  public void testHandleTakeShots() {
    // Arrange
    Coord mockCoord = mock(Coord.class);
    when(player.takeShots()).thenReturn(Collections.singletonList(mockCoord));

    // Act
    controller.handleTakeShots();

    // Assert that the take-shots message was written to the server's output stream
    assert (serverOutputContent.toString().contains("take-shots"));
  }

  @Test
  public void testHandleSuccessfulHits() {
    JsonNode dummyNode = mock(JsonNode.class);
    JsonNode dummyShotsNode = mock(JsonNode.class);

    when(dummyNode.path("shots")).thenReturn(dummyShotsNode);
    // assume shots are empty for simplicity
    when(dummyShotsNode.iterator()).thenReturn(Collections.emptyIterator());

    controller.handleSuccessfulHits(dummyNode);

    // Assert that the successful-hits message was written to the server's output stream
    assert (serverOutputContent.toString().contains("successful-hits"));
  }

  @Test
  public void testHandleReportDamage() {
    JsonNode dummyNode = mock(JsonNode.class);
    JsonNode dummyCoordinatesNode = mock(JsonNode.class);

    when(dummyNode.path("coordinates")).thenReturn(dummyCoordinatesNode);
    when(dummyCoordinatesNode.iterator()).thenReturn(Collections.emptyIterator());

    controller.handleReportDamage(dummyNode);

    // Assert that the report-damage message was written to the server's output stream
    assert (serverOutputContent.toString().contains("report-damage"));
  }

  @Test
  public void testHandleSetup() {
    SetupJson setupJson = new SetupJson(10, 10, new HashMap<>() {{
        put(ShipType.BATTLESHIP, 1);
      }
    });
    JsonNode node = new ObjectMapper().valueToTree(setupJson);

    Ship mockShip = mock(Ship.class);
    when(mockShip.getStart()).thenReturn(new GameCoord(1, 1));
    when(mockShip.getLength()).thenReturn(3);
    controller.handleSetup(node);
    when(mockShip.getDirection()).thenReturn(Direction.HORIZONTAL);
    List<Ship> ships = Collections.singletonList(mockShip);
    when(player.setup(eq(10), eq(10), anyMap())).thenReturn(ships);
    assert (serverOutputContent.toString().contains("setup"));
  }
}
