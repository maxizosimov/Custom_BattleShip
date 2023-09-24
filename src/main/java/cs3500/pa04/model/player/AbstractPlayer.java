package cs3500.pa04.model.player;

import cs3500.pa04.model.coord.Coord;
import cs3500.pa04.model.ship.AbstractShip;
import cs3500.pa04.model.ship.Battleship;
import cs3500.pa04.model.ship.Carrier;
import cs3500.pa04.model.ship.Destroyer;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.model.ship.Submarine;
import cs3500.pa04.model.state.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * APlayer is an abstract class that implements the Player interface.
 * This class provides common fields and methods for all types of players in the game.
 * It leaves implementation of specific details to the subclasses.
 */
public abstract class AbstractPlayer implements Player {
  private final List<Coord> occupiedCoords;
  private final List<Coord> hits;
  private final List<Coord> firedShots;
  private final List<Ship> fleet;
  private final List<Ship> notSunkShips;
  private int numShipsRemaining;
  private int availableShots;
  protected int shotCounter;

  public AbstractShip originalShip; //remove
  protected int width;
  protected int height;

  /**
   * Default constructor for APlayer class.
   * Initializes all fields to empty lists or zero for numerical values.
   */
  public AbstractPlayer() {
    occupiedCoords = new ArrayList<>();
    hits = new ArrayList<>();
    firedShots = new ArrayList<>();
    fleet = new ArrayList<>();
    notSunkShips = new ArrayList<>();
    numShipsRemaining = 0;
    availableShots = 0;
  }

  /**
   * Abstract method that needs to be implemented by subclasses.
   * Returns the name of the player.
   *
   * @return the name of the player.
   */
  @Override
  public abstract String name();

  /**
   * Sets up the game board according to provided height, width and ship specifications.
   *
   * @param height         The height of the board.
   * @param width          The width of the board.
   * @param specifications A Map with ShipType as key and number of such type of ship as value.
   * @return a List of ships belonging to the player after setup.
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.height = height;
    this.width = width;

    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      ShipType shipType = entry.getKey();
      int numShipType = entry.getValue();

      for (int i = 0; i < numShipType; i++) {
        Ship ship = switch (shipType) {
          case CARRIER -> createShip(new Carrier(), height, width);
          case BATTLESHIP -> createShip(new Battleship(), height, width);
          case DESTROYER -> createShip(new Destroyer(), height, width);
          case SUBMARINE -> createShip(new Submarine(), height, width);
          default -> throw new IllegalArgumentException("Invalid ship type: " + shipType);
        };
        fleet.add(ship);
        notSunkShips.add(ship);
      }
    }
    numShipsRemaining = fleet.size();
    availableShots = fleet.size();
    return fleet;
  }

  /**
   * Creates a ship and initiates its placement on the game board.
   *
   * @param ship   The ship to be created.
   * @param height The height of the game board.
   * @param width  The width of the game board.
   * @return The created ship.
   */
  private Ship createShip(AbstractShip ship, int height, int width) {
    initiateShip(ship, height, width);
    return ship;
  }

  /**
   * An abstract method to be implemented by subclasses for taking shots in the game.
   *
   * @return a List of Coords where the player decides to shoot.
   */
  public abstract List<Coord> takeShots();

  /**
   * Checks for successful hits made by the opponent player.
   *
   * @param opponentShotsOnBoard A list of Coordinates representing the opponent's shots.
   * @return A list of Coordinates representing successful hits.
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> shotsThatHitShips = new ArrayList<>();
    for (Coord shot : opponentShotsOnBoard) {
      if (occupiedCoords.contains(shot)) {
        shotsThatHitShips.add(shot);
      }
    }
    updateRemainingShips(shotsThatHitShips);
    this.availableShots = numShipsRemaining;
    return shotsThatHitShips;
  }

  /**
   * Updates the list of successful hits by the player.
   *
   * @param shotsThatHitOpponentShips A list of Coordinates representing successful hits.
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    hits.clear();
    hits.addAll(shotsThatHitOpponentShips);
  }

  /**
   * Abstract method for ending the game, must be implemented by subclasses.
   *
   * @param result The final result of the game.
   * @param reason The reason for the game ending.
   */
  public abstract void endGame(GameResult result, String reason);

  /**
   * Checks if the placement of a ship is out of bounds of the game board.
   *
   * @param height    The height of the game board.
   * @param width     The width of the game board.
   * @param placement The list of Coords where the ship is to be placed.
   * @return true if the placement is out of bounds, false otherwise.
   */
  private boolean outOfBoundsPlacement(int height, int width, List<Coord> placement) {
    boolean outOfBounds = false;
    for (Coord coord : placement) {
      if (coord.getX() >= width || coord.getY() >= height || coord.getX() < 0 || coord.getY() < 0) {
        outOfBounds = true;
      }
    }
    return outOfBounds;
  }


  /**
   * Checks if the placement of a ship overlaps with an existing ship.
   *
   * @param placement The list of Coords where the ship is to be placed.
   * @return true if the placement overlaps with an existing ship, false otherwise.
   */
  private boolean occupiedPlacement(List<Coord> placement) {
    boolean overlap = false;
    for (Coord coord : placement) {
      if (occupiedCoords.contains(coord)) {
        overlap = true;
        break;
      }
    }
    return overlap;
  }


  /**
   * Initiates a ship's placement on the game board.
   * The ship's placement is randomly generated until a valid placement is found.
   *
   * @param ship   The ship to be placed on the game board.
   * @param height The height of the game board.
   * @param width  The width of the game board.
   */
  private void initiateShip(AbstractShip ship, int height, int width) {
    this.originalShip = ship;
    boolean validPlacement = false;
    while (!validPlacement) {
      ship.clearPlacement();
      ship.generatePlacement(height, width, occupiedCoords);
      if (!occupiedPlacement(ship.getPlacement()) && !outOfBoundsPlacement(height, width,
          ship.getPlacement())) {
        validPlacement = true;
      }
    }
    occupiedCoords.addAll(ship.getPlacement());
  }

  /**
   * Updates the remaining ships after the opponent's turn.
   *
   * @param opponentShotsOnBoard a List of Coords that represent the opponent's shots.
   */
  public void updateRemainingShips(List<Coord> opponentShotsOnBoard) {
    List<Ship> sunkShips = new ArrayList<>();

    for (Ship ship : notSunkShips) {
      ship.getPlacement().stream()
          .filter(opponentShotsOnBoard::contains)
          .forEach(Coord::updateIfHitCoordinate);

      boolean fullySunk = ship.getPlacement().stream().allMatch(Coord::getHitShip);

      if (fullySunk) {
        sunkShips.add(ship);
      }
    }

    notSunkShips.removeAll(sunkShips);
    numShipsRemaining = notSunkShips.size();
  }

  /**
   * Returns the number of ships remaining.
   *
   * @return the number of ships remaining.
   */
  public int getNumRemainingShips() {
    return numShipsRemaining;
  }

  /**
   * Returns a list of coordinates where the player has already shot.
   *
   * @return a List of Coords representing the coordinates where the player has already shot.
   */
  public List<Coord> getAlreadyShot() {
    return firedShots;
  }

  /**
   * Returns a list of coordinates occupied by the player's ships.
   *
   * @return a List of Coords representing the coordinates occupied by the player's ships.
   */
  public List<Coord> getShipOccupiedCoordinates() {
    return occupiedCoords;
  }

  /**
   * Adds a shot coordinate to the list of coordinates where the player has already shot.
   *
   * @param shot a Coord representing the shot to add.
   */
  public void addToAlreadyShot(Coord shot) {
    firedShots.add(shot);
  }

  /**
   * Returns the number of shots allowed for the player.
   *
   * @return the number of shots allowed for the player.
   */
  public int getAllowedShots() {
    int maxAllowedShots = this.width * this.height;
    return Math.min(maxAllowedShots - shotCounter, availableShots);
  }

  /**
   * Sets the number of shots allowed for the player.
   *
   * @param availableShots the number of shots to be set.
   */
  public void setAllowedShots(int availableShots) {
    this.availableShots = availableShots;
  }

  /**
   * Returns a list of successful hits the player made.
   *
   * @return a List of Coords representing the successful hits made by the player.
   */
  public List<Coord> getSuccessfulHits() {
    return this.hits;
  }
}


