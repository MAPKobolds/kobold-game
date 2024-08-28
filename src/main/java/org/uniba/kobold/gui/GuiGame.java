package org.uniba.kobold.gui;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;
import org.uniba.kobold.game.GameCommandResult;
import org.uniba.kobold.game.GameCommandResultType;
import org.uniba.kobold.util.GameConverter;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The type Gui game.
 */
public class GuiGame extends JPanel {
    private JTextPane dialogText;
    private JPanel inventoryPanel;
    private JLabel roomName;
    private GuiBackground gamePanel;
    private JPanel dialogPanel;
    private JLabel timerLabel;
    private JTextField inputField;
    private JButton menuButton;
    private JButton saveButton;
    private JButton toggleInventoryButton;
    private int inventoryCount = 0;
    private boolean isGameRunning = true;
    private boolean isInventoryVisible;
    /**
     * The Background image.
     */
    Image backgroundImage = new ImageIcon("src/main/resources/img/woodwall.jpg").getImage();

    /**
     * Creates new form containerPanel
     *
     * @param game the game
     */
    public GuiGame(Game game) {
        initComponents(game);
        isInventoryVisible = true;

        this.refreshItem(game);
        this.refreshGUI(game.getCurrentRoomDescription(), game);
        this.setFocusable(false);

        try {
            this.tickTime(game);
        } catch (Exception e) {
            timerLabel.setText("Invalid time");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initComponents(Game game) {
        dialogPanel = new JPanel();
        dialogText = new JTextPane();
        inventoryPanel = new JPanel();
        gamePanel = new GuiBackground(game.getCurrentRoomMap().getCurrentRoom().getBackgroundImage());
        roomName = new JLabel();
        inputField = new JTextField();
        timerLabel = new JLabel();
        Font customFont = null;
        inventoryPanel.setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Minecraft.ttf"));
            customFont = customFont.deriveFont(Font.PLAIN, 24);
            inputField.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        dialogText.setOpaque(true);
        inputField.setBorder(BorderFactory.createLineBorder(new Color(93, 72, 55), 4));
        dialogText.setBorder(BorderFactory.createLineBorder(new Color(93, 72, 55), 4));
        gamePanel.setBorder(BorderFactory.createLineBorder(new Color(93, 72, 55), 4));
        inputField.setBackground(new Color(147, 119, 90));

        menuButton = new GuiGenericButton(
                "Menu"
        ).getButton();


        saveButton = new GuiGenericButton(
                "Salva"
        ).getButton();

        toggleInventoryButton = new GuiGenericButton(
                "Mostra"
        ).getButton();

        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        toggleInventoryButton.addActionListener(_ -> toggleInventory());

        //Setting the dialogText
        dialogText.setBackground(new Color(147, 119, 90));
        dialogText.setContentType("text/html");
        dialogText.setEditable(false);
        dialogText.setFocusable(false);

        //Setting the inputField
        inputField.addActionListener(_ -> readInput(game));

        //Setting the timerLabel
        timerLabel.setOpaque(true);
        timerLabel.setFont(customFont);
        timerLabel.setFocusable(false);
        timerLabel.setBackground(new Color(147, 119, 90));
        timerLabel.setForeground(Color.WHITE);


        //Setting the saveButton logic
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        saveButton.addActionListener(_ -> this.saveGame(game));

        //Setting the menuButton logic
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        menuButton.addActionListener(_ -> this.goToMenu());

        dialogPanel.setBackground(new Color(0,0,0,0));
        dialogPanel.setFocusable(false);
        inventoryPanel.setBackground(new Color(0,0,0,0));

        if (GuiHub.isGuiActive()) {
            GuiHub.setGameToolbar(timerLabel, saveButton, menuButton, toggleInventoryButton);
        }
        setLayout();
    }

    /**
     * Refresh item.
     *
     * @param game the game
     */
    public void refreshItem(Game game) {
        inventoryPanel.removeAll();

        inventoryPanel.setLayout(new GridBagLayout());
        inventoryCount = 0;
        game.getInventory().getItems().forEach(this::addItem);
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(Item item) {
        JButton itemButton = new GuiObjectButton(item.getName(), item.getImage());
        itemButton.setBorder(BorderFactory.createLineBorder(new Color(93, 72, 55), 3));
        itemButton.setMinimumSize(new Dimension(80, 80));
        itemButton.setMaximumSize(new Dimension(80, 80));
        itemButton.setPreferredSize(new Dimension(80, 80));

        itemButton.setVisible(true);
        itemButton.setToolTipText("<html>" + item.getDescription() + "</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridManager(gridBagConstraints, inventoryCount);

        inventoryPanel.add(itemButton, gridBagConstraints);
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
        inventoryCount++;
    }

    private static void gridManager(GridBagConstraints gridBagConstraints, int i) {
        int columns = 2;
        gridBagConstraints.gridx = i % columns;
        gridBagConstraints.gridy = i / columns;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
    }

    /**
     * Method to toggle the inventory
     */
    private void toggleInventory() {
        isInventoryVisible = !isInventoryVisible;
        inventoryPanel.setVisible(isInventoryVisible);
        revalidate();
        repaint();
    }

    /**
     * Sets dialog label.
     *
     * @param message the message
     */
    public void setDialogLabel(String message) {

        this.dialogText.setText("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    \n" +
                "    <!-- Embedding font using @font-face -->\n" +
                "    <style>\n" +
                "        @font-face {\n" +
                "            font-family: 'Minecraft';\n" +
                "            src: url('src/main/resources/fonts/minecraft.ttf') format('truetype');\n" +
                "            font-weight: normal;\n" +
                "            font-style: normal;\n" +
                "        }\n" +
                "        \n" +
                "        /* Apply the custom font directly to elements */\n" +
                "        body {\n" +
                "            font-family: 'Minecraft', sans-serif;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "        \n" +
                "        h1, h2, h3 {\n" +
                "            font-family: 'Minecraft', serif;\n" +
                "        }\n" +
                "    </style>\n" +
                "    \n" +
                "    <title>Minecraft Font Example</title>\n" +
                "</head>\n" +
                "<body>\n" +  message + "</body>\n" +
                "</html>");
    }

    /**
     * Tick time.
     *
     * @param game the game
     */
    public synchronized void tickTime(Game game) {
        new Thread(() -> {
            while (isGameRunning) {
                timerLabel.setText(" " + game.getTimeManager().getTime() + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /**
     * Update game panel.
     *
     * @param path the path
     */
    public void updateGamePanel(String path) {
        gamePanel.updateBackground(path);
    }

    private void setLayout () {
        GroupLayout dialogPanelLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
                dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
        );

        dialogPanelLayout.setVerticalGroup(
                dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(roomName, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addGap(555, 555, 555))
        );

        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(roomName, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addGap(402, 402, 402))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(inventoryPanel, GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputField))
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(inventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void saveGame(Game game) {
        Object[] options = {"Sì", "No"};
        int response = JOptionPane.showOptionDialog(null, "Vuoi salvare la partita?", "Conferma Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (response == JOptionPane.YES_OPTION) {
            GameConverter.serialize(game, game.getTimeManager().getTime());

            JOptionPane.showMessageDialog(null, "Partita salvata con successo!", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Sets game running.
     *
     * @param gameRunning the game running
     */
    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    private void readInput(Game game) {
        String userInput = inputField.getText();
        inputField.setText("");

        refreshGUI(game.executeCommand(userInput), game);
    }

    /**
     * Refresh gui.
     *
     * @param gameCommandResult the game command result
     * @param game              the game
     */
    public void refreshGUI(GameCommandResult gameCommandResult, Game game) {
        GameCommandResultType type = gameCommandResult.getGameCommandResultType();
        setDialogLabel("<span style='color: white;'>" + gameCommandResult.getDescription() + "</span>");
        switch (type) {
            case REFRESH_INVENTORY -> this.refreshItem(game);
            case REFRESH_BACKGROUND, MOVE -> this.updateGamePanel(game.getCurrentRoomMap().getCurrentRoom().getBackgroundImage());
            case END -> {
                this.updateGamePanel(gameCommandResult.getPath());
                this.endGame(game.getTimeManager().getTime());
            }
        }
    }

    private void endGame(String time) {


        this.isGameRunning = false;
        this.saveButton.setVisible(false);
        this.inputField.setEnabled(false);

        JOptionPane.showMessageDialog(
            null,
            "Hai completato il gioco! \n Tempo di gioco: " + time,
            "Gioco completato",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void goToMenu() {
        Object[] options = {"Sì", "No"};
        int response = JOptionPane.showOptionDialog(null, "Vuoi davvero abbandonare senza salvare?", "Torna al Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (response == JOptionPane.YES_OPTION) {
            this.setGameRunning(false);
            GuiHub.setNormalToolbar();
            GuiHub.changeTo(PagesEnum.MENU, null);
        }
    }
}
