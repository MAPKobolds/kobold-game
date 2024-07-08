package org.uniba.kobold.guiRef;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;
import org.uniba.kobold.game.GameCommandResult;
import org.uniba.kobold.game.GameCommandResultType;
import org.uniba.kobold.util.GameConverter;
import javax.swing.*;
import java.awt.*;

public class GuiGameRef extends JPanel {
    private JTextPane dialogText;
    private JPanel inventoryPanel;
    private JLabel roomName;
    private GuiBackgroundRef gamePanel;
    private JPanel dialogPanel;
    private JLabel timerLabel;
    private JTextField inputField;
    private JButton menuButton;
    private JButton saveButton;
    private JButton toggleInventoryButton;
    private int inventoryCount = 0;
    private boolean isGameRunning = true;
    private boolean isInventoryVisible;
    Image backgroundImage = new ImageIcon("src/main/resources/img/rocks2.png").getImage();
    /**
     * Creates new form containerPanel
     */
    public GuiGameRef(Game game) {
        initComponents(game);
        isInventoryVisible = true;

        this.refreshItem(game);
        this.refreshGUI(game.getCurrentRoomDescription(), game);

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
        inventoryPanel = new GuiBackgroundPanel("src/main/resources/img/wall.png");
        gamePanel = new GuiBackgroundRef(game.getCurrentRoomMap().getCurrentRoom().getBackgroundImage());
        roomName = new JLabel();
        inputField = new JTextField();
        timerLabel = new JLabel();
        inventoryPanel.setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        //Setting the buttons up
        menuButton = new GuiImageButton(
                "src/main/resources/img/rocks.png",
                "Menù"
        ).getButton();


        saveButton = new GuiImageButton(
                "src/main/resources/img/rocks.png",
                "Salva"
        ).getButton();

        toggleInventoryButton = new GuiImageButton(
                "src/main/resources/img/rocks.png",
                "Mostra"
        ).getButton();

        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> toggleInventory());

        //Setting the dialogText
        dialogText.setBackground(new Color(0,0,0));
        dialogText.setContentType("text/html");
        dialogText.setEditable(false);
        dialogText.setFocusable(false);

        //Setting the inputField
        inputField.addActionListener(_ -> readInput(game));

        //Setting the timerLabel
        timerLabel.setOpaque(true);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setFocusable(false);


        //Setting the saveButton logic
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        saveButton.addActionListener(_ -> this.saveGame(game));

        //Setting the menuButton logic
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> this.goToMenu());

        dialogPanel.setBackground(new Color(40, 0, 5));


        if (GuiHubRef.isGuiActive()) {
            GuiHubRef.setGameToolbar(timerLabel, saveButton, menuButton, toggleInventoryButton);
        }
        setLayout();
    }

    public void refreshItem(Game game) {
        inventoryPanel.removeAll();

        inventoryPanel.setLayout(new GridBagLayout());
        inventoryCount = 0;
        game.getInventory().getItems().forEach(this::addItem);
    }

    public void addItem(Item item) {
        System.out.println(inventoryCount);
        JButton itemButton = new GuiObjectButton(item.getName(), item.getImage());
        itemButton.setPreferredSize(new Dimension(70, 70));
        itemButton.setVisible(true);
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
        gridBagConstraints.fill = GridBagConstraints.NORTHEAST;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
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
                "            font-size: 16px;\n" +
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

    public void updateGamePanel(String path) {
        gamePanel.updateBackground(path);
    }

    private void setLayout () {
        GroupLayout dialogPanelLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
                dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        dialogPanelLayout.setVerticalGroup(
                dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 119, Short.MAX_VALUE))
        );

        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(roomName, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addGap(555, 555, 555))
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(roomName, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addGap(402, 402, 402))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(inputField))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(3, 3, 3)
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

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    private void readInput(Game game) {
        String userInput = inputField.getText();
        inputField.setText("");

        refreshGUI(game.executeCommand(userInput), game);
    }

    public void refreshGUI(GameCommandResult gameCommandResult, Game game) {
        GameCommandResultType type = gameCommandResult.getGameCommandResultType();
        setDialogLabel("<span style='color: white;'>" + gameCommandResult.getDescription() + "</span>");
        switch (type) {
            case REFRESH_INVENTORY -> this.refreshItem(game);
            case MOVE -> this.updateGamePanel(game.getCurrentRoomMap().getCurrentRoom().getBackgroundImage());
            case END -> this.endGame(game.getTimeManager().getTime());
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
            GuiHubRef.setNormalToolbar();
            GuiHubRef.changeTo(PagesEnum.MENU, null);
        }
    }
}
