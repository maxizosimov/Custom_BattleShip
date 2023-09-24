package cs3500.pa04.view.subview.boardview;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import java.util.List;

/**
 * The OpponentBoardView class represents the game board of the opponent player.
 */
public class OpponentBoardView extends BoardView {
  private final List<Coord> opponentShipCoordinates;
  private final List<Coord> alreadyShot;

  /**
   * Constructs a new OpponentBoardView with given width, height, and coordinates.
   *
   * @param boardWidth              The width of the board.
   * @param boardHeight             The height of the board.
   * @param opponentShipCoordinates The coordinates of opponent's ships.
   * @param alreadyShot             The coordinates that have been shot.
   */
  public OpponentBoardView(int boardWidth, int boardHeight, List<Coord> opponentShipCoordinates,
                           List<Coord> alreadyShot) {
    super(boardWidth, boardHeight);
    this.opponentShipCoordinates = opponentShipCoordinates;
    this.alreadyShot = alreadyShot;
  }

  /**
   * Displays the opponent's board.
   */
  @Override
  public void display() {
    printOpponentBoard(opponentShipCoordinates, alreadyShot);
  }

  /**
   * Prints the opponent's board with the current game state.
   *
   * @param opponentShipCoordinates The coordinates of opponent's ships.
   * @param alreadyShot             The coordinates that have been shot.
   */
  public void printOpponentBoard(List<Coord> opponentShipCoordinates, List<Coord> alreadyShot) {
    System.out.println("Opponent Board: ");
    for (int i = 1; i <= boardWidth; i++) {
      for (int j = 1; j <= boardHeight; j++) {
        Coord currentCoord = new GameCoord(j, i);
        if (opponentShipCoordinates.contains(currentCoord) && alreadyShot.contains(currentCoord)) {
          System.out.print(" H ");
        } else if (alreadyShot.contains(currentCoord)) {
          System.out.print(" M ");
        } else {
          System.out.print(" 0 ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
