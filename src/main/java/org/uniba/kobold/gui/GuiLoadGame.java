package org.uniba.kobold.gui;

import org.uniba.kobold.game.SaveInstance;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

/**
 * Class GuiLoadGame
 */
public class GuiLoadGame extends JPanel {

    /**
     * Attributes of the GuiLoadGame class
     */
    private final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel();
    private final String savesBGPath = "/img/BR.png";
    private final int width = 800;
    private final int height = 600;
    private JPanel savesBGPanel;
    private JButton menuButton;
    private JToggleButton muteMusicButton;

    /**
     * Constructor for the GuiLoadGame class
     */
    public GuiLoadGame() {
        initComponents();

        // Aggiungi un ComponentAdapter per eseguire il codice legato al muteMusicButton quando il componente viene mostrato
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
        });
    }

    /**
     * Method to initialize the components of the GuiLoadGame class
     */
    private void initComponents() {
        menuButton = new GuiGenericButton("Torna al Menu").getButton();
        muteMusicButton = new JToggleButton();
        muteMusicButton.setBounds(0, 0, 50, 50);

        // Create a JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));

        // Add the backgroundPanel to the JLayeredPane with a lower depth value
        backgroundPanel.add(muteMusicButton);
        backgroundPanel.setBounds(0, 0, width, height);


        //menuButton settings
        if (menuButton != null) {
            menuButton.setBounds(340, 530, 150, 40);
            menuButton.addActionListener(_ -> {
                CardLayout menu = (CardLayout) getParent().getLayout();
                menu.show(getParent(), "Menu");
            });
        }

        // Add the menuButton to the JLayeredPane with a higher depth value
        if (menuButton != null) layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(muteMusicButton, JLayeredPane.PALETTE_LAYER);

        //Mute music button logic and clip management and savesBGPanel settings
        savesBGPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(savesBGPath)));
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        savesBGPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        savesBGPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        savesBGPanel.setRequestFocusEnabled(false);

        //Temporaneo finché non avremo salvataggi veri
        for (int i = 0; i < 2; i++) {
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
        add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Inner class SaveInstancePanel
     */
    public class SaveInstancePanel extends JPanel {

        /**
         * Attributes of the SaveInstancePanel class
         */
        private final int saveWidth = (int) (backgroundPanel.getWidth() * 0.85);
        private final int saveHeight = (int) (backgroundPanel.getHeight() * 0.1);
        private JButton loadButton = new GuiGenericButton("Carica").getButton();
        private JLabel loadInfoLable;

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

            loadInfoLable = new JLabel();
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
