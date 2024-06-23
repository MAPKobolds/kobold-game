package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class GuiBackgroundPanel
 */
public class GuiBackgroundPanel extends JPanel {

    /**
     * Attributes of the class GuiBackgroundPanel
     */
    private final int width = 800;
    private final int height = 600;
    private final String backgroundPath = "/img/pporc.png";

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiBackgroundPanel() {
        initComponents();
    }

    /**
     * Method to initialize the components of the class GuiBackgroundPanel
     */
    private void initComponents() {
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setRequestFocusEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(backgroundPath)));
        Image image = img.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Method to manage the layout of the background panel
     *
     * @param parent          the parent panel
     * @param backgroundPanel the background panel
     */
    public static void manageBackgroundLayout(JPanel parent, JPanel backgroundPanel) {
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
}
