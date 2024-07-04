package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class GuiBackgroundPanel
 */
public class GuiBackgroundPanel extends GuiAbstractPanel {

    /**
     * Attributes of the class GuiBackgroundPanel
     */
    private String backgroundPath;

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiBackgroundPanel(String backgroundPath) {
       panelManager();
        this.backgroundPath = backgroundPath;
    }



    /**
     * Overloaded constructor of the class GuiBackgroundPanel
     */
    public GuiBackgroundPanel(String backgroundPath, int width, int height) {
        panelManager(width, height);
        this.backgroundPath = backgroundPath;
    }

    @Override
    public void initComponents() {
        int width = 1000;
        int height = 700;
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setRequestFocusEnabled(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void initComponents(int width, int height) {
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
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
