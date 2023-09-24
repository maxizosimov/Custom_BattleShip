package cs3500.pa04.view.subview;

import cs3500.pa04.view.GameView;

/**
 * The MessageView class is used for displaying general messages and game end messages.
 */
public class MessageView extends GameView {

  /**
   * Displays the end game message.
   */
  @Override
  public void display() {
    printMessage("Game Over!");
  }

  /**
   * Handles and displays a win message with the reason.
   *
   * @param reason The reason for the win.
   */
  public void handleWin(String reason) {
    printMessage("Congratulations! You won!");
    printMessage(reason);
  }

  /**
   * Handles and displays a loss message with the reason.
   *
   * @param reason The reason for the loss.
   */
  public void handleLoss(String reason) {
    printMessage("You lost! Better luck next time!");
    printMessage(reason);
  }

  /**
   * Handles and displays a tie message with the reason.
   *
   * @param reason The reason for the tie.
   */
  public void handleTie(String reason) {
    printMessage("The game ended in a draw!");
    printMessage(reason);
  }
}

