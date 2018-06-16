package twentyfortyeight;

// required imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import twentyfortyeight.gui.screens.GamePanel;
import twentyfortyeight.gui.GuiScreen;
import twentyfortyeight.gui.screens.MainMenuPanel;
import twentyfortyeight.gui.screens.ModeSelectPanel;
import twentyfortyeight.gui.screens.SurvivalSettingPanel;
import twentyfortyeight.gui.screens.InstructionPanel;

/**
 * The game panel to add into the frame
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class Game extends JPanel implements KeyListener, MouseListener,
        MouseMotionListener, Runnable {

    // default serial 
    public static final long serialVersionUID = 1L;

    // Screen dimensions
    public static final int SCREEN_VERTICAL_GAP = 200;
    public static int SCREEN_WIDTH = 400;
    public static int SCREEN_HEIGHT = SCREEN_WIDTH + SCREEN_VERTICAL_GAP;

    // Font sizes
    public static final int FONT_SIZE_SMALL = 24;
    public static final int FONT_SIZE_MEDIUM = 28;
    public static final int FONT_SIZE_BIG = 32;

    // Font "Papyrus"
    public static final Font FONT = new Font("Papyrus",
            Font.PLAIN, FONT_SIZE_MEDIUM);

    // game thread variable
    private Thread game;

    // boolean variable for checking if the game is running
    private boolean running;

    // new screen image
    private static BufferedImage image
            = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
                    BufferedImage.TYPE_INT_ARGB);

    // GuiScreen variable
    private GuiScreen screen;

    // Default Constructor
    public Game() {

        // allow the frame to accept input
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        /**
         * creates the screen and adds the menu screen, select mode screen, game
         * screen and survival setting screen
         */
        screen = GuiScreen.getInstance();
        screen.add(GuiScreen.MENU_SCREEN, new MainMenuPanel());
        screen.add(GuiScreen.SELECT_MODE_SCREEN, new ModeSelectPanel());
        screen.add(GuiScreen.GAME_SCREEN, new GamePanel());
        screen.add(GuiScreen.SURVIVAL_SETTING_SCREEN, new SurvivalSettingPanel());
        screen.add(GuiScreen.INSTRUCTIONS_SCREEN, new InstructionPanel());

        // sets the starting screen as the menu screen 
        screen.setCurrentPanel(GuiScreen.MENU_SCREEN);

    }

    /**
     * Updates the screen
     */
    private void update() {

        screen.update();
        Keyboard.update();

    }

    /**
     * renders graphics of the game board
     */
    private void render() {

        // graphics of the gameboard
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        screen.render(g);
        g.dispose();

        // graphics of frame, render the gameboard
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

    }

    /**
     * Overridden method which keeps track of the updates and the FPS timer
     */
    @Override
    public void run() {

        int fps = 0, updates = 0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000.0 / 60; // 60 fps

        double then = System.nanoTime();
        double unprocessed = 0;

        while (running) {

            // boolean to set whether or not it should render
            boolean shouldRender = false;
            double now = System.nanoTime();
            unprocessed += (now - then) / nsPerUpdate;
            then = now;

            // while needed to process
            while (unprocessed >= 1) {
                updates++;
                update();
                unprocessed--;
                shouldRender = true;
            }

            // render after process
            if (shouldRender) {
                fps++;
                render();
                shouldRender = false;
            } else {
                // try catch error exception
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // fps timer 
        if (System.currentTimeMillis() - fpsTimer > 1000) {
            System.out.println(fps + " fps " + updates + " update");
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }

    }

    /**
     * Starts the game (creates new thread)
     */
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        game = new Thread(this, "game");
        game.start();
    }

    /**
     * Stops the game
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        System.exit(0);
    }

    /**
     * This keyReleased method will not be used because it cannot detect the
     * KeyCode of arrow keys
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keyboard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.keyRelesaed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        screen.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        screen.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        screen.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        screen.mouseMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Update the image to fit the size of the new dimension of the screen
     */
    public static void updateImageSize() {
        image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
    }

}
