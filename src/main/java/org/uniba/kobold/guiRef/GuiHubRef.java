package org.uniba.kobold.guiRef;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.CardLayout;
import java.net.URL;
import org.uniba.kobold.guiRef.GuiMenuRef;

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
        //JFrame Settings
        setTitle("Cerignolus: Città dei Coboldi");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        //Logo Settings
        URL logo = GuiHubRef.class.getResource("/img/BR.png");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);

        //Add cards to the frame and starts the music
        masterPanel = addMenu();
        add(masterPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        UtilMusic music = UtilMusic.getInstance();
        //music.start();
    }

    private JPanel addMenu() {
        JPanel cards = new JPanel(new CardLayout());

        GuiMenuRef menuRef = new GuiMenuRef();
        cards.add(menuRef, "menu");
        return cards;
    }
}
