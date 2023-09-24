package cs3500.pa04.view.subview;

import cs3500.pa04.view.GameView;

/**
 * The FleetView class is used for displaying the fleet setup phase of the game.
 */
public class FleetView extends GameView {
  private final int maxFleetSize;

  /**
   * Constructs a new FleetView with the given max fleet size.
   *
   * @param maxFleetSize The maximum size of the fleet.
   */
  public FleetView(int maxFleetSize) {
    this.maxFleetSize = maxFleetSize;
  }

  /**
   * Displays the fleet setup message and prompt.
   */
  @Override
  public void display() {
    printMessage(
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].");
    printMessage("Remember, your fleet may not exceed size " + maxFleetSize + ".");
  }

  /**
   * Handles and displays a message for invalid fleet size input.
   */
  public void handleInvalidInput() {
    printMessage("Uh Oh! You've entered invalid fleet sizes.");
    printMessage("Please enter your fleet in order [Carrier, Battleship, Destroyer, Submarine].");
    printMessage("Remember, your fleet may not exceed size " + maxFleetSize + ".");
  }
}