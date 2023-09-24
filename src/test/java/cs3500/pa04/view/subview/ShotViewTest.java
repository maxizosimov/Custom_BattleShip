package cs3500.pa04.view.subview;



import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.coord.GameCoord;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShotViewTest {
  private final ShotView shotView = new ShotView(3);

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void testDisplay() {
    shotView.display();
    assertEquals("Please Enter 3 Shots:", outputStreamCaptor.toString().trim());
  }

  @Test
  void testHandleInvalidInput() {
    shotView.handleInvalidInput();
    assertEquals("Invalid Shots. Please Enter 3 Shots.", outputStreamCaptor.toString().trim());
  }

  @Test
  void testDisplaySuccessfulHits() {
    shotView.displaySuccessfulHits(Arrays.asList(new GameCoord(1, 2),
        new GameCoord(3, 4)), "TestPlayer");
    String expectedOutput = "TestPlayer Successful Hits: \n[1 2]\n[3 4]";
    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  }

  @AfterEach
  void tearDown() {
    System.setOut(System.out);
  }
}

