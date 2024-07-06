package org.uniba.kobold.guiRef;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GuiHubRef extends JFrame {

    private static JPanel masterPanel;
    private static JToggleButton muteMusicButton;
    private static JToolBar toolBar;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;


    public GuiHubRef() {
        initComponents();
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
        //music.start();

        toolBar.setRollover(true);
        toolBar.setFloatable(false);
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
        URL logo = GuiHubRef.class.getResource("/img/BR.png");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);
    }

    private JPanel getInitialPanel() {
        JPanel cards = new JPanel(new CardLayout());

        GuiMenuRef menuRef = new GuiMenuRef();
        cards.add(menuRef, "menu");

        return cards;
    }

    private static void switchPanel(JPanel panel, String name) {
        /*if(panel.getComponents().length < 2) {
            throw new Error("There must be at least 2 panels");
        }*/

        masterPanel.remove(masterPanel.getComponents().length-1);
        masterPanel.add(panel, name);
        masterPanel.revalidate();
        masterPanel.repaint();
    }


    public static void changeTo(PagesEnum page) {
        switch (page) {
            case MENU:
                GuiHubRef.switchPanel(new GuiMenuRef(), page.name());
                break;
            case LOADING:
                GuiHubRef.switchPanel(new GuiLoadingScreenRef(), page.name());
                break;
            case ACKNOWLEDGEMENT:
                GuiHubRef.switchPanel(new GuiCreditsRef(), page.name());
                break;
            case GAME_SAVES:
                GuiHubRef.switchPanel(new GuiLoadRef(), page.name());
                break;
            case NEW_GAME:
                GuiHubRef.switchPanel(new GuiGameRef(), page.name());
                break;
        }
    }

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
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(masterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        ));
    }
}
