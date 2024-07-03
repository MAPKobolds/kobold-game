package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.ArrayList;

public abstract class MiniGame {
    String description;
    ArrayList<Command> commands = new ArrayList<>();

    public abstract MiniGameInteraction play(ParserOutput output);

    public void addItem(Item item) {
        Inventory.addPiece(item);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void removeItem(Item item) {
            Inventory.addPiece(item);
    }

    public String getDescription() {
        return description;
    }
}
