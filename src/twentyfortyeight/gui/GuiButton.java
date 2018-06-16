package twentyfortyeight.gui;

// reqruired imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import twentyfortyeight.DrawUtils;
import twentyfortyeight.gui.screens.GuiState;

/**
 * The GuiButton class is a GUI component which creates a button with desired
 * dimensions and includes an action listener for when the button is pressed and
 * released
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class GuiButton extends GuiComponent {

    // button properties
    private static final int ARC_SIZE = 30;
    private GuiState currentState = GuiState.RELEASED;
    private boolean isRoundCorner = true;

    /**
     * Constructor passes parameters for the button
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width of the button
     * @param height the height of the button
     */
    public GuiButton(int x, int y, int width, int height) {

        super(x, y, width, height);

    }

    /**
     * Do nothing
     */
    @Override
    public void update() {
    }

    /**
     * Renders the button
     *
     * @param g default graphics object
     */
    @Override
    public void render(Graphics2D g) {

        // button color
        if (null != currentState) {
            switch (currentState) {
                case RELEASED: // when mouse released
                    g.setColor(GuiState.RELEASED.getColor());
                    break;
                case HOVER: // when mouse hovering
                    g.setColor(GuiState.HOVER.getColor());
                    break;
                case PRESSED: // when mouse pressed
                    g.setColor(GuiState.PRESSED.getColor());
            }
        }

        // draw rounded button
        if (isRoundCorner) {
            g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height,
                    ARC_SIZE, ARC_SIZE);
        } // draw squared button
        else {
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        // text color
        g.setColor(Color.WHITE);
        g.setFont(font);

        // text in button
        g.drawString(text, bounds.x + bounds.width / 2
                - DrawUtils.getMessageWidth(text, font, g) / 2,
                bounds.y + bounds.height / 2
                + DrawUtils.getMessageHeight(text, font, g) / 2);

    }

    // Overridden methods
    /**
     * sets state to PRESSED
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if (bounds.contains(e.getPoint())) {
            currentState = GuiState.PRESSED;
        }

    }

    /**
     * no need to do anything here as the pressed state and released state are
     * taken care of
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (bounds.contains(e.getPoint())) {
            for (ActionListener action : actionListeners) {
                action.actionPerformed(null);
            }
        }
    }

    /**
     * recognizes when the mouse is pressed and when it is not and sets the
     * states accordingly
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (bounds.contains(e.getPoint())) {
            currentState = GuiState.PRESSED;
        } else {
            currentState = GuiState.RELEASED;
        }

    }

    /**
     * recognizes when the mouse is moved and sets the states accordingly
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (bounds.contains(e.getPoint())) {
            currentState = GuiState.HOVER;
        } else {
            currentState = GuiState.RELEASED;
        }
    }

    /**
     * @return the isRoundCorner
     */
    public boolean isRoundCorner() {
        return isRoundCorner;
    }

    /**
     * @param isRoundCorner the isRoundCorner to set
     */
    public void setIsRoundCorner(boolean isRoundCorner) {
        this.isRoundCorner = isRoundCorner;
    }

}
