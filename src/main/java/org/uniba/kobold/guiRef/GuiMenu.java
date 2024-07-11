package org.uniba.kobold.guiRef;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.util.BrowserNavigator;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GuiMenu extends JPanel {
    
    private JButton creditsButton;
    private JButton exitButton;
    private JButton gameStartButton;
    private JPanel buttonsContainer;
    private JButton loadButton;
    private JButton siteButton;
    private JButton gameRecordButton;
    private static final String BACKGROUND_PATH = "/img/wall.png";

    /**
     * Constructor of the class GuiBackgroundPanel
     */
    public GuiMenu() {
        initComponents();
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    public void initComponents() {
        buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        gameStartButton = new GuiGenericButton(
                "Inizia Partita"
        ).getButton();

        loadButton = new GuiGenericButton(
                "Carica Partita"
        ).getButton();

        creditsButton = new GuiGenericButton(
                "Riconoscimenti"
        ).getButton();

        gameRecordButton = new GuiGenericButton(
                "Record"
        ).getButton();

        siteButton = new GuiGenericButton(
                "Sito Koboldico"
        ).getButton();

        exitButton = new GuiGenericButton(
                "Esci dal gioco"
        ).getButton();

        //gameStartButton logic
        gameStartButton.addActionListener(_ -> {
            try {
                this.createNewGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //loadGameButton logic
        loadButton.addActionListener(_ -> GuiHub.changeTo(PagesEnum.GAME_SAVES, null));

        //CreditsButton logic
        creditsButton.addActionListener(_ -> GuiHub.changeTo(PagesEnum.ACKNOWLEDGEMENT, null));

        //SiteButton logic
        siteButton.addActionListener(_ -> goToAppSite());

        gameRecordButton.addActionListener(_ -> GuiHub.changeTo(PagesEnum.GAME_RECORDS, null));

        //Exit button logic
        exitButton.addActionListener(_ -> GuiHub.changeTo(PagesEnum.EXIT, null));

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
                                        .addComponent(gameRecordButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(gameRecordButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(siteButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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

    private void goToAppSite() {
        try {
            BrowserNavigator.goToSite("http://localhost:4200");
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "Non posso aprire il browser.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewGame() throws IOException {
        JTextField playerInput = new JTextField();
        Object[] message = { "Inserisci il nome del personaggio:", playerInput };
        int option = JOptionPane.showConfirmDialog(null, message, "Nome Personaggio", JOptionPane.OK_CANCEL_OPTION);

        if (option != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(
                    null,
                    "Impossibile aprire la finestra di dialogo.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String playerName = playerInput.getText().trim();

        if (playerName.isEmpty() || playerName.contains("-")) {
            JOptionPane.showMessageDialog(
                    null,
                    "Il nome non deve contenere '-' e deve avere almeno un carattere.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        GuiHub.changeTo(PagesEnum.NEW_GAME, new Game(playerName));
    }

}
