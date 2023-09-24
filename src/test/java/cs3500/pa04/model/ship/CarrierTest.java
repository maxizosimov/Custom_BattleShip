package cs3500.pa04.model.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for carrier type
 */
public class CarrierTest {

  @Test
  public void testCarrierType() {
    Carrier carrier = new Carrier();
    assertEquals(ShipType.CARRIER, carrier.getShipType());
  }

  @Test
  public void testCarrierSize() {
    Carrier carrier = new Carrier();
    assertEquals(ShipType.CARRIER.getSize(), carrier.getSize());
  }
}

