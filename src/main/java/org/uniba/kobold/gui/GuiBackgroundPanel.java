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
    private final int width = 800;
    private final int height = 600;
    private final String backgroundPath;

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiBackgroundPanel(String backgroundPath) {
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
        setRequestFocusEnabled(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(backgroundPath)));
        Image image = img.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
