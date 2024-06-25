package org.uniba.kobold.gui;

import org.uniba.kobold.game.Item;
import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GuiWithInventoryGame extends JPanel {

    /**
     * Attributes of the class GuiWithInventoryGame
     */
    private static final int MAXITEMS = 20;
    private static final Item[] items = new Item[MAXITEMS];
    private static final String backgroundPath = "/img/pporc.png";
    private static final String mapPath = "/img/BR.png";

    private JPanel gamePanel;
    private JToggleButton muteMusicButton;
    private JPanel inventoryPanel;
    private JPanel dialogPanel;
    private JPanel mapPanel;
    private JButton menuButton;
    private JButton saveButton;
    private JButton toggleInventoryButton;
    private JLabel dialogText;
    private JTextField inputField;
    private JToolBar toolBar;

    /**
     * Constructor of the class GuiWithInventoryGame
     */
    public GuiWithInventoryGame() {
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
     * Method to initialize the components of the class GuiWithInventoryGame
     */
    private void initComponents() {
        inventoryPanel = new JPanel();
        dialogPanel = new JPanel();
        dialogText = new JLabel();
        gamePanel = new GuiBackgroundPanel(backgroundPath);
        inputField = new JTextField();
        toolBar = new JToolBar();
        mapPanel = new GuiBackgroundPanel(mapPath);
        muteMusicButton = new JToggleButton();

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

        //Setting the muteMusicButton
        muteMusicButton.setPreferredSize(new Dimension(40, 10));
        UtilMusic.initButton(muteMusicButton);

        //Setting the toggleInventoryButton
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "noInventoryGame");
        });

        //Setting the saveButton
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Setting the menuButton
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Menu");
        });

        //Setting the toolbar
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.BLACK);

        withInventoryLayout();
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        fillInventory();

        dialogPanel.setBackground(new Color(40, 0, 5));
    }

    /**
     * Method to fill the inventory
     */
    private void fillInventory() {
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("Item" + i, "Description" + i);
            items[i].getItemButton().addActionListener(_ -> {
                //Azione dell'oggetto generica quindi da togliere sta roba
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Menu");
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
     * Method to set and update the toolbar
     */
    private void updateToolbar() {
        double widthOffsetSx = this.getWidth() * 0.01;
        toolBar.removeAll();
        toolBar.add(muteMusicButton);
        toolBar.add(toggleInventoryButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(saveButton);
        toolBar.add(Box.createHorizontalStrut((int) widthOffsetSx));
        toolBar.add(menuButton);
        withInventoryLayout();
    }

    /**
     * Method to update the layout of the components of the withInventoryLayout
     */
    public void withInventoryLayout() {
        GroupLayout dialogLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogLayout);
        dialogLayout.setHorizontalGroup(
                dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(dialogLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inputField)
                                        .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        dialogLayout.setVerticalGroup(
                dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(dialogLayout.createSequentialGroup()
                                .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        gamePanel.setBackground(new java.awt.Color(102, 102, 255));

        GroupLayout gameLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gameLayout);
        gameLayout.setHorizontalGroup(
                gameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gameLayout.createSequentialGroup()
                                .addComponent(mapPanel, 200, 200, 200)
                                .addContainerGap()
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        gameLayout.setVerticalGroup(
                gameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gameLayout.createSequentialGroup()
                                .addComponent(mapPanel, 200, 200, 200)
                                .addContainerGap()
                                .addGap(0, 439, Short.MAX_VALUE))
        );
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
