package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.coord.Coord;
import java.util.List;

/**
 * Represents the hit coords data
 *
 * @param shots shots taken
 */
public record ReportDamageJson(
    @JsonProperty("coordinates") List<Coord> shots) {

  @JsonIgnore
  public List<Coord> getShots() {
    return shots;
  }
}
