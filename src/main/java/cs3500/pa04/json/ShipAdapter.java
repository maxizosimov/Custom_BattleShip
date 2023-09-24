package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.state.Direction;

/**
 * To adapt ships for json
 */
public class ShipAdapter {

  private final Coord start;
  private final int length;
  private final Direction dir;

  /**
   * Constructor
   *
   * @param myShip represents this ship
   */
  public ShipAdapter(Ship myShip) {
    this.start = myShip.getStart();
    this.length = myShip.getLength();
    this.dir = myShip.getDirection();
  }

  /**
   * Json constructor
   *
   * @param coord a coordinate
   * @param length length of ship
   * @param dir direction of ship
   */
  @JsonCreator
  public ShipAdapter(@JsonProperty("coord") Coord coord,
                     @JsonProperty("length") int length,
                     @JsonProperty("direction") Direction dir) {
    this.start = coord;
    this.length = length;
    this.dir = dir;
  }

  /**
   * Makes a ship to json
   *
   * @return a ship in json format
   */
  public ShipJson toShipJson() {
    return new ShipJson(
        new CoordJson(this.start.getX(), this.start.getY()),
        this.length,
        this.dir.name() // Assuming that Direction is an enum with HORIZONTAL and VERTICAL
    );
  }
}

  /*
  public AShip toShip() {
    // return a new Ship object constructed from the properties
    return new AShip(start, length, dir);
  }
   */

