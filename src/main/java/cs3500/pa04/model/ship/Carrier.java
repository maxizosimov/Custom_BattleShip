package cs3500.pa04.model.ship;

/**
 * A class that represents the Carrier type of ship. Carrier is a concrete subclass of AShip.
 */
public class Carrier extends AbstractShip {
  public Carrier() {
    super(ShipType.CARRIER);
  }

  /**
   * Gets the type of the ship.
   *
   * @return the type of the ship.
   */
  @Override
  public ShipType getShipType() {
    return ShipType.CARRIER;
  }
}
