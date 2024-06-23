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

    private JPanel gamePanel;
    private JToggleButton muteMusicButton;
    private JPanel inventoryPanel;
    private JPanel dialogPanel;
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

        toggleInventoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toggleInventoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "noInventoryGame");
        });
        toolBar.add(toggleInventoryButton);
        toolBar.add(Box.createHorizontalStrut(800));

        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toolBar.add(saveButton);
        toolBar.add(Box.createHorizontalStrut(50));


        menuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Menu");
        });
        toolBar.add(menuButton);

        //Setting the toolbar
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(40, 0, 5));

        withInventoryLayout();
        inventoryPanel.setBackground(new Color(40, 0, 5));
        inventoryPanel.setLayout(new GridBagLayout());
        fillInventory();

        dialogPanel.setBackground(new Color(40, 0, 5));
    }

    /**
     * Method to fill the inventory
     */
    public void fillInventory() {
        for (int i = 0; i < MAXITEMS; i++) {
            items[i] = new Item("Item" + i, "Description" + i);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i % 3;
            gridBagConstraints.gridy = i / 3;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = GridBagConstraints.ABOVE_BASELINE;
            gridBagConstraints.weightx = 0.4;
            gridBagConstraints.weighty = 2.5;
            gridBagConstraints.insets = new Insets(6, 3, 6, 3);
            inventoryPanel.add(items[i].getItemButton(), gridBagConstraints);
        }
    }

    /**
     * Method to update the layout of the components of the withInventoryLayout
     */
    public void withInventoryLayout() {
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(dialogPanel);
        dialogPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inputField)
                                        .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dialogText, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        gamePanel.setBackground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 674, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(dialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

}
