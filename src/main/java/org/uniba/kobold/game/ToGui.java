package org.uniba.kobold.game;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.gui.GuiGame;

public class ToGui implements GameToGui{

    @Override
    public void updateLabel(String text) {
        //TODO: GuiGame.dialogText.setText("<html>" + text + "<html>");
        GuiGame.setDialogLabel(text);
    }

    @Override
    public void updateImage(String imagePath) {
        GuiGame.updateGamePanel(imagePath);
    }

    @Override
    public void addItemToInventory(Item item) {
        GuiGame.getInstance().addItem(item);
    }

}
