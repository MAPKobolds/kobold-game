package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class GuiBackgroundPanel
 */
public class GuiBackgroundPanel extends JPanel {

    /**
     * Attributes of the class GuiBackgroundPanel
     */
    private int width = 800;
    private int height = 600;

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public void GuiBackgroundPanel() {
        initComponents();
    }

    /**
     * Method to initialize the components of the class GuiBackgroundPanel
     */
    private void initComponents() {
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setRequestFocusEnabled(false);
        paintComponent(getGraphics());
    }
        /**
     * Constructor of the class GuiBackgroundPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/main/resources/img/pporc.png");
        Image image = img.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
