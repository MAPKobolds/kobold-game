package org.uniba.kobold.entities.room;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;
import javax.swing.*;
import java.util.*;

/**
 * The type Room.
 */
abstract public class Room {
    private final String name;
    private String description = "";
    private String backgroundImage;
    private final List<Item> items;
    private List<Command> commands = new ArrayList<>(Arrays.asList(
        new Command("guarda giu", Set.of("giu", "terra", "pavimento", "sotto", "guarda sotto" , "guarda terra" , "guarda pavimento")),
        new Command("guarda davanti",Set.of("avanti", "davanti", "dritto", "innanzi" , "guarda innanzi" , "guarda avanti" , "guarda dritto")),
        new Command("guarda dietro", Set.of("dietro", "indietro", "retro", "dietrofront" , "guarda dietro" , "guarda retro" , "guarda indietro")),
        new Command("guarda sopra", Set.of("sopra", "alto", "cielo", "testa", "su" , "guarda su" , "guarda sopra" , "guarda alto" , "guarda cielo" , "guarda testa")),
        new Command("guarda destra", Set.of("destra", "dx", "lato destro" , "guarda destra" , "guarda dx" , "guarda lato destro")),
        new Command("guarda sinistra", Set.of("sinistra", "sx", "lato sinistro", "guarda sinistra" , "guarda sx" , "guarda lato sinistro")),
        new Command("ispeziona", Set.of("esamina")),
        new Command("prendi", Set.of("raccogli", "acquisisci", "prendere", "raccogliere", "acquisire")),
        new Command("aiuto", Set.of("help", "comandi", "aiuto")),
        new Command("soldi", Set.of("denaro"))
    ));

    /**
     * Instantiates a new Room.
     *
     * @param name            the name
     * @param description     the description
     * @param backgroundImage the background image
     * @param items           the items
     * @param commands        the commands
     */
    public Room(String name, String description, String backgroundImage, List<Item> items, List<Command> commands) {
        this.name = name;
        this.description = description;
        this.backgroundImage = backgroundImage;
        this.items = items;
        this.commands.addAll(commands);
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets background image.
     *
     * @return the background image
     */
    public String getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Inspect room interaction result.
     *
     * @param command   the command
     * @param inventory the inventory
     * @return the room interaction result
     */
    public RoomInteractionResult inspect(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        if (command.getItem() != null) {
            if (inventory.contains(command.getItem().getName()))
                result.setSubject(command.getItem().getDescription());
            else {
                result.setSubject("Non hai questo oggetto");
            }
        } else {
            result.setSubject("Non c'è niente da ispezionare");
        }
        return result;
    }

    /**
     * Take room interaction result.
     *
     * @param command   the command
     * @param inventory the inventory
     * @return the room interaction result
     */
    public RoomInteractionResult take (ParserOutput command, Inventory inventory){
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        if (command.getItem() != null) {
            if (items.contains(command.getItem()) && !inventory.contains(command.getItem().getName())) {
                result.setArgument(command.getItem());
                result.setSubject("Hai preso " + ColorText.setTextGreen(command.getItem().getName()));
                result.setResultType(RoomInteractionResultType.ADD_ITEM);
            } else {
                result.setSubject("Non c'è nessun " + ColorText.setTextGreen(command.getItem().getName()) + " qui");
            }
        } else {
            result.setSubject("Cosa vuoi prendere?");
        }
        return result;
    }

    /**
     * Help room interaction result.
     *
     * @param command the command
     * @return the room interaction result
     */
    public RoomInteractionResult help(ParserOutput command){
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        result.setSubject(this.description);
        return result;
    }


    private RoomInteractionResult money(ParserOutput command, Inventory inventory) {
        RoomInteractionResult result = new RoomInteractionResult(RoomInteractionResultType.DESCRIPTION);
        result.setSubject(ColorText.setTextYellow("Hai " + inventory.getMoney() + " euro"));
        return result;
    }

    /**
     * General commands room interaction result.
     *
     * @param command   the command
     * @param inventory the inventory
     * @return the room interaction result
     */
    public RoomInteractionResult generalCommands(ParserOutput command, Inventory inventory){
        return switch (command.getCommand().getName()) {
            case "ispeziona" -> inspect(command, inventory);
            case "prendi" -> take(command, inventory);
            case "soldi" -> money(command,inventory);
            case "aiuto" -> help(command);
            default -> executeCommand(command, inventory);
        };
    }

    /**
     * Sets background image.
     *
     * @param backgroundImage the background image
     */
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Execute command room interaction result.
     *
     * @param command   the command
     * @param inventory the inventory
     * @return the room interaction result
     */
    public abstract RoomInteractionResult executeCommand(ParserOutput command, Inventory inventory);
}
