package org.uniba.kobold.gui;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * The type Gui hub.
 */
public class GuiHub extends JFrame {
    private static GuiHub instance;
    private static JPanel masterPanel;
    private static JToggleButton muteMusicButton;
    private static JToolBar toolBar;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    /**
     * Instantiates a new Gui hub.
     */
    public GuiHub() {
        instance = this;
        initComponents();
    }

    /**
     * Is gui active boolean.
     *
     * @return the boolean
     */
    public static boolean isGuiActive() {
        return instance != null;
    }

    private void initComponents() {
        toolBar = new JToolBar();
        muteMusicButton = new JToggleButton();
        masterPanel = new JPanel();


        //JFrame Settings
        setTitle("Cerignolus: Città dei Coboldi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setLogo();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);

        masterPanel = getInitialPanel();
        add(masterPanel);

        UtilMusic music = UtilMusic.getInstance();
        music.start();

        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(147, 119, 90));
        toolBar.setBorder(BorderFactory.createLineBorder(new Color(93, 72, 55), 3));
        UtilMusic.initButton(muteMusicButton);
        toolBar.add(muteMusicButton);

        masterPanel = getInitialPanel();
        add(masterPanel);
        revalidate();
        pack();
        setLayout();
        setLocationRelativeTo(null);
    }

    private void setLogo() {
        URL logo = GuiHub.class.getResource("/img/firemaul.jpg");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);
    }

    private JPanel getInitialPanel() {
        JPanel cards = new JPanel(new CardLayout());

        GuiMenu menuRef = new GuiMenu();
        cards.add(menuRef, "menu");

        return cards;
    }

    private static void switchPanel(JPanel panel, String name) {

        masterPanel.remove(masterPanel.getComponents().length-1);
        masterPanel.add(panel, name);
        masterPanel.revalidate();
        masterPanel.repaint();
    }

    /**
     * Change to.
     *
     * @param page the page
     * @param game the game
     */
    public static void changeTo(PagesEnum page, Game game) {
        switch (page) {
            case MENU:
                GuiHub.switchPanel(new GuiMenu(), page.name());
                break;
            case ACKNOWLEDGEMENT:
                GuiHub.switchPanel(new GuiCredits(), page.name());
                break;
            case GAME_SAVES:
                GuiHub.switchPanel(new GuiLoad(), page.name());
                break;
            case NEW_GAME:
                GuiHub.switchPanel(new GuiGame(game), page.name());
                break;
            case GAME_RECORDS:
                GuiHub.switchPanel(new GuiGameRecord(), page.name());
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    /**
     * Sets game toolbar.
     *
     * @param timerLabel            the timer label
     * @param saveButton            the save button
     * @param menuButton            the menu button
     * @param toggleInventoryButton the toggle inventory button
     */
    public static void setGameToolbar(JLabel timerLabel, JButton saveButton, JButton menuButton, JButton toggleInventoryButton) {
        double widthOffset = WIDTH * 0.005;
        toolBar.removeAll();
        toolBar.add(muteMusicButton);
        toolBar.add(Box.createHorizontalStrut((int) widthOffset));
        toolBar.add(toggleInventoryButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(timerLabel);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(saveButton);
        toolBar.add(Box.createHorizontalStrut((int) widthOffset));
        toolBar.add(menuButton);

        toolBar.revalidate();
        toolBar.repaint();
    }

    /**
     * Sets normal toolbar.
     */
    public static void setNormalToolbar() {
        toolBar.removeAll();
        toolBar.add(muteMusicButton);

        toolBar.revalidate();
        toolBar.repaint();
    }
    private void setLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                        .addComponent(masterPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(masterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        ));
    }
}
