package cs3500.pa04.model;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.player.AbstractPlayer;
import cs3500.pa04.model.player.AiPlayer;
import cs3500.pa04.model.player.HumanPlayer;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.model.state.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Model class represents the game model of a Battleship game.
 * It keeps track of the game board, players, their shots, and the game progress.
 * It also updates the game state, validates moves, determines winners, and sets up game rounds.
 */
public class Model {
  private final int boardWidth;
  private final int boardHeight;
  private List<Coord> humanFiredShots;
  private List<Coord> aiFiredShots;
  private final Map<ShipType, Integer> specifications;

  /**
   * Constructs a new Model with specified board dimensions and ship specifications.
   *
   * @param boardWidth     The width of the game board.
   * @param boardHeight    The height of the game board.
   * @param specifications The ship types and their corresponding count for the game.
   */
  public Model(int boardWidth, int boardHeight, Map<ShipType, Integer> specifications) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    this.humanFiredShots = new ArrayList<>();
    this.aiFiredShots = new ArrayList<>();
    this.specifications = specifications;
  }

  /**
   * Handles the shot taking for both human and AI players.
   *
   * @param humanPlayer The human player.
   * @param aiPlayer    The AI player.
   */
  public void takeShots(HumanPlayer humanPlayer, AiPlayer aiPlayer) {
    humanFiredShots = humanPlayer.takeShots();
    aiFiredShots = getAutomatedFiredShots(aiPlayer);
  }


  /**
   * Updates the players based on the damage reported after shots are fired.
   *
   * @param humanPlayer The human player.
   * @param aiPlayer    The AI player.
   */
  public void updatePlayers(HumanPlayer humanPlayer, AiPlayer aiPlayer) {
    List<Coord> damagedAutomated = aiPlayer.reportDamage(humanFiredShots);
    List<Coord> damagedManual = humanPlayer.reportDamage(aiFiredShots);

    humanPlayer.updateRemainingShips(damagedManual);
    aiPlayer.updateRemainingShips(damagedAutomated);
    humanPlayer.successfulHits(damagedAutomated);
    aiPlayer.successfulHits(damagedManual);
  }

  //public List<Coord> getAIUpdate() {
  //return AIFiredShots;
  //}

  /**
   * Checks if the game has ended, which is when any player has no remaining ships.
   *
   * @param humanPlayer The human player.
   * @param aiPlayer    The AI player.
   * @return True if game has ended, false otherwise.
   */
  public boolean endGame(HumanPlayer humanPlayer, AiPlayer aiPlayer) {
    return humanPlayer.getNumRemainingShips() == 0 || aiPlayer.getNumRemainingShips() == 0;
  }

  /**
   * Sets up a new round by updating the allowed shots for each player.
   *
   * @param humanPlayer The human player.
   * @param aiPlayer    The AI player.
   */
  public void setupRound(HumanPlayer humanPlayer, AiPlayer aiPlayer) {
    updateAllowedShots(humanPlayer);
    updateAllowedShots(aiPlayer);
  }

  /**
   * Determines the winner of the game and triggers the end game for players.
   *
   * @param humanPlayer The human player.
   * @param aiPlayer    The AI player.
   */
  public void determineWinner(HumanPlayer humanPlayer, AiPlayer aiPlayer) {
    int humanShips = humanPlayer.getNumRemainingShips();
    int aiShips = aiPlayer.getNumRemainingShips();

    if (humanShips == 0 || aiShips == 0) {
      GameResult humanResult;
      GameResult aiResult;
      String humanMessage;
      String aiMessage;

      if (humanShips == 0 && aiShips == 0) {
        humanResult = aiResult = GameResult.DRAW;
        humanMessage = aiMessage = "Both players sunk all ships last round";
      } else if (aiShips == 0) {
        humanResult = GameResult.WIN;
        aiResult = GameResult.LOSE;
        humanMessage = "You sunk all your opponent's ships!";
        aiMessage = "All of your ships have been sunk.";
      } else { // humanShips == 0
        humanResult = GameResult.LOSE;
        aiResult = GameResult.WIN;
        humanMessage = "All your ships have been sunk.";
        aiMessage = "You sunk all of your opponent's ships!";
      }

      humanPlayer.endGame(humanResult, humanMessage);
      aiPlayer.endGame(aiResult, aiMessage);
    }
  }


  /**
   * Handles the AI player's shots until valid coordinates are fired.
   *
   * @param aiPlayer The AI player.
   * @return List of coordinates that AI player has fired.
   */
  public List<Coord> getAutomatedFiredShots(AiPlayer aiPlayer) {
    boolean validAutomated = false;
    aiFiredShots = new ArrayList<>();
    while (!validAutomated) {
      aiFiredShots = aiPlayer.takeShots();
      if (validCoordinates(aiFiredShots, aiPlayer)) {
        for (Coord shot : aiFiredShots) {
          aiPlayer.addToAlreadyShot(shot);
        }
        validAutomated = true;
      }
    }
    return aiFiredShots;
  }

  private void updateAllowedShots(AbstractPlayer player) {
    int openCoordinates = (boardWidth * boardHeight) - player.getAlreadyShot().size();
    int allowedShots = Math.min(player.getNumRemainingShips(), openCoordinates);
    player.setAllowedShots(allowedShots);
  }

  /**
   * Checks if the fired shots are valid by ensuring
   * they are within the board and not already fired.
   *
   * @param firedShots The list of shots fired.
   * @param player     The player who fired the shots.
   * @return True if all fired shots are valid, false otherwise.
   */
  public boolean validCoordinates(List<Coord> firedShots, AbstractPlayer player) {
    boolean validShot = true;
    for (Coord shot : firedShots) {
      if (shot.getX() <= 0 || shot.getY() <= 0 || shot.getX() > boardWidth
          ||
          shot.getY() > boardHeight || player.getAlreadyShot().contains(shot)) {
        validShot = false;
      }
    }
    return validShot;
  }

  /**
   * Sets up the player with the game board dimensions and ship specifications.
   *
   * @param player The player to setup.
   */
  public void setupPlayers(AbstractPlayer player) {
    player.setup(boardHeight, boardWidth, specifications);
  }
}