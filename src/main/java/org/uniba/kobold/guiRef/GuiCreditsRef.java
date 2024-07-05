package org.uniba.kobold.guiRef;

import org.uniba.kobold.gui.GuiGenericButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiCreditsRef extends JPanel {

    private JLabel creditsLabel;
    private JButton menuButton;
    private JPanel porcelliPanel;
    private JPanel sgaramellaPanel;
    private JPanel tokensPanel;
    private JPanel zippoPanel;
    private static final String backgroundPath = "/img/pporc.png";
    private static final String porcelliPath = "/img/PorcelliToken.png";
    private static final String sgaramellaPath = "/img/SgaramellaToken.png";
    private static final String zippoPath = "/img/ZippoToken.png";
    
    /**
     * Creates new form GuiCreditsRef
     */
    public GuiCreditsRef() {
        initComponents();
    }

    private void initComponents() {
        tokensPanel = new JPanel();
        porcelliPanel = new GuiBackgroundRef(porcelliPath);
        sgaramellaPanel = new GuiBackgroundRef(sgaramellaPath);
        zippoPanel = new GuiBackgroundRef(zippoPath);
        creditsLabel = new JLabel();
        menuButton = new GuiGenericButton(
                "Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();

        menuButton.addActionListener(_ -> GuiHubRef.changeTo(PagesEnum.MENU));

        tokensPanel.setOpaque(false);
        porcelliPanel.setOpaque(false);
        sgaramellaPanel.setOpaque(false);
        zippoPanel.setOpaque(false);
        setLayout();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(backgroundPath))).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


    private void setLayout() {
        porcelliPanel.setPreferredSize(new java.awt.Dimension(270, 225));

        GroupLayout porcelliPanelLayout = new GroupLayout(porcelliPanel);
        porcelliPanel.setLayout(porcelliPanelLayout);
        porcelliPanelLayout.setHorizontalGroup(
                porcelliPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        porcelliPanelLayout.setVerticalGroup(
                porcelliPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        sgaramellaPanel.setPreferredSize(new java.awt.Dimension(270, 225));

        GroupLayout sgaramellaPanelLayout = new GroupLayout(sgaramellaPanel);
        sgaramellaPanel.setLayout(sgaramellaPanelLayout);
        sgaramellaPanelLayout.setHorizontalGroup(
                sgaramellaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        sgaramellaPanelLayout.setVerticalGroup(
                sgaramellaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        zippoPanel.setPreferredSize(new java.awt.Dimension(270, 225));

        GroupLayout zippoPanelLayout = new GroupLayout(zippoPanel);
        zippoPanel.setLayout(zippoPanelLayout);
        zippoPanelLayout.setHorizontalGroup(
                zippoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        zippoPanelLayout.setVerticalGroup(
                zippoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 225, Short.MAX_VALUE)
        );

        GroupLayout tokensPanelLayout = new GroupLayout(tokensPanel);
        tokensPanel.setLayout(tokensPanelLayout);
        tokensPanelLayout.setHorizontalGroup(
                tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tokensPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(porcelliPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sgaramellaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zippoPanel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );
        tokensPanelLayout.setVerticalGroup(
                tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(zippoPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sgaramellaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(porcelliPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(creditsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tokensPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(70, 70, 70))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(tokensPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(creditsLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
        );
    }
}
