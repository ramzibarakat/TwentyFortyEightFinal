package twentyfortyeight.gui.screens;

// required imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import twentyfortyeight.DrawUtils;
import twentyfortyeight.Game;
import twentyfortyeight.gui.GuiButton;
import twentyfortyeight.gui.GuiScreen;

/**
 * The Main Menu Panel is a GUI panel and it contains all the buttons from the
 * main menu
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class MainMenuPanel extends GuiPanel {

    // title font
    private Font titleFont = Game.FONT.deriveFont(Font.BOLD, 100);

    // title "2048"
    private String title = "2048";

    // button dimensions and spacing
    private static int buttonWidth = 220;
    private static int buttonHeight = 100;
    private static int verticalSpacing = 50;

    // default constructor
    public MainMenuPanel() {

        // the center x coordinate
        int centerX = Game.SCREEN_WIDTH / 2 - buttonWidth / 2;

        // sets the position and dimensions for the select mode button
        GuiButton selectModeBtn = new GuiButton(centerX,
                Game.SCREEN_HEIGHT / 2 - buttonHeight / 2 - 100,
                buttonWidth, buttonHeight);

        // sets the position and dimensions for the instructions button
        GuiButton instrcBtn = new GuiButton(centerX,
                selectModeBtn.getY() + buttonHeight + verticalSpacing,
                buttonWidth, buttonHeight);

        // sets the position and dimensions for the quit button
        GuiButton quitBtn = new GuiButton(centerX,
                instrcBtn.getY() + buttonHeight + verticalSpacing,
                buttonWidth, buttonHeight);

        // titles of buttons
        selectModeBtn.setText("Select Mode");
        instrcBtn.setText("Instructions");
        quitBtn.setText("Quit");

        // Overridden methods
        // goes to select mode screen
        selectModeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.SELECT_MODE_SCREEN);
            }
        });

        // goes to instruction screen
        instrcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.INSTRUCTIONS_SCREEN);
            }
        });

        // exits the program
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // add  the buttons in the main menu
        add(selectModeBtn);
        add(instrcBtn);
        add(quitBtn);

    }

    /**
     * Renders menu screen graphics
     *
     * @param g default graphics object
     */
    @Override
    public void render(Graphics2D g) {

        // backgorund render first
        g.setColor(new Color(230, 210, 200));
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // render buttons 
        super.render(g);

        g.setFont(titleFont);
        g.setColor(Color.gray);
        g.drawString(title,
                Game.SCREEN_WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2,
                DrawUtils.getMessageHeight(title, titleFont, g) / 2 + 50);

    }

}
