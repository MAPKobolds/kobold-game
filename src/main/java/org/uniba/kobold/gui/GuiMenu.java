package org.uniba.kobold.gui;

import org.uniba.kobold.util.SaveInstance;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import org.uniba.kobold.game.Game;

/**
 * Class GuiMenu
 */
public class GuiMenu extends GuiAbstractPanel {

    /**
     * Attributes of the MainMenu class
     */
    private static final String bgURL = "/img/pporc.png";
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel(bgURL);
    private static final JToggleButton muteMusicButton = new JToggleButton();
    private static JButton gameStartButton;
    private static JButton loadGameButton;
    private static JButton creditsButton;
    private static JButton exitButton;

    /**
     * Constructor of the class GuiMenu
     */
   public GuiMenu() {
        panelManager();
       addComponentListener(new ComponentAdapter() {
           @Override
           public void componentShown(ComponentEvent e) {
               UtilMusic.initButton(muteMusicButton);
           }
       });
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    @Override
    public void initComponents() {

        //Panel specific settings
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setSize(new Dimension(getWidth(), getHeight()));
        setLayout(null);

        //Buttons initialization
        gameStartButton = new GuiGenericButton(
            "Inizia Partita",
            new Color(40, 0, 5),
            Color.WHITE,
            new Dimension(800, 100)
        ).getButton();

        loadGameButton = new GuiGenericButton(
                "Carica Partita",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(800, 100)
        ).getButton();

        creditsButton = new GuiGenericButton(
                "Riconoscimenti",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(800, 100)
        ).getButton();

        exitButton = new GuiGenericButton(
                "Esci",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(800, 100)
        ).getButton();

        //Mute music button logic and clip management
        muteMusicButton.setBounds(0, 0, 50, 50);
        UtilMusic.initButton(muteMusicButton);

        //gameStartButton logic
        gameStartButton.addActionListener(_ -> {
            JTextField playerInput = new JTextField();
            Object[] message = {
                    "Inserisci il nome del personaggio:", playerInput
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Nome Personaggio", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String playerName = playerInput.getText();
                if (playerName != null && !playerName.trim().isEmpty() && isPlayerNew(playerInput.getText())) {
                    Game.setPlayerName(playerName);
                    CardLayout loadingScreen = (CardLayout) getParent().getLayout();
                    loadingScreen.show(getParent(), "LoadingScreen");
                } else {
                    JOptionPane.showMessageDialog(null, "Nome non valido o gia' occupato.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nuova partita annullata.", "Annullato", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //loadGameButton logic
        loadGameButton.addActionListener(_ -> {
            GuiLoadGame load = new GuiLoadGame();
            //GuiHub.cards.add(load, "SaveInstances");
            CardLayout loadGame = (CardLayout) getParent().getLayout();
            loadGame.show(getParent(), "SaveInstances");
        });

        //CreditsButton logic
        creditsButton.addActionListener(_ -> {
            CardLayout credits = (CardLayout) getParent().getLayout();
            credits.show(getParent(), "Credits");
        });

        //Exit button logic
        exitButton.addActionListener(_ -> System.exit(0));

        //Adding components to the panel
        add(muteMusicButton);
        add(gameStartButton);
        add(loadGameButton);
        add(creditsButton);
        add(exitButton);

        //Background panel management
        super.manageBackgroundLayout(this, backgroundPanel);
    }

    @Override
    public void initComponents(int width, int height) {
    }

    /**
     * Method to check if the player is new
     * @param name the name of the player
     * @return true if the player is new, false otherwise
     */
    private boolean isPlayerNew(String name) {
        for(SaveInstance save : SaveInstance.getInstances()) {
            String saveName = save.getSaveName();
            saveName = saveName.substring(0, saveName.indexOf(" - "));
            if(saveName.matches(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to update the position of the components
     */
    @Override
     public void updateLayout() {
        int width = getWidth();
        int height = getHeight();
        int buttonHeight = 50 * height / 600;
        int buttonWidth = 50 * height / 600;
        double widthOffset = width * 0.10;
        double heightOffset = height * 0.55;
        int offset = 70 * height / 600;

        //This is where the magic happens
        gameStartButton.setBounds((int) widthOffset, (int) heightOffset, (int) (width * 0.80), buttonHeight);
        loadGameButton.setBounds((int) widthOffset, (int) heightOffset + offset, (int) (width * 0.80), buttonHeight);
        creditsButton.setBounds((int) widthOffset, (int) heightOffset + (offset * 2), (int) (width * 0.80), buttonHeight);
        exitButton.setBounds((int) widthOffset, (int) heightOffset + (offset * 3), (int) (width * 0.80), buttonHeight);
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
    }
}
