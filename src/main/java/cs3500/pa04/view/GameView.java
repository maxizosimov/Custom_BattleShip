package cs3500.pa04.view;

/**
 * The GameView abstract class represents a generic view in the game.
 * This class implements the View interface.
 */
public abstract class GameView implements View {

  /**
   * Displays the view. This method should be overridden by subclasses.
   */
  @Override
  public abstract void display();

  /**
   * Prints a message to the console.
   *
   * @param message The message to be printed.
   */
  protected void printMessage(String message) {
    System.out.println(message);
  }
}
