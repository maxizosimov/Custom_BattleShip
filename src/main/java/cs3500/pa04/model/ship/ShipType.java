package cs3500.pa04.model.ship;

/**
 * An enumeration of the different types of ships available in the game.
 * Each ship type is associated with a specific size.
 */
public enum ShipType {
  /**
   * A carrier, which is the largest type of ship with a size of 6.
   */
  CARRIER(6),

  /**
   * A battleship, which is a large type of ship with a size of 5.
   */
  BATTLESHIP(5),

  /**
   * A destroyer, which is a medium size type of ship with a size of 4.
   */
  DESTROYER(4),

  /**
   * A submarine, which is the smallest type of ship with a size of 3.
   */
  SUBMARINE(3);

  /**
   * The size of the ship type.
   */
  private final int size;

  /**
   * Constructs a new ShipType with the specified size.
   *
   * @param size The size of the ship type.
   */
  ShipType(int size) {
    this.size = size;
  }

  /**
   * Returns the size of the ship type.
   *
   * @return The size of the ship type.
   */
  public int getSize() {
    return this.size;
  }
}

