package org.uniba.kobold.guiRef;

import org.uniba.kobold.gui.GuiGenericButton;

import javax.swing.*;

/**
 * Class GuiMenu
 */
public class GuiMenuRef extends JPanel {

    /**
     * Attributes of the MainMenu class
     */
    private static final String bgURL = "/img/pporc.png";
    private static final JToggleButton muteMusicButton = new JToggleButton();
    private static JButton gameStartButton;
    private static JButton loadGameButton;
    private static JButton creditsButton;
    private static JButton exitButton;

    /**
     * Constructor of the class GuiMenu
     */
    public GuiMenuRef() {
        initComponents();
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    public void initComponents() {
        gameStartButton = new GuiGenericButton("Ciao");

        gameStartButton.setVisible(true);
        gameStartButton.setBounds(0,0, 400, 400);

        add(gameStartButton);

    }

}
