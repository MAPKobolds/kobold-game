package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class GuiGame
 */
public class GuiGame extends JPanel {

    /**
     * Attributes of the GuiGame class
     */
    private int width = 800;
    private int height = 600;

    /**
     * Constructor of the GuiGame class
     */
    public GuiGame() {
        initComponents();
    }

    /**
     * Method to initialize the components of the GuiGame class
     */
    private void initComponents() {
        setBackground(Color.BLACK);
    }
}
