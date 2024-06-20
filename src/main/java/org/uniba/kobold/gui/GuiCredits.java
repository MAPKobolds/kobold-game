package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class GuiCredits extends JPanel {

    private JPanel backgroundPanel;
    private JButton menuButton;
    private JLabel creditsText;
    private JPanel sgaramellaPanel;
    private JPanel porcelliPanel;
    private JPanel zippoPanel;
    private JToggleButton muteMusicButton;

    public GuiCredits() {
        initComponents();
    }

    private void initComponents() {
        menuButton = new JButton();
        creditsText = new JLabel();
        muteMusicButton = new JToggleButton();

        //BACKGROUND SETTINGS
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

        /**
         * Mute music button logic and clip management
         */
        muteMusicButton.setBackground(new java.awt.Color(204, 204, 204));
        muteMusicButton.setText("\uD83D\uDD0A");
        muteMusicButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                //Silence
                muteMusicButton.setText("\uD83D\uDD07");
                UtilMusic.getInstance().setMuted(true);
            } else {
                muteMusicButton.setText("\uD83D\uDD0A");
                UtilMusic.getInstance().setMuted(false);
            }
        });

        backgroundPanel.setMinimumSize(new Dimension(800, 600));
        backgroundPanel.setPreferredSize(new Dimension(800, 600));
        backgroundPanel.setRequestFocusEnabled(false);

        //BUTTON SETTINGS
        menuButton.setBackground(new java.awt.Color(204, 204, 204));
        menuButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuButton.setText("Torna al Menu");
        menuButton.addActionListener(e -> {
            CardLayout menu = (CardLayout) getParent().getLayout();
            menu.show(getParent(), "MenuPanel");
        });


        zippoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/ZippoToken.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        zippoPanel.setMinimumSize(new Dimension(150, 150));
        zippoPanel.setPreferredSize(new Dimension(150, 150));


        GroupLayout layoutZippo = new GroupLayout(zippoPanel);
        zippoPanel.setLayout(layoutZippo);
        layoutZippo.setHorizontalGroup(
                layoutZippo.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        layoutZippo.setVerticalGroup(
                layoutZippo.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        sgaramellaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/SgaramellaToken.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        sgaramellaPanel.setMinimumSize(new Dimension(150, 150));
        sgaramellaPanel.setPreferredSize(new Dimension(150, 150));

        GroupLayout layoutSgaramella = new GroupLayout(sgaramellaPanel);
        sgaramellaPanel.setLayout(layoutSgaramella);
        layoutSgaramella.setHorizontalGroup(
                layoutSgaramella.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        layoutSgaramella.setVerticalGroup(
                layoutSgaramella.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );


        porcelliPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/PorcelliToken.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        porcelliPanel.setMinimumSize(new Dimension(150, 150));
        porcelliPanel.setPreferredSize(new Dimension(150, 150));

        GroupLayout layoutPorcelli = new GroupLayout(porcelliPanel);
        porcelliPanel.setLayout(layoutPorcelli);
        layoutPorcelli.setHorizontalGroup(
                layoutPorcelli.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        layoutPorcelli.setVerticalGroup(
                layoutPorcelli.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        creditsText.setText("Ci siamo impegnati molto porco dio");
        GroupLayout backgroundLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(sgaramellaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(menuButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(porcelliPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(59, 59, 59)
                                                .addComponent(zippoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGap(127, 127, 127)
                                                .addComponent(creditsText, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(87, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(sgaramellaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(zippoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(porcelliPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addComponent(creditsText, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
        );
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

