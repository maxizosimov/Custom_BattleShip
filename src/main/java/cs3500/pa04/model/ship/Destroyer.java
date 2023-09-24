package cs3500.pa04.model.ship;

/**
 * A class that represents the Destroyer type of ship. Destroyer is a concrete subclass of AShip.
 */
public class Destroyer extends AbstractShip {
  public Destroyer() {
    super(ShipType.DESTROYER);
  }

  /**
   * Gets the type of the ship.
   *
   * @return the type of the ship.
   */
  @Override
  public ShipType getShipType() {
    return ShipType.DESTROYER;
  }
}
