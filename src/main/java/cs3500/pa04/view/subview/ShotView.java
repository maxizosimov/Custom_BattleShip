package cs3500.pa04.view.subview;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.view.GameView;
import java.util.List;


/**
 * The ShotView class is used for displaying the shot phase of the game.
 */
public class ShotView extends GameView {
  private final int allowedShots;

  public ShotView(int allowedShots) {
    this.allowedShots = allowedShots;
  }

  /**
   * Displays the shots input prompt.
   */
  @Override
  public void display() {
    printMessage("Please Enter " + allowedShots + " Shots: ");
  }

  /**
   * Handles and displays a message for invalid shots input.
   */
  public void handleInvalidInput() {
    printMessage("Invalid Shots. Please Enter " + allowedShots + " Shots.");
  }

  /**
   * Displays the successful hits.
   *
   * @param successfulHits The list of successful hit coordinates.
   * @param playerName     The name of the player.
   */
  public void displaySuccessfulHits(List<Coord> successfulHits, String playerName) {
    printMessage(playerName + " Successful Hits: ");
    for (Coord c : successfulHits) {
      printMessage("[" + c.getX() + " " + c.getY() + "]");
    }
  }
}

