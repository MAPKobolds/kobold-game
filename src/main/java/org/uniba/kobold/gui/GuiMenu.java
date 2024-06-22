package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Class GuiMenu
 */
public class GuiMenu extends JPanel {

    /**
     * Attributes of the MainMenu class
     */
    private JButton gameStartButton;
    private JButton loadGameButton;
    private JButton creditsButton;
    private JButton exitButton;
    private JPanel backgroundPanel;
    private JToggleButton muteMusicButton;
    private int width = 800;
    private int height = 600;

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
        backgroundPanel = new JPanel();
        gameStartButton = new JButton();
        loadGameButton = new JButton();
        creditsButton = new JButton();
        exitButton = new JButton();
        muteMusicButton = new JToggleButton();

        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        //backgroundPanel settings
        backgroundPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/pporc.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setMinimumSize(new Dimension(width, height));
        backgroundPanel.setPreferredSize(new Dimension(width, height));
        backgroundPanel.setRequestFocusEnabled(false);

        //gameStartButton logic and redirect to gamePanel
        gameStartButton.setBackground(new java.awt.Color(204, 204, 204));
        gameStartButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gameStartButton.setText("Nuova Partita");
        gameStartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        gameStartButton.addActionListener(e -> {
            CardLayout loadingScreen = (CardLayout) getParent().getLayout();
            loadingScreen.show(getParent(), "LoadingScreen");

        });

        //loadGameButton logic and redirect to loadGamePanel
        loadGameButton.setBackground(new java.awt.Color(204, 204, 204));
        loadGameButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loadGameButton.setText("Carica Partita");
        loadGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        loadGameButton.addActionListener(e -> {
            CardLayout loadGame = (CardLayout) getParent().getLayout();
            loadGame.show(getParent(), "SaveInstances");
        });

        //CreditsButton logic and redirect to creditsPanel
        creditsButton.setBackground(new java.awt.Color(204, 204, 204));
        creditsButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        creditsButton.setText("Riconoscimenti");
        creditsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        creditsButton.addActionListener(e -> {
            CardLayout credits = (CardLayout) getParent().getLayout();
            credits.show(getParent(), "Credits");
        });

        //Exit button logic
        exitButton.setBackground(new java.awt.Color(204, 204, 204));
        exitButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        exitButton.setText("Esci");
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        //Mute music button logic and clip management
        muteMusicButton.setBackground(new java.awt.Color(204, 204, 204));
        muteMusicButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        UtilMusic.setOnText(muteMusicButton);
        muteMusicButton.addItemListener(e -> {
            UtilMusic.getInstance().setMuted(e.getStateChange() == ItemEvent.SELECTED, muteMusicButton);
        });

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

        //GroupLayout settings for background panel
        backgroundPanel.setLayout(backgroundLayout);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}
