package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class GuiGenericButton
 */
public class GuiGenericButton extends JButton {

        /**
         * Constructor of the class GuiGenericButton
         * @param text the text of the button
         */
        public GuiGenericButton(String text) {
            super(text);
            setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            setBackground(new java.awt.Color(204, 204, 204));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public GuiGenericButton(String text, Color bgColor, Color textColor) {
            super(text);
            setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            setBackground(bgColor);
            setForeground(textColor);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public GuiGenericButton(String text, Color bgColor, Color textColor, Dimension size) {
            super(text);
            setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            setBackground(bgColor);
            setForeground(textColor);
            setPreferredSize(size);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * Method to get the button
         * @return the button
         */
        public JButton getButton() {
            return this;
        }
}
