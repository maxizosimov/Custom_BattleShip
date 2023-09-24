package cs3500.pa04.model.ship;

/**
 * A class that represents the Battleship type of ship. Battleship is a concrete subclass of AShip.
 */
public class Battleship extends AbstractShip {
  public Battleship() {
    super(ShipType.BATTLESHIP);
  }

  /**
   * Gets the type of the ship.
   *
   * @return the type of the ship.
   */
  @Override
  public ShipType getShipType() {
    return ShipType.BATTLESHIP;
  }
}
