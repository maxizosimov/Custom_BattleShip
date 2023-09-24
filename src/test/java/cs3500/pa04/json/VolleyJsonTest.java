package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for volleyJson record
 */
public class VolleyJsonTest {

  @Test
  public void testShots() {
    // Arrange
    CoordJson coord1 = new CoordJson(0, 0);
    CoordJson coord2 = new CoordJson(1, 1);
    CoordJson coord3 = new CoordJson(2, 2);

    CoordJson[] coords = new CoordJson[] { coord1, coord2, coord3 };

    // Act
    VolleyJson volley = new VolleyJson(coords);

    // Assert
    assertEquals(coords, volley.shots());
    assertEquals(coord1, volley.shots()[0]);
    assertEquals(coord2, volley.shots()[1]);
    assertEquals(coord3, volley.shots()[2]);
  }
}
