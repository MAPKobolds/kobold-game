package org.uniba.kobold.gui;

import org.uniba.kobold.game.SaveInstance;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

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
    private final JToggleButton muteMusicButton = new JToggleButton();

    /**
     * Constructor for the GuiLoadGame class
     */
    public GuiLoadGame() {
        panelManager();
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
    @Override
    public void initComponents() {
        //Initializations
        menuButton = new GuiGenericButton(
                "Torna al Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        //muteMusicButton settings
        muteMusicButton.setBounds(0, 0, 50, 50);
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

        //Temporaneo finché non avremo salvataggi veri
        for (int i = 0; i < 3; i++) {
            SaveInstance saveGame = new SaveInstance();
            SaveInstancePanel savePanel = new SaveInstancePanel(saveGame);
        }

        // Add the menuButton to the JLayeredPane with a higher depth value
        if (menuButton != null) layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(muteMusicButton, JLayeredPane.PALETTE_LAYER);

        //Container of the save games panel settings
        savesBGPanel.setRequestFocusEnabled(false);
    }

    /**
     * Method to fill the savesBGPanel with the SaveInstancePanels
     */
    private void fillSavesBGPanel() {
        savesBGPanel.setLayout(new BoxLayout(savesBGPanel, BoxLayout.Y_AXIS));
        for (SaveInstancePanel savePanel : SaveInstancePanel.getInstances()) {
            savePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            savesBGPanel.add(savePanel);
        }
        layeredPane.add(savesBGPanel, JLayeredPane.PALETTE_LAYER);
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
        savesBGPanel.setBounds((int) (width * 0.20), 0, (int) (width * 0.85), (int) (height * 0.80));
        layeredPane.setPreferredSize(new Dimension(width, height));
        menuButton.setBounds((int) widthOffset, (int) (height * 0.85), (int) (width * 0.80), 50);
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
        fillSavesBGPanel();
        add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Inner class SaveInstancePanel
     */
    public class SaveInstancePanel extends GuiAbstractPanel {

        /**
         * Attributes of the SaveInstancePanel class
         */
        private static ArrayList<SaveInstancePanel> instances = new ArrayList<>();
        private final int saveWidth = (int) (backgroundPanel.getWidth() * 0.85);
        private final int saveHeight = (int) (backgroundPanel.getHeight() * 0.10);
        private JButton loadButton;
        private JButton deleteButton;
        private JLabel loadInfoLabel;

        /**
         * Constructor for the SaveInstancePanel class parameterized with a SaveInstance object
         */
        public SaveInstancePanel(SaveInstance saveInstance) {
            initComponents(saveInstance);
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateLayout();
                }
            });
        }

        /**
         * Method to initialize the components of the SaveInstancePanel class
         */
        @Override
        public void initComponents() {
        }

        /**
         * Method to get the instances of the SaveInstancePanel class
         */
        public static ArrayList<SaveInstancePanel> getInstances() {
            return instances;
        }

        /**
         * Method to initialize the components of the SaveInstancePanel class
         */
        @Override
        public <T> void initComponents(T object) {
            setMinimumSize(new Dimension(saveWidth, saveHeight));
            setMaximumSize(new Dimension(saveWidth, saveHeight));
            setBackground(new Color(40, 0, 5));
            setVisible(true);
            try {
                if (object instanceof SaveInstance save) {
                    instances.add(this);
                    setLayout(null);
                    //Initializations
                    loadButton = new GuiGenericButton(
                            "Carica",
                            new Color(40, 0, 5),
                            Color.WHITE,
                            new Dimension(100, 50)
                    ).getButton();
                    deleteButton = new GuiGenericButton(
                            "Elimina",
                            new Color(40, 0, 5),
                            Color.WHITE,
                            new Dimension(100, 50)
                    ).getButton();
                    loadInfoLabel = new JLabel();

                    //loadButton settings
                    loadButton.addActionListener(_ -> {
                        //Qui andrà richiamato il metodo che seleziona il salvataggio e poi loading screen
                        CardLayout loadingScreen = (CardLayout) GuiLoadGame.this.getParent().getLayout();
                        loadingScreen.show(GuiLoadGame.this.getParent(), "LoadingScreen");
                    });

                    //deleteButton settings
                    deleteButton.addActionListener(_ -> {
                        SaveInstance.getInstances().remove(save);
                        updateLayout();
                    });

                    //loadInfoLabel settings
                    loadInfoLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
                    loadInfoLabel.setForeground(Color.WHITE);
                    loadInfoLabel.setText(save.getSaveName() + " - " + save.getSaveDate());

                    //Adding components to the panel
                    add(loadButton);
                    add(deleteButton);
                    add(loadInfoLabel);
                }
            } catch (ClassCastException e){
                throw new IllegalArgumentException("Object is not of type SaveInstance");
            } catch (NullPointerException e){
                throw new IllegalArgumentException("Object is null");
            }
        }

        /**
         * Method to update the layout of the SaveInstancePanel class
         */
        @Override
        public void updateLayout() {
            //This is where the magic happens
            loadInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            loadInfoLabel.setVerticalTextPosition(SwingConstants.CENTER);
            loadInfoLabel.setBounds((int) (saveWidth * 0.05), (int) (saveHeight * 0.10), (int) (saveWidth * 0.50), saveHeight);
            loadButton.setBounds((int) (saveWidth * 0.70), (int) (saveHeight * 0.10), 100, 50);
            deleteButton.setBounds((int) (saveWidth * 0.82), (int) (saveHeight * 0.10), 100, 50);
        }
    }
}
