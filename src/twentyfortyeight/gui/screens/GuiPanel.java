package twentyfortyeight.gui.screens;

// required imports
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import twentyfortyeight.gui.GuiComponent;

/**
 * Abstract GUI panel so multiple panels may be added
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public abstract class GuiPanel {

    // list of GUI components
    protected ArrayList<GuiComponent> components;

    // default constructor
    public GuiPanel() {
        components = new ArrayList<>();
    }

    /**
     * Updates the GUI components
     */
    public void update() {
        for (GuiComponent comp : components) {
            if(comp.isIsVisible())
            comp.update();
        }
    }

    /**
     * Renders the GUI components
     *
     * @param g default graphics object
     */
    public void render(Graphics2D g) {
        for (GuiComponent comp : components) {
            if(comp.isIsVisible())
            comp.render(g);
        }
    }

    /**
     * Add a GUI component
     *
     * @param comp the GUI component to be added
     */
    public void add(GuiComponent comp) {
        components.add(comp);
    }

    /**
     * Remove a GUI component
     *
     * @param comp the GUI component to be removed
     */
    public void remove(GuiComponent comp) {
        components.remove(comp);
    }

    /**
     * Passes action listener to component when mouse is pressed
     *
     * @param e default MouseEvent object
     */
    public void mousePressed(MouseEvent e) {
        for (GuiComponent comp : components) {
            comp.mousePressed(e);
        }
    }

    /**
     * Passes action listener to component when mouse is released
     *
     * @param e default MouseEvent object
     */
    public void mouseReleased(MouseEvent e) {
        for (GuiComponent comp : components) {
            comp.mouseReleased(e);
        }
    }

    /**
     * Passes action listener to component when mouse is dragged
     *
     * @param e default MouseEvent object
     */
    public void mouseDragged(MouseEvent e) {
        for (GuiComponent comp : components) {
            comp.mouseDragged(e);
        }
    }

    /**
     * Passes action listener to component when mouse is moved
     *
     * @param e default MouseEvent object
     */
    public void mouseMoved(MouseEvent e) {
        for (GuiComponent comp : components) {
            comp.mouseMoved(e);
        }
    }

}
