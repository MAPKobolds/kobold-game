package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class GuiCredits
 */
public class GuiCredits extends JPanel {

    /**
     * Attributes of the class GuiCredits
     */
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel();
    private static final String porcelliURL = "/img/PorcelliToken.png";
    private static final String sgaramellaURL = "/img/SgaramellaToken.png";
    private static final String zippoURL = "/img/ZippoToken.png";
    private static final int tokenSize = 150;
    private static final JPanel sgaramellaPanel = new GuiGenericToken(sgaramellaURL, tokenSize).getToken();
    private static final JPanel porcelliPanel = new GuiGenericToken(porcelliURL, tokenSize).getToken();
    private static final JPanel zippoPanel = new GuiGenericToken(zippoURL, tokenSize).getToken();

    private static JButton menuButton;
    private static JLabel creditsText;
    private static JToggleButton muteMusicButton;


    /**
     * Constructor of the class GuiCredits
     */
    public GuiCredits() {
        initTokens();
        initComponents();

        // Aggiungi un ComponentAdapter per eseguire il codice legato al muteMusicButton quando il componente viene mostrato
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
        });
    }

    /**
     * Method to initialize the tokens of the class GuiCredits
     */
    private void initTokens() {
        GuiGenericToken.manageTokenLayout(zippoPanel, tokenSize);
        GuiGenericToken.manageTokenLayout(sgaramellaPanel, tokenSize);
        GuiGenericToken.manageTokenLayout(porcelliPanel, tokenSize);
    }

    /**
     * Method to initialize the components of the class GuiCredits
     */
    private void initComponents() {
        menuButton = new GuiGenericButton("Torna al Menu").getButton();
        creditsText = new JLabel();
        muteMusicButton = new JToggleButton();

        muteMusicButton.setBounds(0, 0, 50, 50);

        //menuButton logic
        menuButton.addActionListener(_ -> {
            CardLayout menu = (CardLayout) getParent().getLayout();
            menu.show(getParent(), "Menu");
        });

        //Background layout settings
        creditsText.setText("Ci siamo impegnati molto porco dio");

        GroupLayout backgroundLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(sgaramellaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(menuButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(porcelliPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(59, 59, 59)
                                                .addComponent(zippoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGap(127, 127, 127)
                                                .addComponent(creditsText, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(87, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                .addComponent(muteMusicButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(sgaramellaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(zippoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(porcelliPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addComponent(creditsText, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
        );

        //Background group layout manager
        backgroundPanel.setLayout(backgroundLayout);
        GuiBackgroundPanel.manageBackgroundLayout(this, backgroundPanel);
    }
}
