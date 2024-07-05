package org.uniba.kobold.gui;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;
import org.uniba.kobold.util.ManageTimer;
import org.uniba.kobold.util.SaveInstance;
import org.uniba.kobold.util.Serializer;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiGame extends JPanel {

    private static GuiGame instance;
    private static final int MAXITEMS = 10;
    private static final String backgroundPath = "/img/pporc.png";
    private static final String mapPath = "/img/BR.png";
    private static final GuiBackgroundPanel gamePanel = new GuiBackgroundPanel(backgroundPath, 300, 300);
    private final JPanel mapPanel = new GuiBackgroundPanel(mapPath, 200, 200);
    private JPanel dialogPanel;
    public static JLabel dialogText;
    private JTextField inputField;
    private JPanel inventoryPanel;
    private JButton menuButton;
    private JToggleButton muteMusicButton;
    private JButton saveButton;
    private static JLabel timerLabel;
    private JButton toggleInventoryButton;
    private JToolBar toolBar;
    private boolean isInventoryVisible;
    private final String temporaryItemName = "temporaryName";
    private final String temporaryItemPath = "/img/buttonDefault.png";

    public GuiGame() {
        initComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
            @Override
            public void componentResized(ComponentEvent e) {
                updateToolbar();
            }
        });
    }

    /**
     * Method to get the instance of the class
     * @return the instance of the class
     */
    public static GuiGame getInstance() {
        if (instance == null) {
            instance = new GuiGame();
        }
        return instance;
    }

    private void initComponents() {
        toolBar = new JToolBar();
        timerLabel = new JLabel();
        dialogPanel = new JPanel();
        inputField = new JTextField();
        dialogText = new JLabel();
        inventoryPanel = new JPanel();
        muteMusicButton = new JToggleButton();
        ManageTimer.getInstance();
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

        //Setting the muteMusicButton
        muteMusicButton.setPreferredSize(new Dimension(40, 10));
        UtilMusic.initButton(muteMusicButton);

        //Setting the dialogText
        dialogText.setFont(new Font("Arial", Font.BOLD, 16));
        dialogText.setForeground(Color.WHITE);
        dialogText.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting the inputField
        inputField.addActionListener(_ -> {
            String userInput = inputField.getText();
            inputField.setText("");
            //TODO: metodo input

        });

        //Setting the timerLabel
        timerLabel.setOpaque(true);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setFocusable(false);
        timerLabel.setText("00:00:00");

        //inventoryPanel settings
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        isInventoryVisible = true;
        fillInventory();

        //Setting the saveButton logic
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        saveButton.addActionListener(_ -> {
            Object[] options = {"Sì", "No"};
            int response = JOptionPane.showOptionDialog(null, "Vuoi salvare la partita?", "Conferma Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == JOptionPane.YES_OPTION) {
                SaveInstance save = new SaveInstance(Game.getPlayerName());
                JOptionPane.showMessageDialog(null, "Partita salvata con successo!", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
                Serializer.saveToJson(save, save.getSaveName());
            }
        });

        //Setting the menuButton logic
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> {
            Object[] options = {"Sì", "No"};
            int response = JOptionPane.showOptionDialog(null, "Vuoi davvero abbandonare senza salvare?", "Torna al Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == JOptionPane.YES_OPTION) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Menu");
                ManageTimer.resetTimer();
            }
        });

        //Setting the toolbar
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.BLACK);
        updateToolbar();

        dialogPanel.setBackground(new Color(40, 0, 5));
        gameLayout();
    }

    /**
     * Method to set the time label
     * @param time the time to set
     */
    public static void setTimeLabel(String time) {
        timerLabel.setText(" " + time + " ");
    }

    /**
     * Method to update the game panel
     * @param imagePath the path to the image
     */
    public static void updateGamePanel(String imagePath) {
        gamePanel.updateBackground(imagePath);
    }

    public void addItem(Item item) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (Component component : inventoryPanel.getComponents()) {
                    if (component instanceof GuiObjectButton) {
                        GuiObjectButton itemButton = (GuiObjectButton) component;
                        if (temporaryItemName.equals(itemButton.getText())) {
                            itemButton.setName(item.getName());
                            itemButton.updateImage(item.getImage());
                            itemButton.setVisible(true);
                            setButtonAction(item, itemButton);
                            inventoryPanel.revalidate();
                            inventoryPanel.repaint();
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * Method to update the inventory based on the game
     */
    public void fillInventory() {
        for (int i = 0; i < MAXITEMS; i++) {
            GuiObjectButton itemButton = new GuiObjectButton(temporaryItemName,temporaryItemPath);
            itemButton.setVisible(false);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridManager(gridBagConstraints, i);
            inventoryPanel.add(itemButton, gridBagConstraints);
        }
        gameLayout();
    }

    /**
     * Method to manage the grid layout
     * @param gridBagConstraints the grid layout manager
     * @param i the index of the item
     */
    private static void gridManager(GridBagConstraints gridBagConstraints, int i) {
        int columns = 2;
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

    public static void setDialogLabel(String message) {
        dialogText.setText("<html>" + message + "<html>");
    }

    /**
     * Method to set the action of the button
     * @param itemButton the button
     */
    private void setButtonAction(Item item,JButton itemButton) {
        itemButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //TODO: Interaction with the item
                    updateGamePanel("/img/modricBR.png");
                } else if (e.getClickCount() == 1) {
                    dialogText.setText("<html>" + item.getDescription() + "<html>");
                }
            }
        });
    }

    /**
     * Method to update the toolbar
     */
    protected void updateToolbar() {
        double widthOffsetSx = this.getWidth() * 0.01;
        toolBar.removeAll();
        toolBar.add(muteMusicButton);
        toolBar.add(toggleInventoryButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(timerLabel);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(saveButton);
        toolBar.add(Box.createHorizontalStrut((int) widthOffsetSx));
        toolBar.add(menuButton);
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

    public void gameLayout() {
        GroupLayout dialogPanelLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
                dialogPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogPanelLayout.setVerticalGroup(
                dialogPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(dialogPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inputField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dialogText, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout mapPanelLayout = new GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
                mapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 200, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
                mapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 155, Short.MAX_VALUE)
        );

        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(mapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 409, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(mapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 278, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inventoryPanel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
}
