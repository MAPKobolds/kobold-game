package org.uniba.kobold.gui;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.CardLayout;
import java.net.URL;

/**
 * Main Frame of the game
 */
public class HubFrame extends JFrame {

    public HubFrame() {
        //JFRAME SETTINGS
        setTitle("Cerignolus: Città dei Coboldi");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //LOGO SETTINGS
        URL logo = GuiMenu.class.getResource("/img/BR.png");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);

        // CARDS LAYOUT
        JPanel cards = new JPanel(new CardLayout());
        GuiMenu menu = new GuiMenu();
        GuiCredits credits = new GuiCredits();

        // Add the panels to cards
        cards.add(menu, "MenuPanel");
        cards.add(credits, "CreditsPanel");

        // Start the frame
        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        /**
         * Start the music
         */
        UtilMusic music = UtilMusic.getInstance();
        music.start();
    }
}