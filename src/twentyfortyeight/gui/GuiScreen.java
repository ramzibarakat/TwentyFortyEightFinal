package twentyfortyeight.gui;

// required imports
import twentyfortyeight.gui.screens.GuiPanel;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * The GuiScreen class includes all the different screens and renders and
 * updates them
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class GuiScreen {

    // menu title
    public static final String MENU_SCREEN = "menu";

    /**
     * Classic 2048 Mode and Survival mode with custom board size
     */
    public static final String SELECT_MODE_SCREEN = "select_mode";
    public static final String INSTRUCTIONS_SCREEN = "instructions";
    public static final String GAME_SCREEN = "game";

    /**
     * The screen that shows the menu to
     */
    public static final String SURVIVAL_SETTING_SCREEN = "survival_setting";

    public static final String GAME_OVER_SCREEN = "game_over";

    // GuiScreen variable
    private static GuiScreen screen;

    // like an array with string index
    private HashMap<String, GuiPanel> panels;
    private String currentPanelName;

    /**
     * Default constructor which contains the list of panels to show
     */
    private GuiScreen() {

        panels = new HashMap<>();
        currentPanelName = "";

    }

    /**
     * Get the instance of GUI panel
     *
     * @return the screen
     */
    public static GuiScreen getInstance() {
        if (screen == null) {
            screen = new GuiScreen();
        }
        return screen;
    }

    /**
     * update the state of panel
     */
    public void update() {

        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.update();
        }

    }

    /**
     * Render panel according to its state
     *
     * @param g default graphics object
     */
    public void render(Graphics2D g) {

        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.render(g);
        }

    }

    /**
     * Add a panel
     *
     * @param panelName the name of the panel
     * @param panel the type of panel
     */
    public void add(String panelName, GuiPanel panel) {
        panels.put(panelName, panel);
    }

    /**
     * Set name of panel
     *
     * @param name the name of panel
     */
    public void setCurrentPanel(String name) {
        currentPanelName = name;
    }

    /**
     * Checks when mouse is pressed
     *
     * @param e default MouseEvent object
     */
    public void mousePressed(MouseEvent e) {
        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.mousePressed(e);
        }
    }

    /**
     * Checks when mouse is released
     *
     * @param e default MouseEvent object
     */
    public void mouseReleased(MouseEvent e) {
        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.mouseReleased(e);
        }
    }

    /**
     * Checks when mouse is dragged
     *
     * @param e default MouseEvent object
     */
    public void mouseDragged(MouseEvent e) {
        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.mouseDragged(e);
        }
    }

    /**
     * Checks when mouse is moved
     *
     * @param e default MouseEvent object
     */
    public void mouseMoved(MouseEvent e) {
        GuiPanel current = panels.get(currentPanelName);
        if (current != null) {
            current.mouseMoved(e);
        }
    }

}
