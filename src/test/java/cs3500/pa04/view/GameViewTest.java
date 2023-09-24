package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameViewTest {
  // Create an anonymous subclass of GameView for testing the abstract class.
  private final GameView gameView = new GameView() {
    @Override
    public void display() {
      printMessage("Test message");
    }
  };

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  public void testDisplay() {
    gameView.display();
    assertEquals("Test message\n", outputStreamCaptor.toString());
  }
}
