package org.uniba.kobold.guiRef;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.gui.GuiGenericButton;
import org.uniba.kobold.util.SaveInstance;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class GuiMenuRef extends JPanel {
    
    private JButton creditsButton;
    private JButton exitButton;
    private JButton gameStartButton;
    private JPanel buttonsContainer;
    private JButton loadButton;
    private JButton siteButton;
    private static final String BACKGROUND_PATH = "/img/pporc.png";

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiMenuRef() {
        initComponents();
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    public void initComponents() {
        buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        gameStartButton = new GuiGenericButton(
                "Inizia Partita",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        loadButton = new GuiGenericButton(
                "Carica Partita",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        creditsButton = new GuiGenericButton(
                "Riconoscimenti",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        siteButton = new GuiGenericButton(
                "Sito Koboldico",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        exitButton = new GuiGenericButton(
                "Esci dal gioco",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        //gameStartButton logic
        gameStartButton.addActionListener(_ -> {
            JTextField playerInput = new JTextField();
            Object[] message = {
                    "Inserisci il nome del personaggio:", playerInput
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Nome Personaggio", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String playerName = playerInput.getText();
                if (playerName != null && !playerName.trim().isEmpty() && isPlayerNew(playerInput.getText())) {
                    Game.setPlayerName(playerName);
                    GuiHubRef.changeTo(PagesEnum.LOADING);
                } else {
                    JOptionPane.showMessageDialog(null, "Nome non valido o gia' occupato.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nuova partita annullata.", "Annullato", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //loadGameButton logic
        loadButton.addActionListener(_ -> GuiHubRef.changeTo(PagesEnum.GAME_SAVES));

        //CreditsButton logic
        creditsButton.addActionListener(_ -> GuiHubRef.changeTo(PagesEnum.ACKNOWLEDGEMENT));

        //SiteButton logic
        siteButton.addActionListener(_ -> {
        });

        //Exit button logic
        exitButton.addActionListener(_ -> System.exit(0));
        setLayout();
    }

    /**
     * Method to paint the background image
     * @param g the graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_PATH)));
        Image image = backgroundImage.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Method to check if the player is new
     * @param name the name of the player
     * @return true if the player is new, false otherwise
     */
    private boolean isPlayerNew(String name) {
        for (SaveInstance save : SaveInstance.getInstances()) {
            String saveName = save.getSaveName();
            saveName = saveName.substring(0, saveName.indexOf(" - "));
            if (saveName.matches(name)) {
                return false;
            }
        }
        return true;
    }

    private void setLayout() {
        GroupLayout jPanel1Layout = new GroupLayout(buttonsContainer);
        buttonsContainer.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(siteButton, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                                        .addComponent(loadButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gameStartButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(creditsButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exitButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(27, Short.MAX_VALUE)
                                .addComponent(gameStartButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(creditsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(siteButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(buttonsContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(227, 227, 227))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(297, 297, 297)
                                .addComponent(buttonsContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(53, 53, 53))
        );
    }
}
