package cs3500.pa04.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class GameControllerTest {

  @Test
  public void testRun() {
    String input = """
        6 6
        0 0 0 1
        1 1
        1 2
        1 3
        1 4
        1 5
        1 6
        2 1
        2 2
        2 3
        2 4
        2 5
        2 6
        3 1
        3 2
        3 3
        3 4
        3 5
        3 6
        4 1
        4 2
        4 3
        4 5
        4 6
        5 1
        5 2
        5 3
        5 4
        5 5
        5 6
        6 1
        6 2
        6 3
        6 3
        6 4
        6 5
        6 6
        """;
    ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inStream);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    GameController controller = new GameController();

    controller.run();

    System.setOut(originalOut);

    System.setIn(System.in);
  }
}
