package twentyfortyeight.gui.screens;

// required imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import twentyfortyeight.Box;
import twentyfortyeight.Game;
import twentyfortyeight.GameBoard;
import twentyfortyeight.TwentyFortyEight;
import twentyfortyeight.gui.GuiButton;
import twentyfortyeight.gui.GuiScreen;

/**
 * The game panel is a GUI panel and it is where the game actually takes place.
 * This class renders the board and fixes the size of the board if the grid size
 * is altered
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class GamePanel extends GuiPanel {

    // GameBoard variable
    public static GameBoard board;
    private GuiButton back;
    private GuiButton escape;

    // default constructor
    public GamePanel() {

        int centerX = Game.SCREEN_WIDTH / 2 - 200 / 2;

        // The menu button
        back = new GuiButton(centerX + 75,
                Game.SCREEN_HEIGHT - 100 - 50,
                200, 100);

        // Text "Menu" on button
        back.setText("Menu");
        back.setVisible(false);

        // goes back to menu screen
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.MENU_SCREEN);
                board.resetGame();
                resetVisibility();
            }
        });

        // adds the button to the frame
        add(back);

        // The menu button
        escape = new GuiButton(10, 10, 150, 75);

        // Text "Menu" on button
        escape.setText("Menu");
        escape.setVisible(true);

        // goes back to menu screen
        escape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GuiScreen.getInstance().setCurrentPanel(GuiScreen.MENU_SCREEN);
                board.resetGame();
                setGameBoardSize(4);
                resetVisibility();
            }
        });

        // adds the button to the frame
        add(escape);
    }

    // Overridden methods
    // update the board
    @Override
    public void update() {
        board.update();
    }

    // render the screen, buttons, and board
    @Override
    public void render(Graphics2D g) {

        // backgorund render first
        g.setColor(new Color(230, 210, 200));
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // render the board
        board.render(g);

        // render game over button
        if (GameBoard.isGameOver) {
            back.setVisible(true);
            escape.setVisible(false);
        }
        super.render(g);
    }

    private void resetVisibility() {

        back.setVisible(false);
        escape.setVisible(true);

    }

    /**
     * Set the size of the game board
     *
     * @param size number of boxes in a row / column
     */
    public static void setGameBoardSize(int size) {

        /**
         * updating size should be done first to tell GameBoard about new frame
         * size
         */
        updateFrameSize(size);

        // create new board with the specified size
        board = new GameBoard(size);

    }

    /**
     * Updates the frame size by changing its dimensions accordingly
     *
     * @param boardSize the size of one side from the board
     */
    private static void updateFrameSize(int boardSize) {

        // the game frame
        JFrame frame = TwentyFortyEight.frame;

        // the width of the screen relative to the size of the board
        Game.SCREEN_WIDTH = Box.WIDTH * boardSize + (boardSize + 1) * GameBoard.SPACING + 30;

        // the height of the screen relative to the size of the board
        Game.SCREEN_HEIGHT = Game.SCREEN_WIDTH + Game.SCREEN_VERTICAL_GAP;

        frame.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // update the size
        Game.updateImageSize();

        // centre frame
        frame.setLocationRelativeTo(null);

    }

}
