package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.controller.GameController;
import cs3500.pa04.model.player.AiPlayer;
import cs3500.pa04.model.player.HumanPlayer;
import cs3500.pa04.model.ship.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelTest {

  private final Map<ShipType, Integer> specifications = new HashMap<>();
  private Model model;
  private AiPlayer aiPlayer1 = new AiPlayer();
  private HumanPlayer humanPlayer1 = new HumanPlayer(new GameController());

  private final AiPlayer aiPlayer2 = new AiPlayer();
  private final HumanPlayer humanPlayer2 = new HumanPlayer(new GameController());
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setup() {
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    model = new Model(6, 6, specifications);
    humanPlayer1.setup(6, 6, specifications);
    aiPlayer1.setup(6, 6, specifications);
    aiPlayer2.setup(6, 6, new HashMap<>());
    humanPlayer2.setup(6, 6, new HashMap<>());
    System.setOut(new PrintStream(outputStreamCaptor));
    System.setOut(System.out);
  }

  @Test
  public void testTakeShots() {
    assertEquals(0, aiPlayer1.getAlreadyShot().size());

    model.takeShots(humanPlayer1, aiPlayer1);
  }

  @Test
  public void testUpdatePlayers() {
    model.updatePlayers(humanPlayer1, aiPlayer1);

    assertEquals(4, humanPlayer1.getNumRemainingShips());
    assertEquals(4, aiPlayer1.getNumRemainingShips());
  }

  @Test
  public void testEndGame() {
    assertFalse(model.endGame(humanPlayer1, aiPlayer1));
    assertTrue(model.endGame(humanPlayer1, aiPlayer2));
  }

  @Test
  public void testSetupRound() {
    humanPlayer1.setAllowedShots(6);
    aiPlayer1.setAllowedShots(10);

    model.setupRound(humanPlayer1, aiPlayer1);

    assertEquals(4, humanPlayer1.getAllowedShots());
    assertEquals(4, aiPlayer1.getAllowedShots());
  }

  @Test
  public void testSetupPlayers() {
    humanPlayer1 = new HumanPlayer(new GameController());
    aiPlayer1 = new AiPlayer();

    model.setupPlayers(humanPlayer1);
    model.setupPlayers(aiPlayer1);

    assertEquals(4, humanPlayer1.getAllowedShots());
    assertEquals(4, aiPlayer1.getAllowedShots());
  }

  @Test
  public void testDetermineWinnerManual() {
    model.determineWinner(humanPlayer2, aiPlayer1);
    String expectedOutput = """
        Game Over!
        You lost! Better luck next time!
        All your ships have been sunk.
        """;
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  public void testDetermineWinnerAutomated() {
    model.determineWinner(humanPlayer1, aiPlayer2);
    String expectedOutput2 = """
        Game Over!
        Congratulations! You won!
        You sunk all your opponent's ships!
        """;
    assertEquals(expectedOutput2, outputStreamCaptor.toString());
  }

  @Test
  public void testDetermineWinnerTie() {
    model.determineWinner(humanPlayer2, aiPlayer2);
    String expectedOutput2 = """
        Game Over!
        The game ended in a draw!
        Both players sunk all ships last round
        """;
    assertEquals(expectedOutput2, outputStreamCaptor.toString());
  }
}
