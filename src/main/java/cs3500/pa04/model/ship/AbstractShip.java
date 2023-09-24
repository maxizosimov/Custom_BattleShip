package cs3500.pa04.model.ship;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.state.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AShip is an abstract class that implements the Ship interface
 */
public abstract class AbstractShip implements Ship {
  private final ShipType shipType;
  Random random = new Random();
  List<Coord> placement = new ArrayList<>();
  Direction dir;

  public AbstractShip(ShipType shipType) {
    this.shipType = shipType;
  }

  @Override
  public ShipType getShipType() {
    return this.shipType;
  }

  /**
   * Clears the placement of this ship
   */
  public void clearPlacement() {
    placement.clear();
  }

  /**
   * Generates a random coordinate to be used as a starting point for this
   * ship's placement
   *
   * @param height the height of the board
   * @param width  the width of the board
   * @return the generated Coordinate
   */
  private Coord getRandomCoordinate(int height, int width) {
    int x = random.nextInt(width);
    int y = random.nextInt(height);
    return new GameCoord(x, y);
  }

  /**
   * Generates a random boolean to represent if this ship will be placed
   * vertically or horizontally on the board
   *
   * @return a random boolean, true if the ship will be vertical, false if horizontal
   */
  private boolean getRandomOrientation() {
    return random.nextBoolean();
  }

  /**
   * Initiates the coordinate placements for a ship
   *
   * @param height           the height of the board
   * @param width            the width of the board
   * @param takenCoordinates a list of coordinates already occupied by other ships
   */
  public void generatePlacement(int height, int width, List<Coord> takenCoordinates) {
    boolean validPlacement;
    do {
      validPlacement = true;
      placement.clear();
      Coord startCoord = getRandomCoordinate(height, width);
      int startCordX = startCoord.getX();
      int startCordY = startCoord.getY();
      placement.add(startCoord);
      boolean isVertical = getRandomOrientation();
      this.dir = isVertical ? Direction.VERTICAL : Direction.HORIZONTAL;

      if (takenCoordinates.contains(startCoord)) {
        validPlacement = false;
        continue;
      }
      for (int i = 1; i < getSize(); i++) {
        Coord nextCoord;
        if (isVertical) {
          nextCoord = new GameCoord(startCordX, startCordY + i);
          if (nextCoord.getY() < 0 || nextCoord.getY() >= height) {
            validPlacement = false;
            break;
          }
        } else {
          nextCoord = new GameCoord(startCordX + i, startCordY);
          if (nextCoord.getX() < 0 || nextCoord.getX() >= width) {
            validPlacement = false;
            break;
          }
        }

        if (takenCoordinates.contains(nextCoord)) {
          validPlacement = false;
          break;
        }

        placement.add(nextCoord);
      }
    } while (!validPlacement);
  }

  /**
   * Get the placement of this ship
   *
   * @return the list of coordinates where this ship is set
   */
  public List<Coord> getPlacement() {
    return this.placement;
  }

  /**
   * Gets the size of a ship
   *
   * @return the size of a ship
   */
  public int getSize() {
    return getShipType().getSize();
  }

  /**
   * Gets the starting coord of a ship
   *
   * @return the first coord of a ship
   */
  public Coord getStart() {
    int startX = placement.get(0).getX();
    int startY = placement.get(0).getY();
    return new GameCoord(startX, startY);
  }

  /**
   * The length of a ship
   *
   * @return the length of this ship
   */
  public int getLength() {
    int counter = 0;
    int i = 0;
    while (i < shipType.getSize()) {
      counter++;
      i++;
    }
    return counter;
  }

  public Direction getDirection() {
    return this.dir;
  }
}

