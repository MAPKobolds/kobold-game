package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

abstract public class Room {
    private final String name;
    private String description = "";
    private final ImageIcon backgroundImage;
    private final List<Item> items;
    private final List<Character> characters;
    private List<Command> commands = Arrays.asList(
            new Command("guarda giù", Set.of("giù", "terra", "pavimento", "sotto")),
            new Command("guarda davanti",Set.of("avanti", "davanti", "dritto", "innanzi")),
            new Command("guarda dietro", Set.of("dietro", "indietro", "retro", "dietrofront")),
            new Command("guarda sopra", Set.of("sopra", "alto", "cielo", "testa")),
            new Command("guarda a destra", Set.of("destra", "dx", "lato destro")),
            new Command("guarda a sinistra", Set.of("sinistra", "sx", "lato sinistro")),

            new Command("prendi", Set.of("raccogli", "acquisisci", "cattura")),
            new Command("usa", Set.of("utilizza", "impiega", "applica")),
            new Command("vai", Set.of("muoviti", "spostati", "dirigiti")),
            new Command("ispeziona", Set.of("analizza", "scruta")));

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

    public final void interact(ParserOutput command) {
        if(!commands.contains(command.getCommand())) {
            throw new Error("Command not found");
        }

        this.executeCommand(command);
    };

    protected abstract void executeCommand(ParserOutput command);
}
