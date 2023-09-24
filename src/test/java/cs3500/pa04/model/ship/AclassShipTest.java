package cs3500.pa04.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import java.util.List;
import org.junit.jupiter.api.Test;

class AclassShipTest {
  @Test
  void testShipCreationAndSize() {
    AbstractShip ship = new Carrier();
    assertEquals(ShipType.CARRIER, ship.getShipType());
    assertEquals(ShipType.CARRIER.getSize(), ship.getSize());
  }

  @Test
  void testShipPlacement() {
    AbstractShip ship = new Battleship();
    ship.generatePlacement(10, 10, List.of(new GameCoord(2, 2)));
    List<Coord> placement = ship.getPlacement();

    assertFalse(placement.isEmpty());
    assertEquals(ShipType.BATTLESHIP.getSize(), placement.size());

    // all placements should be valid
    for (Coord coord : placement) {
      assertTrue(coord.getX() >= 0);
      assertTrue(coord.getY() >= 0);
      assertTrue(coord.getX() <= 10);
      assertTrue(coord.getY() <= 10);
    }
  }

  @Test
  void testClearPlacement() {
    AbstractShip ship = new Submarine();
    ship.generatePlacement(10, 10, List.of(new GameCoord(2, 2)));
    assertFalse(ship.getPlacement().isEmpty());

    ship.clearPlacement();
    assertTrue(ship.getPlacement().isEmpty());
  }
}

