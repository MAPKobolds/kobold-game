package org.uniba.kobold.guiRef;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Minecraft.ttf"));
                customFont = customFont.deriveFont(Font.PLAIN, 20);
                this.setFont(customFont);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            setForeground(Color.WHITE);
            setBackground( new Color(40, 0, 5));
            setBorder(BorderFactory.createLineBorder(new Color(205,164,62), 2));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * Constructor of the class GuiGenericButton
         * @param text the text of the button
         * @param bgColor the background color of the button
         * @param textColor the text color of the button
         */
        public GuiGenericButton(String text, Color bgColor, Color textColor) {
            super(text);
            setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            setBackground(bgColor);
            setForeground(textColor);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * Constructor of the class GuiGenericButton
         * @param text the text of the button
         * @param bgColor the background color of the button
         * @param textColor the text color of the button
         * @param size the size of the button
         */
        public GuiGenericButton(String text, Color bgColor, Color textColor, Dimension size) {
            super(text);
            setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            setBackground(bgColor);
            setForeground(textColor);
            setPreferredSize(size);
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
