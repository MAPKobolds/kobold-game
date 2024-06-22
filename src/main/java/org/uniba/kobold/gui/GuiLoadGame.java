package org.uniba.kobold.gui;

import org.uniba.kobold.game.SaveInstance;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Class GuiLoadGame
 */
public class GuiLoadGame extends JPanel {

    /**
     * Attributes of the GuiLoadGame class
     */
    private JPanel backgroundPanel;
    private JPanel savesBGPanel;
    private JButton menuButton;
    private JToggleButton muteMusicButton;
    private int width = 800;
    private int height = 600;

    /**
     * Constructor for the GuiLoadGame class
     */
    public GuiLoadGame() {
        initComponents();
    }

    /**
     * Method to initialize the components of the GuiLoadGame class
     */
    private void initComponents() {

        menuButton = new JButton();
        muteMusicButton = new JToggleButton();

        // Create a JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));

        //backgroundPanel settings
        backgroundPanel = new JPanel(new BorderLayout()) {
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

        // Add the backgroundPanel to the JLayeredPane with a lower depth value
        backgroundPanel.setBounds(0, 0, width, height);

        //Mute music button logic and clip management
        muteMusicButton.setBackground(new java.awt.Color(204, 204, 204));
        if (UtilMusic.getInstance().isMuted()) {
            UtilMusic.setOffText(muteMusicButton);
        } else {
            UtilMusic.setOnText(muteMusicButton);
        }
        muteMusicButton.addItemListener(e -> UtilMusic.getInstance().setMuted(ItemEvent.SELECTED == e.getStateChange(), muteMusicButton));
        muteMusicButton.setBounds(0, 0, 50, 50);
        muteMusicButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        layeredPane.add(muteMusicButton, JLayeredPane.PALETTE_LAYER);

        //menuButton settings
        menuButton.setBackground(new java.awt.Color(204, 204, 204));
        menuButton.setFont(new java.awt.Font("Arial", 0, 14));
        menuButton.setBounds(340, 530, 150, 40);
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        menuButton.setText("Torna al Menu");
        menuButton.addActionListener(e -> {
            CardLayout menu = (CardLayout) getParent().getLayout();
            menu.show(getParent(), "Menu");
        });

        // Add the menuButton to the JLayeredPane with a higher depth value
        layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        //Mute music button logic and clip management and savesBGPanel settings
        savesBGPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/BR.png");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        savesBGPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        savesBGPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        savesBGPanel.setRequestFocusEnabled(false);

        //Temporaneo finché non avremo salvataggi veri
        for (int i = 0; i < 5; i++) {
            SaveInstance saveGame = new SaveInstance();
        }

        savesBGPanel.setLayout(new BoxLayout(savesBGPanel, BoxLayout.Y_AXIS));
        for (SaveInstance save : SaveInstance.getInstances()) {
            SaveInstancePanel saveInstancePanel = new SaveInstancePanel();
            saveInstancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            savesBGPanel.add(saveInstancePanel);
        }

        savesBGPanel.setBounds(120, 0, (int)(width * 0.85), (int)(height * 0.52));
        layeredPane.add(savesBGPanel, JLayeredPane.PALETTE_LAYER);

        // Add the JLayeredPane to the GuiLoadGame
        add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Inner class SaveInstancePanel
     */
    public class SaveInstancePanel extends JPanel {

        /**
         * Attributes of the SaveInstancePanel class
         */
        private JButton loadButton;
        private JLabel loadInfoLable;
        private int saveWidth = (int) (backgroundPanel.getWidth() * 0.85);
        private int saveHeight = (int) (backgroundPanel.getHeight() * 0.1);

        /**
         * Constructor for the SaveInstancePanel class
         */
        public SaveInstancePanel() {
            initComponents();
        }

        /**
         * Method to initialize the components of the SaveInstancePanel class
         */
        private void initComponents() {
            setMinimumSize(new Dimension(saveWidth, saveHeight));
            setMaximumSize(new Dimension(saveWidth, saveHeight));
            setBackground(new java.awt.Color(70,204,204));
            loadButton = new JButton();
            loadInfoLable = new JLabel();

            loadButton.setBackground(new java.awt.Color(204, 204, 204));
            loadButton.setFont(new java.awt.Font("Arial", 0, 14));
            loadButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            loadButton.setText("Carica\n");

            loadInfoLable.setFont(new java.awt.Font("Arial", 0, 14));
            loadInfoLable.setText("Data e Nome Utente");

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(loadInfoLable, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                    .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                    .addGap(46, 46, 46))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(29, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(loadInfoLable, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                    .addGap(24, 24, 24))
            );
        }
    }
}


