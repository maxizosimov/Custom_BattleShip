package cs3500.pa04.model.ship;

/**
 * A class that represents the Submarine type of ship. Submarine is a concrete subclass of AShip.
 */
public class Submarine extends AbstractShip {
  public Submarine() {
    super(ShipType.SUBMARINE);
  }

  /**
   * Gets the type of the ship.
   *
   * @return the type of the ship.
   */
  public ShipType getShipType() {
    return ShipType.SUBMARINE;
  }
}
