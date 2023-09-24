package cs3500.pa04.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.GameController;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {

  GameController controller = new GameController();

  HumanPlayer player = new HumanPlayer(controller);

  @Test
  public void testName() {
    assertEquals("Manual Player", player.name());
  }
}
