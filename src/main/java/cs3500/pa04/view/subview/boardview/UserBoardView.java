package cs3500.pa04.view.subview.boardview;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import java.util.List;

/**
 * The UserBoardView class represents the game board of the user player.
 */
public class UserBoardView extends BoardView {
  private final List<Coord> userShipCoordinates;
  private final List<Coord> opponentShots;

  /**
   * Constructs a new UserBoardView with given width, height, and coordinates.
   *
   * @param boardWidth          The width of the board.
   * @param boardHeight         The height of the board.
   * @param userShipCoordinates The coordinates of user's ships.
   * @param opponentShots       The coordinates that have been shot by the opponent.
   */
  public UserBoardView(int boardWidth, int boardHeight, List<Coord> userShipCoordinates,
                       List<Coord> opponentShots) {
    super(boardWidth, boardHeight);
    this.userShipCoordinates = userShipCoordinates;
    this.opponentShots = opponentShots;
  }

  /**
   * Displays the user's board.
   */
  @Override
  public void display() {
    printUserBoard(userShipCoordinates, opponentShots);
  }

  /**
   * Prints the user's board with the current game state.
   *
   * @param userShipCoordinates The coordinates of user's ships.
   * @param opponentShots       The coordinates that have been shot by the opponent.
   */
  public void printUserBoard(List<Coord> userShipCoordinates, List<Coord> opponentShots) {
    System.out.println("Your Board: ");
    for (int i = 1; i <= boardWidth; i++) {
      for (int j = 1; j <= boardHeight; j++) {
        Coord currentCoord = new GameCoord(j, i);
        if (userShipCoordinates.contains(currentCoord) && opponentShots.contains(currentCoord)) {
          System.out.print(" H ");
        } else if (opponentShots.contains(currentCoord)) {
          System.out.print(" M ");
        } else if (userShipCoordinates.contains(currentCoord)) {
          System.out.print(" S ");
        } else {
          System.out.print(" 0 ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
