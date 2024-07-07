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
    private static JLabel dialogText;
    private static GuiBackgroundRef gamePanel;
    private static JPanel inventoryPanel;
    private static JLabel roomName;
    private static int inventoryCount = 0;
    private static final String BACKGROUND_PATH = "/img/pporc.png";
    private JPanel dialogPanel;
    private JLabel timerLabel;
    private JTextField inputField;
    private JButton menuButton;
    private JButton saveButton;
    private JButton toggleInventoryButton;
    private boolean isGameRunning = true;
    private boolean isInventoryVisible;

    /**
     * Creates new form containerPanel
     */
    public GuiGameRef(Game game) {
        initComponents(game);
        isInventoryVisible = true;

        try {
            this.tickTime(game);
        } catch (Exception e) {
            timerLabel.setText("Invalid time");
        }
    }

    private void initComponents(Game game) {
        dialogPanel = new JPanel();
        dialogText = new JLabel();
        inventoryPanel = new JPanel();
        gamePanel = new GuiBackgroundRef(BACKGROUND_PATH);
        roomName = new JLabel();
        inputField = new JTextField();
        timerLabel = new JLabel();
        inventoryPanel.setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        //Setting the buttons up
        menuButton = new GuiGenericButton(
                "Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        saveButton = new GuiGenericButton(
                "Salva",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        toggleInventoryButton = new GuiGenericButton(
                "Mostra",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> toggleInventory());

        //Setting the dialogText
        dialogText.setFont(new Font("Arial", Font.BOLD, 16));
        dialogText.setForeground(Color.WHITE);
        dialogText.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting the inputField
        inputField.addActionListener(_ -> readInput(game));

        //Setting the timerLabel
        timerLabel.setOpaque(true);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setFocusable(false);

        //inventoryPanel settings
        inventoryPanel.setBackground(new Color(40, 0, 5));
        this.refreshItem(game);

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
        inventoryPanel.setLayout(new GridBagLayout());
        game.getInventory().getItems().forEach(this::addItem);
    }

    public void addItem(Item item) {
        JButton itemButton = new GuiObjectButton(item.getName(), item.getImage());
        itemButton.setVisible(true);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridManager(gridBagConstraints, inventoryCount);

        inventoryPanel.add(itemButton, gridBagConstraints);
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
        inventoryCount++;
    }

    private static void gridManager(GridBagConstraints gridBagConstraints, int i) {
        int columns = 3;
        gridBagConstraints.gridx = i % columns;
        gridBagConstraints.gridy = i / columns;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
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

    public static void setDialogLabel(String message) {
        dialogText.setText("<html>" + message + "</html>");
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

    public static void updateGamePanel(String path) {
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
        dialogText.setText(gameCommandResult.getDescription());

        switch (type) {
            case REFRESH_INVENTORY -> this.refreshItem(game);
            case MOVE -> {
                //TODO refresh bg
                //refresh bg
            }
        }
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
