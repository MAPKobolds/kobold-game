package org.uniba.kobold.gui;

import org.uniba.kobold.game.SaveInstance;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;

/**
 * Class GuiLoadGame
 */
public class GuiLoadGame extends GuiAbstractPanel {

    /**
     * Attributes of the GuiLoadGame class
     */
    private final String bgURL = "/img/pporc.png";
    private final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel(bgURL);
    private final String savesBGPath = "/img/BR.png";
    private final GuiBackgroundPanel savesBGPanel = new GuiBackgroundPanel(savesBGPath);
    private final JLayeredPane layeredPane = new JLayeredPane();
    private JButton menuButton;

    /**
     * Constructor for the GuiLoadGame class
     */
    public GuiLoadGame() {
       panelManager();
    }

    /**
     * Method to initialize the components of the GuiLoadGame class
     */
    @Override
    public void initComponents() {

        //Initializations
        menuButton = new GuiGenericButton(
                "Torna al Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        //muteMusicButton settings
        UtilMusic.initButton(muteMusicButton);
        add(muteMusicButton);

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

        // Add the save games panels to the container
        savesBGPanel.setLayout(new BoxLayout(savesBGPanel, BoxLayout.Y_AXIS));
        layeredPane.add(savesBGPanel, JLayeredPane.DEFAULT_LAYER);

        //Container of the save games panel settings
        savesBGPanel.setRequestFocusEnabled(false);
    }

    /**
     * Method to fill the savesBGPanel with the SaveInstancePanels
     */
    private void fillSavesBGPanel() {
        savesBGPanel.removeAll();
        //Temporaneo finché non avremo salvataggi veri
        for (int i = 0; i < 3; i++) {
            SaveInstance saveGame = new SaveInstance();
        }

        for (SaveInstance save : SaveInstance.getInstances()) {
            SaveInstancePanel saveInstancePanel = new SaveInstancePanel();
            saveInstancePanel.setVisible(true);
            saveInstancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            savesBGPanel.add(saveInstancePanel);
        }
    }

    /**
     * Method to update the layout of the GuiLoadGame class
     */
    @Override
    public void updateLayout() {
        int width = getWidth();
        int height = getHeight();
        int buttonHeight = 50 * height / 600;
        int buttonWidth = 50 * height / 600;
        double widthOffset = width * 0.10;

        //This is where the magic happens
        backgroundPanel.setBounds(0, 0, width, height);
        savesBGPanel.setBounds((int) (width * 0.20), 0, (int)(width * 0.85), (int)(height * 0.80));
        layeredPane.setPreferredSize(new Dimension(width, height));
        menuButton.setBounds((int) widthOffset, (int) (height * 0.85) , (int) (width * 0.80), 50);
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
        add(layeredPane, BorderLayout.CENTER);
        fillSavesBGPanel();
    }

    /**
     * Inner class SaveInstancePanel
     */
    public class SaveInstancePanel extends GuiAbstractPanel {

        /**
         * Attributes of the SaveInstancePanel class
         */
        private final int saveWidth = (int) (backgroundPanel.getWidth() * 0.85);
        private final int saveHeight = (int) (backgroundPanel.getHeight() * 0.10);
        private JButton loadButton;
        private JButton deleteButton;
        private JLabel loadInfoLabel;

        /**
         * Constructor for the SaveInstancePanel class
         */
        public SaveInstancePanel() {
            panelManager();
        }

        /**
         * Method to initialize the components of the SaveInstancePanel class
         */
        @Override
        public void initComponents() {
            setMinimumSize(new Dimension(saveWidth, saveHeight));
            setMaximumSize(new Dimension(saveWidth, saveHeight));
            setBackground(new Color(40, 0, 5));

            //Initializations
            loadButton = new GuiGenericButton(
                    "Carica",
                    new Color(40, 0, 5),
                    Color.WHITE
                    ).getButton();
            deleteButton = new GuiGenericButton(
                    "Elimina",
                    new Color(40, 0, 5),
                    Color.WHITE
                    ).getButton();
            loadInfoLabel = new JLabel();

            //loadButton settings
            loadButton.addActionListener(_ -> {
                    //Qui andrà richiamato il metodo che seleziona il salvataggio e poi loading screen
                    CardLayout loadingScreen = (CardLayout) getParent().getLayout();
                    loadingScreen.show(getParent(), "LoadingScreen");
                });

            //deleteButton settings
            deleteButton.addActionListener(_ -> {
                    SaveInstance.getInstances().remove(this);
                    updateLayout();
                });

            //loadInfoLabel settings
            loadInfoLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
            loadInfoLabel.setForeground(Color.WHITE);
            loadInfoLabel.setText("Data e Nome Utente");

            //Adding components to the panel
            add(loadButton);
            add(deleteButton);
            add(loadInfoLabel);
        }

        /**
         * Method to update the layout of the SaveInstancePanel class
         */
        @Override
        public void updateLayout() {
            int width = getWidth();
            int height = getHeight();
            int buttonHeight = 100 * height / 600;
            int buttonWidth = 100 * width / 600;
            double widthOffset = width * 0.60;

            //This is where the magic happens
            loadInfoLabel.setBounds(0, 0, (int) (width * 0.60), height);
            deleteButton.setBounds((int) widthOffset, (int) (height * 0.10), buttonWidth, buttonHeight);
            loadButton.setBounds((int) widthOffset + buttonWidth, (int) (height * 0.85) , (int) (width * 0.20), buttonHeight);
        }
    }
}
