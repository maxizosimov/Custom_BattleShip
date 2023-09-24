package cs3500.pa04.model.ship;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.state.Direction;
import java.util.List;

/**
 * An interface to represent the general actions and characteristics of a ship.
 * Defines methods related to ship size, type, placement, etc.
 */
public interface Ship {

  /**
   * Gets the size of the ship. The size is determined by the ship type.
   *
   * @return the size of the ship.
   */
  default int getSize() {
    return getShipType().getSize();
  }

  /**
   * Gets the type of the ship.
   *
   * @return the type of the ship.
   */
  ShipType getShipType();

  /**
   * Generates a placement for the ship on the game board.
   *
   * @param height           the height of the game board.
   * @param width            the width of the game board.
   * @param takenCoordinates a list of coordinates that are already occupied by other ships.
   */
  void generatePlacement(int height, int width, List<Coord> takenCoordinates);

  /**
   * Gets the placement of the ship on the game board.
   *
   * @return a list of coordinates representing the ship's location on the game board.
   */
  List<Coord> getPlacement();

  /**
   * Clears the current placement of the ship.
   * This is typically used before generating a new placement.
   */
  void clearPlacement();

  Coord getStart();


  int getLength();


  Direction getDirection();

}
