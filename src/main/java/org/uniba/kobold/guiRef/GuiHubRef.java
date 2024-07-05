package org.uniba.kobold.guiRef;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GuiHubRef extends JFrame {

    private JPanel masterPanel;
    private JToggleButton muteMusicButton;
    private JToolBar toolBar;
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;


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
        UtilMusic.initButton(muteMusicButton);
        toolBar.add(muteMusicButton);

        setLayout();
        pack();
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


    private void switchPanel(JPanel panel, String name) {
        if(panel.getComponents().length < 2) {
            throw new Error("There must be at least 2 panels");
        }

        masterPanel.remove(masterPanel.getComponents().length);
        masterPanel.add(panel, name);
    }

    private void setLayout() {
        GroupLayout masterPanelLayout = new GroupLayout(masterPanel);
        masterPanel.setLayout(masterPanelLayout);
        masterPanelLayout.setHorizontalGroup(
                masterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        masterPanelLayout.setVerticalGroup(
                masterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 663, Short.MAX_VALUE)
        );

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
                                .addComponent(masterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }
}
