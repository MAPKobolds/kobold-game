package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class GuiLoadingScreen
 */
public class GuiLoadingScreen extends JPanel {

    /**
     * Attributes of the class GuiLoadingScreen
     */
    private JProgressBar progressBar;
    private JToggleButton muteMusicButton;
    private final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel();

    /**
     * Constructor of the class GuiLoadingScreen
     */
    public GuiLoadingScreen() {
        initComponents();
    }

    /**
     * Method to initialize the components of the class GuiLoadingScreen
     */
    private void initComponents() {
        progressBar = new JProgressBar(0, 100);
        muteMusicButton = new JToggleButton();

        //Progress Bar Settings
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setBounds(100, 500, 600, 50);
        progressBar.setFont(new Font("Arial", Font.BOLD, 20));
        progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        progressBar.setForeground(Color.MAGENTA);
        progressBar.setBackground(Color.BLACK);
        backgroundPanel.add(progressBar);

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
                            Thread.sleep(20);
                        }
                        progressBar.setString("Kobold is ready!");
                        Thread.sleep(1000);
                        SwingUtilities.invokeLater(() -> {
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

        //Mute music button logic and clip management
        UtilMusic.initButton(muteMusicButton);
        UtilMusic.manageButton(muteMusicButton);

        //Layout Settings
        GroupLayout backgroundLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199)
        ));

        //Background group layout manager
        backgroundPanel.setLayout(backgroundLayout);
        GuiBackgroundPanel.manageBackgroundLayout(this, backgroundPanel);
    }
}
