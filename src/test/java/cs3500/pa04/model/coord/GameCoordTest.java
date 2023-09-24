package cs3500.pa04.model.coord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GameCoordTest {

  @Test
  void testGetX() {
    GameCoord coord = new GameCoord(1, 2);
    assertEquals(1, coord.getX());
  }

  @Test
  void testGetY() {
    GameCoord coord = new GameCoord(1, 2);
    assertEquals(2, coord.getY());
  }

  @Test
  void testGetHitShip() {
    GameCoord coord = new GameCoord(1, 2);
    assertFalse(coord.getHitShip());
  }

  @Test
  void testUpdateIfHitCoordinate() {
    GameCoord coord = new GameCoord(1, 2);
    coord.updateIfHitCoordinate();
    assertTrue(coord.getHitShip());
  }

  @Test
  void testEquals() {
    GameCoord coord1 = new GameCoord(1, 2);
    GameCoord coord2 = new GameCoord(1, 2);
    GameCoord coord3 = new GameCoord(2, 1);
    assertEquals(coord1, coord2);
    assertNotEquals(coord1, coord3);
  }

  @Test
  void testEqualsWithNonGameCoord() {
    GameCoord coord1 = new GameCoord(1, 2);
    String str = "Not a GameCoord";
    assertNotEquals(coord1, str);
  }
}

