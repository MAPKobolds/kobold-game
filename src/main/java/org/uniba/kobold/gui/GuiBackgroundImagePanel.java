package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The type Gui background image panel.
 */
public class GuiBackgroundImagePanel extends JTextPane {
    private BufferedImage backgroundImage;

    /**
     * Instantiates a new Gui background image panel.
     *
     * @param imagePath the image path
     */
    public GuiBackgroundImagePanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}