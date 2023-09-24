package cs3500.pa04.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.state.GameResult;
import java.util.List;
import org.junit.jupiter.api.Test;

class AiPlayerTest {

  @Test
  void testAiPlayerName() {
    AiPlayer player = new AiPlayer();
    assertEquals("AI Player", player.name());
  }

  @Test
  void testAiPlayerTakeShots() {
    AiPlayer player = new AiPlayer();
    List<Coord> firedShots = player.takeShots();

    // Checking the size of fired shots
    assertEquals(player.getAllowedShots(), firedShots.size());

    // Checking there are no duplicate shots
    long distinctCount = firedShots.stream().distinct().count();
    assertEquals(firedShots.size(), distinctCount);

    // Checking coordinates are within the valid range (0-15)
    for (Coord shot : firedShots) {
      assertTrue(shot.getX() >= 0 && shot.getX() < 16);
      assertTrue(shot.getY() >= 0 && shot.getY() < 16);
    }
  }

  // Since endGame does nothing, we can only check if it doesn't throw an exception
  @Test
  void testAiPlayerEndGame() {
    AiPlayer player = new AiPlayer();
    try {
      player.endGame(GameResult.WIN, "Test");
    } catch (Exception e) {
      fail("endGame method should not throw any exception.");
    }
  }
}

