package org.uniba.kobold.guiRef;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class GuiBackgroundPanel
 */
public class GuiBackgroundRef extends JPanel {

    /**
     * Attributes of the class GuiBackgroundPanel
     */
    private String backgroundPath;

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiBackgroundRef(String backgroundPath) {
        this.backgroundPath = backgroundPath;
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