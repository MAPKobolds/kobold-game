package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.character.Character;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import javax.swing.*;
import java.util.*;

abstract public class Room {
    private final String name;
    private String description = "";
    private final ImageIcon backgroundImage;
    private final List<Item> items;
    private final Map<String, Character> characters = (Map<String, Character>) new HashSet<>();
    private List<Command> commands = new ArrayList<>(Arrays.asList(
        new Command("guarda giù", Set.of("giù", "terra", "pavimento", "sotto")),
        new Command("guarda davanti",Set.of("avanti", "davanti", "dritto", "innanzi")),
        new Command("guarda dietro", Set.of("dietro", "indietro", "retro", "dietrofront")),
        new Command("guarda sopra", Set.of("sopra", "alto", "cielo", "testa", "sù")),
        new Command("guarda destra", Set.of("destra", "dx", "lato destro")),
        new Command("guarda sinistra", Set.of("sinistra", "sx", "lato sinistro"))
    ));

    public Room(String name, String description, ImageIcon backgroundImage, List<Item> items, List<Character> charactersList, List<Command> commands) {
        this.name = name;
        this.description = description;
        this.backgroundImage = backgroundImage;
        this.items = items;
        charactersList.forEach(c -> characters.put(c.getName(), c));
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

    public Character getCharacterByName(String name) {
        return characters.get(name);
    }

    public Character updateCharacter(String name, Character character) {
        return characters.replace(name, character);
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public abstract void executeCommand(ParserOutput command);
}
