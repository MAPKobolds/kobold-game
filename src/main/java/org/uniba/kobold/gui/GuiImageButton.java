package org.uniba.kobold.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Gui image button.
 */
public class GuiImageButton extends JButton {
    private BufferedImage backgroundImage;

    /**
     * Instantiates a new Gui image button.
     *
     * @param imagePath the image path
     * @param text      the text
     */
    public GuiImageButton(String imagePath, String text) {
        super(text);
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g);
    }

    /**
     * Gets button.
     *
     * @return the button
     */
    public JButton getButton() {
        return this;
    }
}