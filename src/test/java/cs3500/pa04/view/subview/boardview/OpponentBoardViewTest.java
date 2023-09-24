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


class OpponentBoardViewTest {

  private OpponentBoardView opponentBoardView;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private List<Coord> opponentShipCoordinates;
  private List<Coord> alreadyShot;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    opponentShipCoordinates = new ArrayList<>();
    alreadyShot = new ArrayList<>();
    opponentShipCoordinates.add(new GameCoord(1, 1));
    alreadyShot.add(new GameCoord(1, 1));
    opponentBoardView = new OpponentBoardView(3, 3, opponentShipCoordinates, alreadyShot);
  }

  @AfterEach
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  public void testDisplay() {
    opponentBoardView.display();
    String expectedOutput = "Opponent Board: \n H  0  0 \n 0  0  0 \n 0  0  0 \n\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testPrintOpponentBoard() {
    opponentBoardView.printOpponentBoard(opponentShipCoordinates, alreadyShot);
    String expectedOutput = "Opponent Board: \n H  0  0 \n 0  0  0 \n 0  0  0 \n\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }
}

