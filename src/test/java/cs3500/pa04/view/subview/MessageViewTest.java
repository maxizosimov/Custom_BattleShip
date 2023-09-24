package cs3500.pa04.view.subview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageViewTest {

  private final MessageView messageView = new MessageView();

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    System.setOut(System.out);
  }

  @Test
  public void testDisplay() {
    messageView.display();
    String expectedOutput = "Game Over!\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testHandleWin() {
    messageView.handleWin("Reason");
    String expectedOutput = "Congratulations! You won!\nReason\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testHandleLoss() {
    messageView.handleLoss("Reason");
    String expectedOutput = "You lost! Better luck next time!\nReason\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testHandleTie() {
    messageView.handleTie("Reason");
    String expectedOutput = "The game ended in a draw!\nReason\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @AfterEach
  void tearDown() {
    System.setOut(System.out);
  }
}
