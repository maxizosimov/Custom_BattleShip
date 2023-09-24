package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents fleet data
 *
 * @param fleet a fleet of ship data
 */
public record FleetJson(
    @JsonProperty("fleet") ShipJson[] fleet) {
}
