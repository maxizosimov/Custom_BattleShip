package cs3500.pa04.view.subview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FleetViewTest {

  private final FleetView fleetView = new FleetView(7);

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    System.setOut(System.out);
  }

  @Test
  public void testDisplay() {
    fleetView.display();
    String expectedOutput = """
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed size 7.
        """;
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testHandleInvalidInput() {
    fleetView.handleInvalidInput();
    String expectedOutput = """
        Uh Oh! You've entered invalid fleet sizes.
        Please enter your fleet in order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed size 7.
        """;
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @AfterEach
  void tearDown() {
    System.setOut(System.out);
  }
}
