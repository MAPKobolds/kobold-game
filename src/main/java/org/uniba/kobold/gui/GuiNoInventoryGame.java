package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GuiNoInventoryGame extends JPanel {

    /**
     * Attributes of the class GuiNoInventoryGame
     */
    private static final String backgroundPath = "/img/pporc.png";
    private static final String mapPath = "/img/BR.png";
    private JPanel gamePanel;
    private JPanel dialogPanel;
    private JPanel mapPanel;
    private JToggleButton muteMusicButton;
    private JButton saveButton;
    private JButton menuButton;
    private JButton toggleInventoryButton;
    private JLabel dialogText;
    private JTextField inputField;
    private JToolBar toolBar;

    /**
     * Constructor of the class GuiNoInventoryGame
     */
    public GuiNoInventoryGame() {
        initComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
            public void componentResized(ComponentEvent e) {
                updateLayout();
            }
        });
    }

    public void initComponents() {
        gamePanel = new GuiBackgroundPanel(backgroundPath);
        dialogPanel = new JPanel();
        mapPanel = new GuiBackgroundPanel(mapPath);
        dialogText = new JLabel();
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

        toggleInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toggleInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_ -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Game");
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

        mapPanel.setBounds(getWidth() - 100, 0, 100, 100);
        noInventoryLayout();
        dialogPanel.setBackground(new Color(40, 0, 5));
    }

    /**
     * Method to update the layout of the components of the gamePanel
     */
    private void updateLayout() {
        mapPanel.setBounds(getWidth() - 200, 0, 200, 200);
        noInventoryLayout();
    }

    /**
     * Method to update the layout of the components of the noInventoryLayout
     */
    public void noInventoryLayout() {

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
                                .addContainerGap()
                                .addComponent(dialogText, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

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
                        .addComponent(dialogPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                        .addComponent(gamePanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dialogPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
}
