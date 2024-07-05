package org.uniba.kobold.guiRef;

import org.uniba.kobold.gui.GuiMenu;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Main hub of the game
 */
public class GuiHubRef extends JFrame {

    /**
     * Attributes of the GuiHub class
     */
    private final static int width = 1000;
    private final static int height = 700;
    public static JPanel masterPanel = new JPanel(new CardLayout());

    /**
     * Constructor of the class GuiHub
     */
    public GuiHubRef() {
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

        setLocationRelativeTo(null);
        setVisible(true);

        masterPanel = addMenu();
        add(masterPanel);

        UtilMusic music = UtilMusic.getInstance();
        //music.start();
    }

    private JPanel addMenu() {
        JPanel cards = new JPanel(new CardLayout());
        cards.add(new GuiHubRef(), "menu");

        return cards;
    }
}
