package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import java.util.ArrayList;

/**
 * The type Mini game.
 */
public abstract class MiniGame {
    /**
     * The Description.
     */
    String description;
    /**
     * The Commands.
     */
    ArrayList<Command> commands = new ArrayList<>();

    /**
     * Play mini game interaction.
     *
     * @param output    the output
     * @param inventory the inventory
     * @return the mini game interaction
     */
    public abstract MiniGameInteraction play(ParserOutput output, Inventory inventory);

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public ArrayList<Command> getCommands() {
        return commands;
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
     * Sets description.
     *
     * @param join the join
     */
    protected void setDescription(String join) {
        this.description = join;
    }
}
