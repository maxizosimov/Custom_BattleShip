package cs3500.pa04.view.subview;

import cs3500.pa04.view.GameView;

/**
 * The DimensionSetupView class is used for
 * displaying the initial setup of the game board dimensions.
 */
public class DimensionSetupView extends GameView {

  /**
   * Displays the initial message and prompt for game setup.
   */
  @Override
  public void display() {
    printMessage("Hello! Welcome to BattleSalvo");
    printMessage("Please enter a valid height and width: ");
  }

  /**
   * Handles and displays a message for invalid dimension input.
   */
  public void handleInvalidInput() {
    printMessage("Uh Oh! You've entered invalid dimensions.");
    printMessage(
        "Please remember that height and width must be in the range (6, 15), inclusive. "
           + "Try again!");
  }
}

