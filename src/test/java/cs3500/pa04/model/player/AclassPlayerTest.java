package cs3500.pa04.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.GameController;
import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.ship.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AclassPlayerTest {

  AbstractPlayer manualPlayer = new HumanPlayer(new GameController());

  private final Map<ShipType, Integer> specifications = new HashMap<>();
  Coord c1 = new GameCoord(1, 1);
  Coord c2 = new GameCoord(6, 6);

  List<Coord> coords = new ArrayList<>(Arrays.asList(c1, c2));

  @Test
  public void testGetSuccessfulHits() {
    manualPlayer.successfulHits(coords);
    List<Coord> hits = manualPlayer.getSuccessfulHits();
    assertEquals(coords, hits);
  }

  @Test
  public void testGetShipOccupiedCoordinates() {
    assertEquals(new ArrayList<>(), manualPlayer.getShipOccupiedCoordinates());
  }

  @Test
  public void testUpdateRemainingShips() {
    specifications.put(ShipType.CARRIER, 6);
    manualPlayer.setup(6, 6, specifications);
    manualPlayer.updateRemainingShips(coords);
  }

}
