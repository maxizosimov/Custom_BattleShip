package cs3500.pa04.view.subview.boardview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserBoardViewTest {

  private UserBoardView userBoardView;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private List<Coord> userShipCoordinates;
  private List<Coord> opponentShots;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    userShipCoordinates = new ArrayList<>();
    opponentShots = new ArrayList<>();
    userShipCoordinates.add(new GameCoord(1, 1));
    opponentShots.add(new GameCoord(1, 1));
    userBoardView = new UserBoardView(3, 3, userShipCoordinates, opponentShots);
  }

  @AfterEach
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  public void testDisplay() {
    userBoardView.display();
    String expectedOutput = "Your Board: \n H  0  0 \n 0  0  0 \n 0  0  0 \n\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testPrintUserBoard() {
    userBoardView.printUserBoard(userShipCoordinates, opponentShots);
    String expectedOutput = "Your Board: \n H  0  0 \n 0  0  0 \n 0  0  0 \n\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }
}

