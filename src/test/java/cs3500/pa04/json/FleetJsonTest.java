package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for fleetJson record
 */
public class FleetJsonTest {
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void fleetJsonSerializationTest() throws Exception {
    // Create test data
    ShipJson[] ships = {
        new ShipJson(new CoordJson(1, 2), 3, "HORIZONTAL"),
        new ShipJson(new CoordJson(3, 4), 2, "VERTICAL")
    };
    FleetJson fleetJson = new FleetJson(ships);

    // Serialize FleetJson to JSON
    String json = objectMapper.writeValueAsString(fleetJson);

    // Deserialize JSON to FleetJson
    FleetJson deserializedFleetJson = objectMapper.readValue(json, FleetJson.class);

    // Assert the deserialized object is equal to the original
    assertArrayEquals(ships, deserializedFleetJson.fleet());
  }

  @Test
  void fleetJsonDeserializationTest() throws Exception {
    // Create test data
    String json = "{\"fleet\":[{\"coord\":{\"x\":1,\"y\":2},\"length\":3,\"direction\":"
        + "\"HORIZONTAL\"},{\"coord\":{\"x\":3,\"y\":4},\"length\":2,\"direction\":\"VERTICAL\"}]}";

    // Deserialize JSON to FleetJson
    FleetJson fleetJson = objectMapper.readValue(json, FleetJson.class);

    // Assert the deserialized object contains the correct ships
    ShipJson[] expectedShips = {
        new ShipJson(new CoordJson(1, 2), 3, "HORIZONTAL"),
        new ShipJson(new CoordJson(3, 4), 2, "VERTICAL")
    };
    assertArrayEquals(expectedShips, fleetJson.fleet());
  }
}
