package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

    /**
     * Constructor of the class GuiLoadingScreen
     */
    public GuiLoadingScreen() {
        panelManager();
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

        //Layout Settings
        /*GroupLayout backgroundLayout = new GroupLayout(backgroundPanel);
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
        backgroundPanel.setLayout(backgroundLayout);*/
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
        backgroundPanel.setBounds(0, 0, width, height);
        progressBar.setBounds((int) widthOffset, (int) heightOffset, (int) (width * 0.80), (int) (height * 0.10));
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
    }
}
