package cs3500.pa04.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for battleship type
 */
public class BattleshipTest {

  @Test
  public void testBattleshipType() {
    Battleship battleship = new Battleship();
    assertEquals(ShipType.BATTLESHIP, battleship.getShipType());
  }

  @Test
  public void testBattleshipSize() {
    Battleship battleship = new Battleship();
    assertEquals(ShipType.BATTLESHIP.getSize(), battleship.getSize());
  }
}
