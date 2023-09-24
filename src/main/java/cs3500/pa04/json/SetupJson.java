package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ship.ShipType;
import java.util.Map;

/**
 * Represents the setup of ships in a game (data)
 *
 * @param width of a board
 * @param height of a board
 * @param fleetSpec of a board
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") Map<ShipType, Integer> fleetSpec) {
}
