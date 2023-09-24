package cs3500.pa04.model.coord;

/**
 * Coord interface, representing the coordinates on the game board.
 * Each Coord should know its x and y values and whether a ship has been hit at this location.
 */
public interface Coord {

  /**
   * Returns the x-coordinate.
   *
   * @return the x-coordinate.
   */
  int getX();

  /**
   * Returns the y-coordinate.
   *
   * @return the y-coordinate.
   */
  int getY();

  /**
   * Returns a boolean representing if a ship has been hit at this coordinate.
   *
   * @return true if a ship has been hit, false otherwise.
   */
  boolean getHitShip();

  /**
   * Updates the status of the coordinate to represent a ship has been hit here.
   */
  void updateIfHitCoordinate();
}
