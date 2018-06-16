package twentyfortyeight.gui;

// required imports
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import twentyfortyeight.Game;

/**
 * Abstract class used for GUI component
 * 
 * @since 15/06/2018
 * @author r.barakat
 */
public abstract class GuiComponent {

  // protected variables
  protected Rectangle bounds;
  protected ArrayList<ActionListener> actionListeners;
  protected String text = "";
  protected Font font;
  private boolean isVisible = true;
  
  // default constructor
  public GuiComponent(int x, int y, int width, int height) {

    // creates a rectangle and sets the font
    bounds = new Rectangle(x, y, width, height);
    font = Game.FONT.deriveFont(Game.FONT_SIZE_SMALL);
    
    // creates a list of action listeners
    actionListeners = new ArrayList<>();

  }

  
  // Abstract methods
  
  public abstract void update();

  public abstract void render(Graphics2D g);

  public abstract void mousePressed(MouseEvent e);

  public abstract void mouseReleased(MouseEvent e);

  public abstract void mouseDragged(MouseEvent e);

  public abstract void mouseMoved(MouseEvent e);
  /**
   * Add another action listener
   * @param listener the type of action listener
   */
  public void addActionListener(ActionListener listener) {
    actionListeners.add(listener);
  }

  // the x of GUI component
  public int getX() {
    return getBounds().x;
  }

  // the y of GUI component
  public int getY() {
    return getBounds().y;
  }

  // the width of GUI component
  public int getWidth() {
    return getBounds().width;
  }

  // the height of GUI component
  public int getHeight() {
    return getBounds().height;
  }

  // the text of GUI component
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the bounds
   */
  public Rectangle getBounds() {
    return bounds;
  }

  /**
   * @param bounds the bounds to set
   */
  public void setBounds(Rectangle bounds) {
    this.bounds = bounds;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @return the font
   */
  public Font getFont() {
    return font;
  }

  /**
   * @param font the font to set
   */
  public void setFont(Font font) {
    this.font = font;
  }

    /**
     * @return the isVisible
     */
    public boolean isIsVisible() {
        return isVisible;
    }

    /**
     * @param isVisible the isVisible to set
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

  
  
}
