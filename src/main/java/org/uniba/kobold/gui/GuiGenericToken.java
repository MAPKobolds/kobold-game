package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiGenericToken {

    /**
     * Attributes of the class GuiGenericToken
     */
    private JPanel tokenPanel;

    /**
     * Constructor of the class GuiGenericToken
     */
    public GuiGenericToken(String tokenPath, int tokenSize) {
        tokenPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
                ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(tokenPath)));
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        tokenPanel.setOpaque(false);
        tokenPanel.setBounds(0, 0, tokenSize, tokenSize);
    }

    /**
     * Method to manage the layout of the token panels
     * @param tokenPanel the token panel
     */
    public static void manageTokenLayout(JPanel tokenPanel, int tokenSize) {
        GroupLayout layoutZippo = new GroupLayout(tokenPanel);
        tokenPanel.setLayout(layoutZippo);
        layoutZippo.setHorizontalGroup(
                layoutZippo.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, tokenSize, Short.MAX_VALUE)
        );
        layoutZippo.setVerticalGroup(
                layoutZippo.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, tokenSize, Short.MAX_VALUE)
        );
    }

    /**
     * Method to get the token panel
     * @return the token panel
     */
    public JPanel getToken() {
        return tokenPanel;
    }
}
