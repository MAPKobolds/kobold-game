package org.uniba.kobold.gui;

import org.uniba.kobold.util.SaveInstance;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class GuiLoadGame
 */
public class GuiLoadGame extends GuiAbstractPanel {

    /**
     * Attributes of the GuiLoadGame class
     */
    private static final String bgImagePath = "/img/pporc.png";
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel(bgImagePath);
    private static final String containerImagePath = "/img/BR.png";
    private static final GuiBackgroundPanel containerPanel = new GuiBackgroundPanel(containerImagePath);
    private static final JLayeredPane layeredPane = new JLayeredPane();
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

        if (menuButton != null) layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(muteMusicButton, JLayeredPane.PALETTE_LAYER);

        //containerPanel settings
        containerPanel.setOpaque(false);
        containerPanel.setRequestFocusEnabled(false);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        layeredPane.add(containerPanel, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    public void initComponents(int width, int height) {
    }

    /**
     * Method to add a SaveInstancePanel to the containerPanel
     * @param savePanel the SaveInstancePanel to add
     */
    public static void addSave(SaveInstancePanel savePanel) {
        containerPanel.add(savePanel);
        containerPanel.revalidate();
        containerPanel.repaint();
    }

    /**
     * Method to update the layout of the GuiLoadGame class
     */
    @Override
    public void updateLayout() {
        int width = getWidth();
        int height = getHeight();
        int buttonHeight = 50 * height / 700;
        int buttonWidth = 50 * height / 700;
        double widthOffset = width * 0.10;

        //This is where the magic happens
        backgroundPanel.setBounds(0, 0, width, height);
        containerPanel.setBounds((int) (width * 0.20), 0, (int) (width * 0.85), (int) (height * 0.80));
        layeredPane.setPreferredSize(new Dimension(width, height));
        menuButton.setBounds((int) widthOffset, (int) (height * 0.85), (int) (width * 0.80), 50);
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
        add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Inner class SaveInstancePanel
     */
    public static class SaveInstancePanel extends GuiAbstractPanel {

        /**
         * Attributes of the SaveInstancePanel class
         */
        private static ArrayList<SaveInstancePanel> instances = new ArrayList<>();
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
            GuiLoadGame.addSave(this);
        }

        @Override
        public void initComponents() {
        }

        public void initComponents(int width, int height) {
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
        public void initComponents(SaveInstance save) {
            setBackground(new Color(40, 0, 5));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

                //loadButton logic
                loadButton.addActionListener(_ -> {
                    Object[] options = {"Sì", "No"};
                    int response = JOptionPane.showOptionDialog(null, "Vuoi caricare questo salvataggio?",
                            "Carica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (response == JOptionPane.YES_OPTION) {
                        startLoadingScreen(save);
                    }
                });

                //deleteButton settings
                deleteButton.addActionListener(_ -> {
                    Object[] options = {"Sì", "No"};
                    int response = JOptionPane.showOptionDialog(null, "Vuoi eliminare questo salvataggio?",
                            "Elimina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            save.deleteSave();
                            JOptionPane.showMessageDialog(null, "File cancellato correttamente",
                                    "Conferma", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        instances.remove(this);
                        containerPanel.remove(this);
                        containerPanel.revalidate();
                        containerPanel.repaint();
                        updateLayout();
                    }
                });

                //loadInfoLabel settings
                loadInfoLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
                loadInfoLabel.setForeground(Color.WHITE);
                loadInfoLabel.setText(save.getSaveName() + "-" + save.getSaveDate());

                //Adding components to the panel
                add(loadButton);
                add(deleteButton);
                add(loadInfoLabel);
            }

        private void startLoadingScreen(SaveInstance save) {
            CardLayout loadingScreen = (CardLayout) GuiHub.masterPanel.getLayout();
            loadingScreen.show(GuiHub.masterPanel, "LoadingScreen");
            SwingUtilities.invokeLater(() -> onLoadingComplete(save));
        }

        private void onLoadingComplete(SaveInstance save) {
            try {
                save.loadSave();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Method to update the layout of the SaveInstancePanel class
         */
        @Override
        public void updateLayout() {
            int saveWidth = containerPanel.getWidth();
            int saveHeight = (int) (containerPanel.getHeight() * 0.11);

            // Imposta le dimensioni minime e massime
            setMinimumSize(new Dimension(saveWidth, saveHeight));
            setMaximumSize(new Dimension(saveWidth, saveHeight));

            setBounds(0, 0, saveWidth, saveHeight);
            //This is where the magic happens
            loadInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            loadInfoLabel.setVerticalTextPosition(SwingConstants.CENTER);
            loadInfoLabel.setBounds((int) (saveWidth * 0.05), (int) (saveHeight * 0.10), (int) (saveWidth * 0.50), saveHeight);
            loadButton.setBounds((int) (saveWidth * 0.50), (int) (saveHeight * 0.10), 100, 50);
            deleteButton.setBounds((int) (saveWidth * 0.66), (int) (saveHeight * 0.10), 100, 50);
        }
    }
}
