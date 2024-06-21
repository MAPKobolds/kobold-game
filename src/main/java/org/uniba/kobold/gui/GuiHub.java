package org.uniba.kobold.gui;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.CardLayout;
import java.net.URL;

/**
 * Main hub of the game
 */
public class GuiHub extends JFrame {

    public GuiHub() {
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
        GuiLoadGame loadGame = new GuiLoadGame();

        // Add the panels to cards
        cards.add(menu, "MenuPanel");
        cards.add(credits, "CreditsPanel");
        cards.add(loadGame, "SaveInstancesPanel");

        // Start the frame
        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        UtilMusic music = UtilMusic.getInstance();
        music.start();
    }
}