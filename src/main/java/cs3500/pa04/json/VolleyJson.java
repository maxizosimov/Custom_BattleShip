package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A volley consisting of shots
 *
 * @param shots shots taken
 */
public record VolleyJson(
    @JsonProperty("shots") CoordJson[] shots) {
}
