package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

abstract public class Room {
    private final String name;
    private String description = "";
    private final ImageIcon backgroundImage;
    private final List<Item> items;
    private final List<Character> characters;
    private List<Command> commands = new ArrayList<>(Arrays.asList(
        new Command("guarda giù", Set.of("giù", "terra", "pavimento", "sotto")),
        new Command("guarda davanti",Set.of("avanti", "davanti", "dritto", "innanzi")),
        new Command("guarda dietro", Set.of("dietro", "indietro", "retro", "dietrofront")),
        new Command("guarda sopra", Set.of("sopra", "alto", "cielo", "testa")),
        new Command("guarda a destra", Set.of("destra", "dx", "lato destro")),
        new Command("guarda a sinistra", Set.of("sinistra", "sx", "lato sinistro"))
    ));

    public Room(String name, String description, ImageIcon backgroundImage, List<Item> items, List<Character> characters, List<Command> commands) {
        this.name = name;
        this.description = description;
        this.backgroundImage = backgroundImage;
        this.items = items;
        this.characters = characters;
        this.commands.addAll(commands);
    }

    public String getDescription() {
        return description;
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

    public List<Command> getCommands() {
        return commands;
    }

    public abstract void executeCommand(ParserOutput command);
}
