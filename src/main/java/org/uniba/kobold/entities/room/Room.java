package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.inventory.Item;
import javax.swing.*;
import java.util.List;

abstract public class Room {
    private final String name;
    private final ImageIcon backgroundImage;
    private final List<Item> items;
    private final List<Character> characters;
    private final List<Command> commands;

    public Room(String name, ImageIcon backgroundImage, List<Item> items, List<Character> characters, List<Command> commands) {
        this.name = name;
        this.backgroundImage = backgroundImage;
        this.items = items;
        this.characters = characters;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public final void interact(Command command) {
        if(!commands.contains(command)) {
            throw new Error("Command not found");
        }

        this.executeCommand(command);
    };

    abstract void executeCommand(Command command);
}
