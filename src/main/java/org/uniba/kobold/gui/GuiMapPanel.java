package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiMapPanel extends GuiAbstractPanel {

    /**
     * Attributes of the class GuiBackgroundPanel
     */
    private final int width = 200;
    private final int height = 120;
    private String backgroundPath;

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiMapPanel(String backgroundPath) {
        panelManager();
        this.backgroundPath = backgroundPath;
    }

    /**
     * Method to initialize the components of the class GuiBackgroundPanel
     */
    @Override
    public void initComponents() {
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setRequestFocusEnabled(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Method to paint the background image
     * @param g the graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(backgroundPath)));
        Image image = backgroundImage.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Method to update the background image
     * @param newBackgroundPath the path to the new background image
     */
    public void updateBackground(String newBackgroundPath) {
        this.backgroundPath = newBackgroundPath;
        revalidate();
        repaint();
    }
}