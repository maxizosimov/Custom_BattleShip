package cs3500.pa04.view.subview.boardview;

import cs3500.pa04.view.GameView;

/**
 * The BoardView class is an abstract class that represents a generic board in the game.
 * It provides the basic structure for any type of game board.
 */
public abstract class BoardView extends GameView {
  protected int boardWidth;
  protected int boardHeight;

  /**
   * Constructs a new BoardView with the given width and height.
   *
   * @param boardWidth  The width of the board.
   * @param boardHeight The height of the board.
   */
  public BoardView(int boardWidth, int boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }

  /**
   * Displays the board.
   * The implementation of this method is up to the child classes.
   */
  @Override
  public abstract void display();
}

