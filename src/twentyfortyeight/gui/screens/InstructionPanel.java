package twentyfortyeight.gui.screens;

// reqruired imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import twentyfortyeight.Game;
import twentyfortyeight.gui.GuiButton;
import twentyfortyeight.gui.GuiScreen;

/**
 * The Instruction Panel is a GUI panel and it contains the instructions for the
 * game
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class InstructionPanel extends GuiPanel {

    // properties for text and button
    private Font font = Game.FONT.deriveFont(Font.BOLD, 12);
    private final int SPACE = 32;
    private static int buttonWidth = 150;
    private static int buttonHeight = 75;

    public InstructionPanel() {

        int centerX = Game.SCREEN_WIDTH / 2 - 100;

        // The menu button
        GuiButton back = new GuiButton(centerX + 75,
                Game.SCREEN_HEIGHT - buttonHeight - 50,
                buttonWidth, buttonHeight);

        // Text "Menu" on button
        back.setText("Menu");

        // goes back to menu screen
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GuiScreen.getInstance().setCurrentPanel(GuiScreen.MENU_SCREEN);
            }
        });

        // adds the button to the frame
        add(back);

    }

    // Overridden method
    /**
     * Renders text and frame inside Instruction Panel
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

        // Instructions text (Spaced because they are drawn and not displayed)
        g.setFont(font);
        g.setColor(Color.gray); // gray color
        g.drawString("Welcome to 2048, here are some instructions on how ",
                Game.SCREEN_WIDTH / 2 - 200, 100);
        g.drawString("to play the game: ",
                Game.SCREEN_WIDTH / 2 - 200, 100 + SPACE);
        g.drawString("Press the UP arrow key to move and add boxes up",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 3 * SPACE);
        g.drawString("Press the DOWN arrow key to move and add boxes down",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 4 * SPACE);
        g.drawString("Press the LEFT arrow key to move and add boxes left",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 5 * SPACE);
        g.drawString("Press the RIGHT arrow key to move and add boxes right",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 6 * SPACE);
        g.drawString("You may change the grid size by navigating to the survival mode",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 8 * SPACE);
        g.drawString("settings and select the desired matrix",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 9 * SPACE);
        g.setFont(Game.FONT.deriveFont(Font.BOLD, 18));
        g.setColor(Color.red);
        g.drawString("TRY AND BEAT 2048!!!!",
                Game.SCREEN_WIDTH / 2 - 200, 100 + 11 * SPACE);
    }

}
