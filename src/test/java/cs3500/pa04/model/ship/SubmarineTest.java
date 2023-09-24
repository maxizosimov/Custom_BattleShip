package cs3500.pa04.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for submarine type
 */
public class SubmarineTest {

  @Test
  public void testSubmarineType() {
    Submarine submarine = new Submarine();
    assertEquals(ShipType.SUBMARINE, submarine.getShipType());
  }

  @Test
  public void testSubmarineSize() {
    Submarine submarine = new Submarine();
    assertEquals(ShipType.SUBMARINE.getSize(), submarine.getSize());
  }
}

