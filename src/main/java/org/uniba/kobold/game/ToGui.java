package org.uniba.kobold.game;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.guiRef.GuiGameRef;

import javax.swing.*;

public class ToGui implements GameToGui{

    @Override
    public void updateLabel(String text) {
        SwingUtilities.invokeLater(() -> GuiGameRef.setDialogLabel(text));
    }

    @Override
    public void updateImage(String imagePath) {
        SwingUtilities.invokeLater(() -> GuiGameRef.updateGamePanel(imagePath));
    }

    @Override
    public void addItemGui(Item item) {
//        SwingUtilities.invokeLater(() -> GuiGameRef.addItem(item));
    }
}
