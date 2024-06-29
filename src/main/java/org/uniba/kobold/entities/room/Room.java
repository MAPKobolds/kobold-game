package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import org.uniba.kobold.entities.item.Item;
import java.util.List;

abstract public class Room {
    private final String name;
    private final List<Item> items;
    private final List<Character> characters;
    private final List<Pair<String, String>> commands;

    public Room(String name, List<Item> items, List<Character> characters, List<Pair<String, String>> commands) {
        this.name = name;
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

    abstract void interact(String command);
}
