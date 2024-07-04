package org.uniba.kobold.gui;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.game.Game;
import org.uniba.kobold.game.GameToGui;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.util.ManageTimer;
import org.uniba.kobold.util.SaveInstance;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Abstract class for the game GUI
 */
public abstract class GuiGame extends JPanel implements GameToGui {

    /**
     * Attributes of the class GuiNoInventoryGameGame
     */
    private static final String backgroundPath = "/img/pporc.png";
    private static final String mapPath = "/img/BR.png";
    private static boolean toggledInventory = true;
    public static JLabel dialogText;
    protected static JLabel timerLabel;
    protected static GuiBackgroundPanel gamePanel = new GuiBackgroundPanel(backgroundPath);
    protected JPanel dialogPanel;
    protected JTextField inputField;
    protected JToolBar toolBar;
    protected JToggleButton muteMusicButton;
    protected JButton saveButton;
    protected JButton menuButton;
    protected JButton toggleInventoryButton;
    protected JPanel mapPanel;

    /**
     * Constructor of the class GuiGame
     */
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

    @Override
    public void updateLabelBasedOnGame(String text) {
        dialogText.setText("<html>" + text + "<html>");
    }

    @Override
    public void updateImageBasedOnGame(String imagePath) {
        updateGamePanel(imagePath);
    }

    @Override
    public void updateInventoryBasedOnGame(Item item) {

    }

    /**
     * Method to initialize the components of the GuiGame class
     */
    protected void initComponents() {
        dialogPanel = new JPanel();
        dialogText = new JLabel();
        inputField = new JTextField();
        toolBar = new JToolBar();
        mapPanel = new GuiBackgroundPanel(mapPath);
        muteMusicButton = new JToggleButton();
        timerLabel = new JLabel();
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

        //Setting the muteMusicButton
        muteMusicButton.setPreferredSize(new Dimension(40, 10));
        UtilMusic.initButton(muteMusicButton);

        //Setting the dialogText
        dialogText.setFont(new Font("Arial", Font.BOLD, 16));
        dialogText.setForeground(Color.WHITE);
        dialogText.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting the inputField
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                inputField.setText("");
                //TODO: metodo input

            }
        });

        //Setting the toggleInventoryButton
        toggleInventoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        toggleInventoryButton.addActionListener(_-> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            if(toggledInventory) {
                cardLayout.show(getParent(), "noInventoryGame");
                toggledInventory = false;
            } else {
                cardLayout.show(getParent(), "Game");
                toggledInventory = true;
            }
        });

        //Setting the timerLabel
        timerLabel.setOpaque(true);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setFocusable(false);
        timerLabel.setText("00:00:00");

        //Setting the saveButton
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        saveButton.addActionListener(_ -> {
            Object[] options = {"Sì", "No"};
            int response = JOptionPane.showOptionDialog(null, "Vuoi salvare la partita?", "Conferma Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == JOptionPane.YES_OPTION) {
                new SaveInstance(Game.getPlayerName());
                JOptionPane.showMessageDialog(null, "Partita salvata con successo!", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //Setting the menuButton
        menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuButton.addActionListener(_ -> {
            Object[] options = {"Sì", "No"};
            int response = JOptionPane.showOptionDialog(null, "Vuoi davvero abbandonare senza salvare?", "Torna al Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == JOptionPane.YES_OPTION) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Menu");
            }
        });

        //Setting the toolbar
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.BLACK);

        dialogPanel.setBackground(new Color(40, 0, 5));
        generalLayout();
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

    public void generalLayout() {
        GroupLayout dialogLayout = new GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogLayout);
        dialogLayout.setHorizontalGroup(
                dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogLayout.createSequentialGroup()
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
                                .addComponent(mapPanel, 250, 250, 250)
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
    }
    /**
     * Method to update the layout of the components of the gameLayout
     */
    public abstract void gameLayout();
}