package org.uniba.kobold.gui;

import javax.swing.*;
import java.awt.*;
import org.uniba.kobold.game.Item;

public class GuiGame extends JPanel {

    /**
     * Attributes of the class GuiGame
     */
    private static final int MAXITEMS = 5;
    private static JButton[] inventoryButtons = new JButton[MAXITEMS];
    private static JLabel dialogText;
    private static JLayeredPane layeredPane;
    private static JPanel inventoryPanel;
    private static JPanel dialogPanel;
    private static JPanel gamePanel;
    private static final Item[] items = new Item[MAXITEMS];

    /**
     * Constructor of the class GuiGame
     */
    public GuiGame() {
        initComponents();
    }

    /**
     * Method to initialize the components of the class GuiGame
     */
    private void initComponents() {

        //Initializations
        layeredPane = new JLayeredPane();
        inventoryPanel = new JPanel();
        dialogPanel = new JPanel();
        dialogText = new JLabel();
        gamePanel = new GuiBackgroundPanel("/img/BR.png");

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridLayout(5, 3, 5, 5));

        for (int i = 0; i < 5; i++) {
            items[i] = new Item("Item " + i, "Description " + i);
        }
        fillInventory(items);

        dialogPanel.setBackground(new Color(40, 0, 5));

        //Qui andrà l'oggetto dialogo
        dialogText.setText("SMATTO DIOCANE YUPPY YAY");

        layoutManager();
        add(layeredPane, BorderLayout.CENTER);
    }

    private void fillInventory(Item[] items) {
        inventoryPanel.removeAll();
        for(int i = 0; i < 5; i++) {
            //Qui prenderemo l'oggetto dell'inventario
            inventoryButtons[i] = new GuiGenericButton(
                    items[i].getName(),
                    new Color(40, 0, 5),
                    Color.WHITE
            ).getButton();
            inventoryPanel.add(inventoryButtons[i]);
        }
    }

    /**
     * Method to update the layout of the class GuiGame
     */
    private void updateLayout() {
        //Da chiamare quando elimini o aggiungi un oggetto all'inventario
        fillInventory(items);
    }


    /**
     * Method to manage the layout of the class GuiGame
     */
    private void layoutManager() {
        GroupLayout dialogPanelLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
                dialogPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(dialogPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogPanelLayout.setVerticalGroup(
                dialogPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );

        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 554, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(400, 400, Short.MAX_VALUE)
        );

        layeredPane.setLayer(inventoryPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(dialogPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(gamePanel, JLayeredPane.DEFAULT_LAYER);

        GroupLayout layeredPaneLayout = new GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
                layeredPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addComponent(inventoryPanel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layeredPaneLayout.setVerticalGroup(
                layeredPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGroup(layeredPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }
}
