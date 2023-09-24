package cs3500.pa04.model.player;

import cs3500.pa04.controller.GameController;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.state.GameResult;
import java.util.List;

/**
 * HumanPlayer class, a subclass of APlayer.
 * This class represents a human player in the game,
 * capable of manually taking shots on the game board.
 */
public class HumanPlayer extends AbstractPlayer {

  private final GameController controller;

  /**
   * Constructor for HumanPlayer, takes a GameController as an argument.
   *
   * @param controller The GameController which the human player uses to interact with the game.
   */
  public HumanPlayer(GameController controller) {
    this.controller = controller;
  }

  /**
   * Returns the name of this player.
   *
   * @return the string "Manual Player".
   */
  @Override
  public String name() {
    return "Manual Player";
  }

  /**
   * Allows the human player to manually take shots on the game board.
   *
   * @return a list of Coords representing the human player's shots.
   */
  @Override
  public List<Coord> takeShots() {
    return controller.getManualFiredShots();
  }

  /**
   * Method for handling end of the game for the human player.
   * It delegates this responsibility to the GameController.
   *
   * @param result The result of the game.
   * @param reason The reason why the game ended.
   */
  @Override
  public void endGame(GameResult result, String reason) {
    controller.handleEndGame(result, reason);
  }
}
