package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;

/**
 * Class GuiLoadingScreen
 */
public class GuiLoadingScreen extends JPanel {

    /**
     * Attributes of the class GuiLoadingScreen
     */
    private JProgressBar progressBar;
    private JToggleButton muteMusicButton;
    private JPanel backgroundPanel;
    private int height = 600;

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

        //Background Settings
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

        //Progress Bar Settings
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setBounds(100, 500, height, 50);
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
                            Thread.sleep(50);
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
