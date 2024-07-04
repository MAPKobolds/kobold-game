package org.uniba.kobold.game;

import org.uniba.kobold.entities.inventory.Item;

public interface GameToGui{

    /**
     * This method is called by the game to update the GUI based on the current game state.
     * @param text the message to be displayed in the GUI
     */
    void updateLabelBasedOnGame(String text);

    /**
     * This method is called by the game to update the GUI based on the current game state.
     * @param imagePath the path to the image to be displayed in the GUI
     */
    void updateImageBasedOnGame(String imagePath);

    /**
     * This method is called by the game to update the GUI based on the current game state.
     * @param item the item to be added to the inventory
     */
    void updateInventoryBasedOnGame(Item item);
}