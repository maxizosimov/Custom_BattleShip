package cs3500.pa04;

import cs3500.pa04.controller.GameController;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.player.AiPlayer;
import java.io.IOException;
import java.net.Socket;


/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * Runs the client side for server
   *
   * @param host the host ("0.0.0.0")
   * @param port the port (35001)
   * @throws IOException correct exception if I/O error
   * @throws IllegalStateException If the state of the run is illegal
   */
  public static void runClient(String host, int port) throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    AiPlayer playerAi = new AiPlayer();
    ProxyController proxyController = new ProxyController(server, playerAi);
    proxyController.run();
  }

  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    int cmdLength = args.length;

    if (cmdLength != 0 && cmdLength != 2) {
      throw new IllegalArgumentException("Please enter either 0 or 2 command-line arguments");
    }

    if (cmdLength == 0) {
      try {
        GameController gameController = new GameController();
        gameController.run();
      } catch (Exception e) {
        System.out.println("An Unexpected Error Occurred. Please Try Again.");
      }
    }

    if (cmdLength == 2) {
      try {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        runClient(host, port);
      } catch (Exception e) {
        System.err.println("An Unexpected Error Occurred. Please Try Again.");
      }
    }
  }
}