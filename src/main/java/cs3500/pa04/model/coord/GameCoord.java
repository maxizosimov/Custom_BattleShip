package cs3500.pa04.model.coord;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GameCoord class, an implementation of the Coord interface.
 * This class is responsible for keeping track of its x and y values,
 * and whether a ship has been hit at this location.
 */
public class GameCoord implements Coord {
  private final int x;
  private final int y;
  private boolean hitShip;

  /**
   * Constructor for GameCoord, which sets the x and y values, and initializes HitShip to false.
   *
   * @param x The x-coordinate
   * @param y The y-coordinate
   */
  public GameCoord(int x, int y) {
    this.x = x;
    this.y = y;
    this.hitShip = false;
  }

  /**
   * Returns the x-coordinate.
   *
   * @return the x-coordinate.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y-coordinate.
   *
   * @return the y-coordinate.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns a boolean representing if a ship has been hit at this coordinate.
   *
   * @return true if a ship has been hit, false otherwise.
   */
  @JsonIgnore
  public boolean getHitShip() {
    return this.hitShip;
  }

  /**
   * Updates the status of the coordinate to represent a ship has been hit here.
   */
  public void updateIfHitCoordinate() {
    this.hitShip = true;
  }

  /**
   * Checks if two GameCoord objects are equal based on their x and y values.
   *
   * @param coord1 The object to compare this GameCoord to.
   * @return true if the given object is a GameCoord with the same x and y values, false otherwise.
   */
  @Override
  public boolean equals(Object coord1) {
    if (coord1 instanceof GameCoord coord2) {
      return (coord2.getX() == x && coord2.getY() == y);
    } else {
      return false;
    }
  }
}
