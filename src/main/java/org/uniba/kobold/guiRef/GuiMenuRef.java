package org.uniba.kobold.guiRef;

import org.uniba.kobold.gui.GuiGenericButton;

import javax.swing.*;

public class GuiMenuRef extends JPanel{

    private JButton gameStartButton;
    public GuiMenuRef() {
        initComponents();
    }

    /**
     * Method to initialize the components of the MainMenu class
     */
    public void initComponents() {
        gameStartButton = new GuiGenericButton("Ciao");

        gameStartButton.setVisible(true);
        gameStartButton.setBounds(0,0, 400, 400);

        add(gameStartButton);

    }

}
