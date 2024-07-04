package org.uniba.kobold.gui;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Class GuiLoadingScreen
 */
public class GuiLoadingScreen extends GuiAbstractPanel {

    /**
     * Attributes of the class GuiLoadingScreen
     */
    private final String bgURL = "/img/pporc.png";
    private JProgressBar progressBar;
    private final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel(bgURL);
    private final JToggleButton muteMusicButton = new JToggleButton();

    /**
     * Constructor of the class GuiLoadingScreen
     */
    public GuiLoadingScreen() {
        panelManager();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
        });
    }

    /**
     * Method to initialize the components of the class GuiLoadingScreen
     */
    @Override
    public void initComponents() {
        progressBar = new JProgressBar(0, 100);

        //Progress Bar Settings
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setFont(new Font("Arial", Font.BOLD, 20));
        progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        progressBar.setForeground(new Color(40, 0, 5));
        progressBar.setBackground(Color.BLACK);

        //muteMusicButton settings
        muteMusicButton.setBounds(0, 0, 50, 50);
        UtilMusic.initButton(muteMusicButton);
        add(muteMusicButton);

        //Progress bar thread logic and card layout management
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                Thread progressBarThread = new Thread(() -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            final int progress = i;
                            SwingUtilities.invokeLater(() -> {
                                progressBar.setValue(progress);
                                progressBar.setString("Loading..." + progress + "%");
                            });
                            Thread.sleep(10);
                        }
                        progressBar.setString("Kobold is ready!");
                        Thread.sleep(1000);
                        SwingUtilities.invokeLater(() -> {
                            try {
                                GugoGame guiGame = new GugoGame();
                                new Game();
                                getParent().add(guiGame, "Game");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            CardLayout menu = (CardLayout) getParent().getLayout();
                            menu.show(getParent(), "Game");
                        });
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted: " + ex.getMessage());
                    }
                });
                progressBarThread.start();
            }
        });

        super.manageBackgroundLayout(this, backgroundPanel);
    }

    /**
     * Method to update the layout of the class GuiLoadingScreen
     */
    @Override
    public void updateLayout() {
        int width = getWidth();
        int height = getHeight();
        int buttonHeight = 50 * height / 600;
        int buttonWidth = 50 * height / 600;
        double widthOffset = width * 0.10;
        double heightOffset = height * 0.85;

        //This is where the magic happens
        add(progressBar);
        backgroundPanel.setBounds(0, 0, width, height);
        progressBar.setBounds((int) widthOffset, (int) heightOffset, (int) (width * 0.80), (int) (height * 0.10));
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
    }
}
