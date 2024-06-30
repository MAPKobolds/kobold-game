package org.uniba.kobold.gui;

import org.uniba.kobold.game.Item;
import javax.swing.*;
import java.awt.*;

/**
 * Class GuiWithInventoryGameGame
 */
public class GuiWithInventoryGameGame extends GuiGame {

    /**
     * Attributes of the class GuiWithInventoryGameGame
     */
    private static final int MAXITEMS = 20;
    private static final Item[] items = new Item[MAXITEMS];
    private JPanel inventoryPanel;

    /**
     * Constructor of the class GuiWithInventoryGameGame
     */
    public GuiWithInventoryGameGame() {
       initAdditionalComponents();
    }

    /**
     * Method to initialize the components of the class GuiWithInventoryGameGame
     */
    private void initAdditionalComponents() {
        inventoryPanel = new JPanel();
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        fillInventory();
        gameLayout();
    }

    /**
     * Method to fill the inventory
     */
    private void fillInventory() {
        for (int i = 0; i < items.length; i++) {
            //TODO: Adattare agli item
            items[i] = new Item("Item" + i, "Description" + i);
            items[i].getItemButton().addActionListener(_ -> {
                //TODO: Azione dell'oggetto generica quindi da togliere sta roba
                gamePanel.updateBackground("/img/BR.png");
            });
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridManager(gridBagConstraints, i);
            inventoryPanel.add(items[i].getItemButton(), gridBagConstraints);
        }
    }

    /**
     * Method to manage the grid layout
     * @param gridBagConstraints the grid layout manager
     * @param i the index of the item
     */
    private void gridManager(GridBagConstraints gridBagConstraints, int i) {
        gridBagConstraints.gridx = i % 3;
        gridBagConstraints.gridy = i / 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 2.5;
        gridBagConstraints.insets = new Insets(6, 3, 6, 3);
    }

    /**
     * Method to update the layout of the components of the withInventoryLayout
     */
    public void gameLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toolBar, 30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
