package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class GuiMenu
 */
public class GuiMenu extends JPanel {

    /**
     * Attributes of the MainMenu class
     */
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel();
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

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateButtonPositions();
            }
        });
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

        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setSize(new Dimension(getWidth(), getHeight()));

        //Mute music button logic and clip management
        UtilMusic.initButton(muteMusicButton);

        //gameStartButton logic
        gameStartButton.addActionListener(_ -> {
            CardLayout loadingScreen = (CardLayout) getParent().getLayout();
            loadingScreen.show(getParent(), "LoadingScreen");
        });

        //loadGameButton logic
        loadGameButton.addActionListener(_ -> {
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

    /**
     * Method to update the position of the buttons
     */
    private void updateButtonPositions() {
        int width = getWidth();
        int height = getHeight();
        double widthOffset = width * 0.75;
        double heightOffset = height * 0.25;
        int offset = 50;

        gameStartButton.setBounds((int) widthOffset, (int) heightOffset, 140, 40);
        loadGameButton.setBounds((int) widthOffset , (int) heightOffset + offset, 140, 40);
        creditsButton.setBounds((int) widthOffset, (int) heightOffset + (offset * 2), 140, 40);
        exitButton.setBounds((int) widthOffset, (int) heightOffset + (offset * 3), 120, 40);
    }
}
