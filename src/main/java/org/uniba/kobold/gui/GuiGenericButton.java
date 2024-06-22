package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;

public class GuiGenericButton extends JButton {

        /**
         * Constructor of the class GuiGenericButton
         * @param text the text of the button
         */
        public GuiGenericButton(String text) {
            super(text);
            setFont(new java.awt.Font("Arial", 0, 14));
            setBackground(new java.awt.Color(204, 204, 204));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        /**
         * Method to get the button
         * @return the button
         */
        public JButton getButton() {
            return this;
        }
}
