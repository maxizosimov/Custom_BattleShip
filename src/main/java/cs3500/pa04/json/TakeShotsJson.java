package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents taken shot data
 *
 * @param coords coordinates of shots
 */
public record TakeShotsJson(
    @JsonProperty("coordinates") CoordJson[] coords) {
}
