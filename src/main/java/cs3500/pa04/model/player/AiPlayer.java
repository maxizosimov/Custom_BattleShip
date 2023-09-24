package cs3500.pa04.model.player;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.coord.GameCoord;
import cs3500.pa04.model.state.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AIPlayer class, a subclass of APlayer.
 * This class represents an AI player in the game, capable of taking random shots on the game board.
 */
public class AiPlayer extends AbstractPlayer {

  private final List<Coord> trackShots;

  // AIPlayer constructor
  public AiPlayer() {
    this.trackShots = new ArrayList<>();
  }

  /**
   * Returns the name of this player.
   *
   * @return the string "AI Player".
   */
  @Override
  public String name() {
    return "AI Player";
  }

  private boolean overlappedFiredShots(Coord currentShot, List<Coord> firedShots) {
    for (Coord firedShot : firedShots) {
      if ((currentShot.getX() == firedShot.getX()) && (currentShot.getY() == firedShot.getY())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Allows the AI player to take random shots on the game board.
   *
   * @return a list of Coords representing the AI player's shots.
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> firedShots = new ArrayList<>();
    Random random = new Random();
    int count = 0;
    int shots = getAllowedShots();
    while (count < shots) {
      int x = random.nextInt(this.width);
      int y = random.nextInt(this.height);
      Coord firedShot = new GameCoord(x, y);
      // && !overlappedShots(firedShots)
      if (!overlappedFiredShots(firedShot, firedShots)
          && !overlappedFiredShots(firedShot, trackShots)) {
        firedShots.add(firedShot);
        count++;
      }
    }
    shotCounter = shotCounter + firedShots.size();
    trackShots.addAll(firedShots);
    return firedShots;
  }

  /**
   * Method for handling end of the game for the AI player.
   * Currently, this method doesn't perform any action.
   *
   * @param result The result of the game.
   * @param reason The reason why the game ended.
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }
}
