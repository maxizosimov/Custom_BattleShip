package cs3500.pa04.controller;

import cs3500.pa04.model.Model;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.player.AiPlayer;
import cs3500.pa04.model.player.HumanPlayer;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.model.state.GameResult;
import cs3500.pa04.view.subview.DimensionSetupView;
import cs3500.pa04.view.subview.FleetView;
import cs3500.pa04.view.subview.MessageView;
import cs3500.pa04.view.subview.ShotView;
import cs3500.pa04.view.subview.boardview.OpponentBoardView;
import cs3500.pa04.view.subview.boardview.UserBoardView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The GameController class manages the game, coordinating interactions
 * between the model, the view, and the user. It handles game setup,
 * updates the game state, and presents the updated state to the user.
 */
public class GameController implements Controller {
  private Model battleSalvoModel = new Model(0, 0,
      new HashMap<>());
  private final HumanPlayer humanPlayer = new HumanPlayer(this);
  private final AiPlayer aiPlayer = new AiPlayer();
  private final Scanner scanner = new Scanner(System.in);
  private int boardWidth;
  private int boardHeight;
  private final Map<ShipType, Integer> specifications = new HashMap<>();
  private FleetView fleetView;

  public GameController() {

  }

  /**
   * Begins the execution of the Controller.
   */
  public void run() {
    getValidBoard();
    getValidFleet();
    battleSalvoModel.setupPlayers(humanPlayer);
    battleSalvoModel.setupPlayers(aiPlayer);
    while (!battleSalvoModel.endGame(humanPlayer, aiPlayer)) {
      battleSalvoModel.setupRound(humanPlayer, aiPlayer);
      displayCurrentState();
      battleSalvoModel.takeShots(humanPlayer, aiPlayer);
      battleSalvoModel.updatePlayers(humanPlayer, aiPlayer);
      displaySuccessfulPlayerHits();
    }
    battleSalvoModel.determineWinner(humanPlayer, aiPlayer);
  }

  /**
   * Displays the current state of the game to the user.
   */
  private void displayCurrentState() {
    ShotView shotView = new ShotView(humanPlayer.getAllowedShots());
    OpponentBoardView opponentBoardView = new OpponentBoardView(boardWidth, boardHeight,
        aiPlayer.getShipOccupiedCoordinates(),
        humanPlayer.getAlreadyShot());

    UserBoardView userBoardView = new UserBoardView(boardWidth, boardHeight,
        humanPlayer.getShipOccupiedCoordinates(),
        aiPlayer.getAlreadyShot());

    opponentBoardView.display();
    userBoardView.display();
    shotView.display();
  }

  /**
   * Shows successful shots made by both players.
   */
  private void displaySuccessfulPlayerHits() {
    ShotView shotViewHuman = new ShotView(humanPlayer.getAllowedShots());
    ShotView shotViewAi = new ShotView(aiPlayer.getAllowedShots());

    shotViewHuman.displaySuccessfulHits(humanPlayer.getSuccessfulHits(), humanPlayer.name());
    shotViewAi.displaySuccessfulHits(aiPlayer.getSuccessfulHits(), aiPlayer.name());
  }

  /**
   * Parses the coordinates of manually entered shots from the user.
   *
   * @return a list of shot coordinates.
   */
  private List<Coord> parseManualShots() {
    List<Coord> firedShots = new ArrayList<>();
    int count = 0;
    while (count < humanPlayer.getAllowedShots()) {
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      Coord firedShot = new GameCoord(x, y);
      firedShots.add(firedShot);
      count++;
    }
    return firedShots;
  }

  /**
   * Gets the shots manually entered by the user, validating and processing the input.
   *
   * @return a list of shot coordinates.
   */
  public List<Coord> getManualFiredShots() {
    boolean validManual = false;
    List<Coord> manualFiredShots = new ArrayList<>();
    ShotView shotView = new ShotView(humanPlayer.getAllowedShots());

    while (!validManual) {
      manualFiredShots = parseManualShots();

      if (!battleSalvoModel.validCoordinates(manualFiredShots, humanPlayer)) {
        shotView.handleInvalidInput();
      } else {
        for (Coord shot : manualFiredShots) {
          humanPlayer.addToAlreadyShot(shot);
        }
        validManual = true;
      }
    }
    return manualFiredShots;
  }

  /**
   * Gets the dimensions for the game board from the user, validating the input.
   */
  private void getValidBoard() {
    DimensionSetupView dimensionSetupView = new DimensionSetupView();
    boolean validDimensions = false;
    dimensionSetupView.display();
    while (!validDimensions) {
      boardWidth = scanner.nextInt();
      boardHeight = scanner.nextInt();
      if (boardWidth < 16 && boardWidth > 5 && boardHeight < 16 && boardHeight > 5) {
        fleetView = new FleetView(Math.min(boardWidth, boardHeight));
        validDimensions = true;
      } else {
        dimensionSetupView.handleInvalidInput();
      }
    }
  }


  /**
   * Gets the specifications for the user's fleet, validating the input.
   */
  private void getValidFleet() {
    boolean validFleet = false;
    fleetView.display();
    while (!validFleet) {
      int numCarrier = scanner.nextInt();
      int numBattleship = scanner.nextInt();
      int numDestroyer = scanner.nextInt();
      int numSubmarine = scanner.nextInt();
      if (numSubmarine + numCarrier + numDestroyer + numBattleship
          <=
          Math.min(boardWidth, boardHeight)) {
        specifications.put(ShipType.CARRIER, numCarrier);
        specifications.put(ShipType.BATTLESHIP, numBattleship);
        specifications.put(ShipType.DESTROYER, numDestroyer);
        specifications.put(ShipType.SUBMARINE, numSubmarine);
        battleSalvoModel = new Model(boardWidth, boardHeight, specifications);
        validFleet = true;
      } else {
        fleetView.handleInvalidInput();
      }
    }
  }

  /**
   * Handles the end of the game, displaying the appropriate message based on the game result.
   *
   * @param result the result of the game.
   * @param reason the reason for the result.
   */
  public void handleEndGame(GameResult result, String reason) {
    MessageView messageView = new MessageView();
    messageView.display();
    if (result.equals(GameResult.WIN)) {
      messageView.handleWin(reason);
    } else if (result.equals(GameResult.DRAW)) {
      messageView.handleTie(reason);
    } else if (result.equals(GameResult.LOSE)) {
      messageView.handleLoss(reason);
    }
  }
}





