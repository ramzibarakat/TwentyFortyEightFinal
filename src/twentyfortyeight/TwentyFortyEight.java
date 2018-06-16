package twentyfortyeight;

// required import
import javax.swing.JFrame;

//
/**
 * Main class which creates the frame and starts the game
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class TwentyFortyEight {

    public static JFrame frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Calls the createFrame method which creates the game frame
        frame = createFrame();
    }

    /**
     * Creates the frame for the game and sets its properties
     *
     * @return returns the frame
     */
    private static JFrame createFrame() {

        Game game = new Game();

        // Create and set up the window
        JFrame frame = new JFrame("2048");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

        return frame;

    }

}
