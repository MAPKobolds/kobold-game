package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;

/**
 * Class GuiMenu
 */
public class GuiMenu extends JPanel {

    /**
     * Attributes of the MainMenu class
     */
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel();
    private static final int width = 800;
    private static final int height = 600;
    private static JButton gameStartButton;
    private static JButton loadGameButton;
    private static JButton creditsButton;
    private static JButton exitButton;
    private static JToggleButton muteMusicButton;

    /**
     * Constructor of the class GuiMenu
     */
    public GuiMenu() {
        initComponents();
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    private void initComponents() {
        gameStartButton = new GuiGenericButton("Inizia Partita").getButton();
        loadGameButton = new GuiGenericButton("Carica Partita").getButton();
        creditsButton = new GuiGenericButton("Riconoscimenti").getButton();
        exitButton = new GuiGenericButton("Esci").getButton();
        muteMusicButton = new JToggleButton();

        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        //gameStartButton logic
        gameStartButton.addActionListener(e -> {
            CardLayout loadingScreen = (CardLayout) getParent().getLayout();
            loadingScreen.show(getParent(), "LoadingScreen");
        });

        //loadGameButton logic
        loadGameButton.addActionListener(e -> {
            CardLayout loadGame = (CardLayout) getParent().getLayout();
            loadGame.show(getParent(), "SaveInstances");
        });

        //CreditsButton logic
        creditsButton.addActionListener(e -> {
            CardLayout credits = (CardLayout) getParent().getLayout();
            credits.show(getParent(), "Credits");
        });

        //Exit button logic
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        //Mute music button logic and clip management
        UtilMusic.initButton(muteMusicButton);
        UtilMusic.manageButton(muteMusicButton);

        //Layout Settings
        GroupLayout backgroundLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                .addContainerGap(491, Short.MAX_VALUE)
                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(creditsButton, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                                        .addComponent(loadGameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(gameStartButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(169, 169, 169))
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                .addGap(179, 179, 179))))
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199)
                                .addComponent(gameStartButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(loadGameButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(creditsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(88, Short.MAX_VALUE))
        );

        //Background group layout manager
        backgroundPanel.setLayout(backgroundLayout);
        GuiBackgroundPanel.manageBackgroundLayout(this, backgroundPanel);
    }
}
