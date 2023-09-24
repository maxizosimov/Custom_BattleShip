package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.state.GameResult;

/**
 * Represents the endgame in data form
 *
 * @param result the game result (win, lose, draw)
 * @param reason the reason for the game result
 */
public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {

  public GameResult getGameResult() {
    return result;
  }
}
