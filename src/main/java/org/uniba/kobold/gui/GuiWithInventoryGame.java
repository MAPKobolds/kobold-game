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
    private static final int MAXITEMS = 8;
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

        UtilMusic.initButton(muteMusicButton);
        toolBar.add(muteMusicButton);
        toolBar.add(Box.createHorizontalStrut(50));

        toggleInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toggleInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "noInventoryGame");
        });
        toolBar.add(toggleInventoryButton);
        toolBar.add(Box.createHorizontalStrut(800));

        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toolBar.add(saveButton);
        toolBar.add(Box.createHorizontalStrut(50));

        menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        menuButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Menu");
        });
        toolBar.add(menuButton);

        //Setting the toolbar
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.BLACK);

        mapPanel.setBounds(getWidth() - 200, 0, 200, 200);
        withInventoryLayout();
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        fillInventory();

        dialogPanel.setBackground(new Color(40, 0, 5));
    }

    private void updateLayout() {
        mapPanel.setBounds(getWidth() - 200, 0, 200, 200);
        withInventoryLayout();
    }

    /**
     * Method to fill the inventory
     */
    public void fillInventory() {
        int i = 0;
        for (Item item : items) {
            item = new Item("Item" + i, "Description" + i);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i % 3;
            gridBagConstraints.gridy = i / 3;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = GridBagConstraints.ABOVE_BASELINE;
            gridBagConstraints.weightx = 0.4;
            gridBagConstraints.weighty = 2.5;
            gridBagConstraints.insets = new Insets(6, 3, 6, 3);
            inventoryPanel.add(item.getItemButton(), gridBagConstraints);
            i++;
        }
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
                                .addGap(0, 0, 0)
                                .addComponent(mapPanel, 200, 200, 200)
                                .addContainerGap()
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        gameLayout.setVerticalGroup(
                gameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gameLayout.createSequentialGroup()
                                .addContainerGap()
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
                                .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

}
