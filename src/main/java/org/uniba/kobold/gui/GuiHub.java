package org.uniba.kobold.gui;

import org.uniba.kobold.util.Deserializer;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.CardLayout;
import java.net.URL;

/**
 * Main hub of the game
 */
public class GuiHub extends JFrame {

    /**
     * Attributes of the GuiHub class
     */
    private final static int width = 1000;
    private final static int height = 700;
    public static JPanel cards;

    /**
     * Constructor of the class GuiHub
     */
    public GuiHub() {

        try {
            Deserializer.loadInstancesFromJSON();
        } catch (Exception e) {
            System.out.println("Error during JSON reading");
        }
        //JFrame Settings
        setTitle("Cerignolus: Città dei Coboldi");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        //Logo Settings
        URL logo = GuiMenu.class.getResource("/img/BR.png");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);

        //Add cards to the frame and starts the music
        cards = getCards();
        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        UtilMusic music = UtilMusic.getInstance();
        //music.start();
    }

    private static JPanel getCards() {

        JPanel cards = new JPanel(new CardLayout());
        GuiMenu menu = new GuiMenu();
        GuiCredits credits = new GuiCredits();
        GuiLoadGame loadGame = new GuiLoadGame();
        GuiLoadingScreen loadingScreen = new GuiLoadingScreen();

        //Card layout management
        cards.add(menu, "Menu");
        cards.add(credits, "Credits");
        cards.add(loadGame, "SaveInstances");
        cards.add(loadingScreen, "LoadingScreen");
        return cards;
    }
}
