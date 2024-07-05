package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.character.Character;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import javax.swing.*;
import java.util.*;

abstract public class Room {
    private final String name;
    private String description = "";
    private final ImageIcon backgroundImage;
    private final List<Item> items;
    private Map<String, Character> characters = new HashMap<>();
    private List<Command> commands = new ArrayList<>(Arrays.asList(
        new Command("guarda giu", Set.of("giu", "terra", "pavimento", "sotto", "guarda sotto" , "guarda terra" , "guarda pavimento")),
        new Command("guarda davanti",Set.of("avanti", "davanti", "dritto", "innanzi" , "guarda innanzi" , "guarda avanti" , "guarda dritto")),
        new Command("guarda dietro", Set.of("dietro", "indietro", "retro", "dietrofront" , "guarda dietro" , "guarda retro" , "guarda indietro")),
        new Command("guarda sopra", Set.of("sopra", "alto", "cielo", "testa", "su" , "guarda su" , "guarda sopra" , "guarda alto" , "guarda cielo" , "guarda testa")),
        new Command("guarda destra", Set.of("destra", "dx", "lato destro" , "guarda destra" , "guarda dx" , "guarda lato destro")),
        new Command("guarda sinistra", Set.of("sinistra", "sx", "lato sinistro", "guarda sinistra" , "guarda sx" , "guarda lato sinistro")),
        new Command("ispeziona", Set.of("esamina"))
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

    public RoomInteractionResult ispeziona (ParserOutput command) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        if (command.getItem() != null) {
            if (Inventory.contains(command.getItem().getName()))
                result.setSubject(command.getItem().getDescription());
            else {
                result.setSubject("Non hai questo oggetto");
            }
        } else {
            result.setSubject("Non c'è niente da ispezionare");
        }
        return result;
    }

    public RoomInteractionResult prendi (ParserOutput command){
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        if (command.getItem() != null) {
            if (items.contains(command.getItem()) && !Inventory.contains(command.getItem().getName())) {
                Inventory.addPiece(command.getItem());
                result.setSubject("Hai preso " + ColorText.setTextGreen(command.getItem().getName()));
            } else {
                result.setSubject("Non c'è nessun " + ColorText.setTextGreen(command.getItem().getName()) + " qui");
            }
        } else {
            result.setSubject("Cosa vuoi prendere?");
        }
        return result;
    }

    public RoomInteractionResult generalCommands(ParserOutput command){
        return switch (command.getCommand().getName())
        {
            case "ispeziona" -> ispeziona(command);
            case "prendi" -> prendi(command);
            default -> executeCommand(command);
        };
    }

    public abstract RoomInteractionResult executeCommand(ParserOutput command);
}
