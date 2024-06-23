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
    private final int width = 800;
    private final int height = 600;

    /**
     * Constructor of the GuiGame class
     */
    public GuiGame() {
        initComponents();
    }

    /**
     * Method to initialize the components of the GuiGame class
     */
    public void initComponents() {
        setBackground(Color.BLACK);
    }

    public void manageBackgroundLayout(JPanel parent, JPanel backgroundPanel) {
        GroupLayout layout = new GroupLayout(parent);
        parent.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public void updateLayout() {
        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
    }
}
