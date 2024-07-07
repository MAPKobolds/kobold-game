package org.uniba.kobold.guiRef;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiObjectButton extends JButton{

    /**
     * Attributes of the class GuiObjectButton
     */
    private String objectImagePath;

    /**
     * Constructor of the class GuiObjectButton
     * @param name the name of the button
     * @param objectImagePath the path of the image
     */
    public GuiObjectButton(String name, String objectImagePath) {
        super(name);
        this.objectImagePath = objectImagePath;
        setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     *  Method to paint an image on the button
     * @param g the graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(objectImagePath)));
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Method to get the button
     * @return the button
     */
    public JButton getButton() {
        return this;
    }

    public void updateImage(String objectImagePath) {
        this.objectImagePath = objectImagePath;
        repaint();
    }
}