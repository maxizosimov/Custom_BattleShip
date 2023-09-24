package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.state.GameResult;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Tests for endGame record
 */
public class EndGameJsonTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  //    "result" : "WIN",
  //    "reason" : "You won!"
  @Test
  public void testEndGameJsonDeserialization() throws IOException {
    String json = "{\"result\":\"WIN\",\"reason\":\"You won!\"}";

    EndGameJson deserialized = objectMapper.readValue(json, EndGameJson.class);

    assertEquals(GameResult.WIN, deserialized.getGameResult());
    assertEquals("You won!", deserialized.reason());
  }

  @Test
  public void testEndGameJsonSerialization() throws IOException {
    EndGameJson endGameJson = new EndGameJson(GameResult.WIN, "You won!");

    String serialized = objectMapper.writeValueAsString(endGameJson);

    assertEquals("{\"result\":\"WIN\",\"reason\":\"You won!\",\"gameResult\":\"WIN\"}",
        serialized);
  }
}
