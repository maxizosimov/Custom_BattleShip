package cs3500.pa04.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for destroyer shiptype
 */
public class DestroyerTest {

  @Test
  public void testDestroyerType() {
    Destroyer destroyer = new Destroyer();
    assertEquals(ShipType.DESTROYER, destroyer.getShipType());
  }

  @Test
  public void testDestroyerSize() {
    Destroyer destroyer = new Destroyer();
    assertEquals(ShipType.DESTROYER.getSize(), destroyer.getSize());
  }
}

