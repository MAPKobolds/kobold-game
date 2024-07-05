package org.uniba.kobold.guiRef;

import org.uniba.kobold.util.UtilMusic;

/**
 * Main hub of the game
 */
/*public class GuiHubRef extends JFrame {

    /**
     * Attributes of the GuiHub class
     */
    /*private final static int width = 1000;
    private final static int height = 700;
    public static JPanel masterPanel = new JPanel(new CardLayout());*/

    /**
     * Constructor of the class GuiHub
     */
    /*public GuiHubRef() {
        //JFrame Settings
        setTitle("Cerignolus: Città dei Coboldi");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        //Logo Settings
        URL logo = GuiHubRef.class.getResource("/img/BR.png");
        ImageIcon image = null;
        if (logo != null) {
            image = new ImageIcon(logo);
        }
        setIconImage(image != null ? image.getImage() : null);

        //Add cards to the frame and starts the music
        masterPanel = addMenu();
        add(masterPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        UtilMusic music = UtilMusic.getInstance();
        //music.start();
    }

    private JPanel addMenu() {
        JPanel cards = new JPanel(new CardLayout());

        GuiMenuRef menuRef = new GuiMenuRef();
        cards.add(menuRef, "menu");
        return cards;
    }
}*/

import javax.swing.*;

public class GuiHubRef extends JFrame {

    private JPanel masterPanel;
    private JToggleButton muteMusicButton;
    private JToolBar toolBar;

    public GuiHubRef() {
        initComponents();
    }
    
    private void initComponents() {
        toolBar = new JToolBar();
        muteMusicButton = new JToggleButton();
        masterPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        toolBar.setRollover(true);
        UtilMusic.initButton(muteMusicButton);
        toolBar.add(muteMusicButton);

        GroupLayout jPanel1Layout = new GroupLayout(masterPanel);
        masterPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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

        pack();
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {


    }*/
}
