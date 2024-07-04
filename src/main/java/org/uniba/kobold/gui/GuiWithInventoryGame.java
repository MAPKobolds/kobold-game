package org.uniba.kobold.gui;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Class GuiWithInventoryGame
 */
public class GuiWithInventoryGame extends GuiGame {

    /**
     * Attributes of the class GuiWithInventoryGame
     */
    private static JPanel inventoryPanel = new JPanel();
    private final Set<Item> items = Inventory.getItems();

    /**
     * Constructor of the class GuiWithInventoryGame
     */
    public GuiWithInventoryGame() {
       initAdditionalComponents();
    }

    /**
     * Method to initialize the components of the class GuiWithInventoryGame
     */
    private void initAdditionalComponents() {
        inventoryPanel = new JPanel();
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        fillInventory();
        gameLayout();
    }

    /**
     * Method to update the inventory based on the game
     */
    public void fillInventory() {

        for(Item item: items)
        {
            JButton itemButton;
            itemButton = new GuiObjectButton(item.getName(), item.getImage());
            itemButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        GuiGame.updateGamePanel("/img/modric.png");
                    } else if (e.getClickCount() == 1) {
                        dialogText.setText("<html>" + item.getDescription() + "<html>");
                    }
                }
            });
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridManager(gridBagConstraints, items.size());
            inventoryPanel.add(itemButton, gridBagConstraints);
            gameLayout();
        }
    }

    /**
     * Method to add an item to inventory
     * @param item the item to add
     */
    /*public void addItemToInventory(Item item) {
        JButton itemButton;
        itemButton = new GuiObjectButton(item.getName(), item.getImage());
        itemButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    GuiGame.updateGamePanel("/img/modric.png");
                } else if (e.getClickCount() == 1) {
                    dialogText.setText("<html>" + item.getDescription() + "<html>");
                }
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridManager(gridBagConstraints, inventoryPanel.getComponentCount());
        inventoryPanel.add(itemButton, gridBagConstraints);
        gameLayout();
    }*/

    /**
     * Method to manage the grid layout
     * @param gridBagConstraints the grid layout manager
     * @param i the index of the item
     */
    private static void gridManager(GridBagConstraints gridBagConstraints, int i) {
        gridBagConstraints.gridx = i % 3;
        gridBagConstraints.gridy = i / 3;
        if(i % 3 == 0) gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.ipadx = 50;
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
                                .addComponent(toolBar, 25, 25, 25)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
