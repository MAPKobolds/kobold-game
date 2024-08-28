/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uniba.kobold.parser;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.type.Command;


/**
 * The type Parser output.
 *
 * @author pierpaolo
 */
public class ParserOutput {

    private Command command;

    private Item item;
    
    private Item invItem;

    /**
     * Instantiates a new Parser output.
     *
     * @param command the command
     * @param item    the item
     */
    public ParserOutput(Command command, Item item) {
        this.command = command;
        this.item = item;
    }

    /**
     * Instantiates a new Parser output.
     *
     * @param command   the command
     * @param item      the item
     * @param invObejct the inv obejct
     */
    public ParserOutput(Command command, Item item, Item invObejct) {
        this.command = command;
        this.item = item;
        this.invItem = invObejct;
    }

    /**
     * Gets command.
     *
     * @return command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Gets item.
     *
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets inv item.
     *
     * @return inv item
     */
    public Item getInvItem() {
        return invItem;
    }

    /**
     * Sets inv item.
     *
     * @param invItem the inv item
     */
    public void setInvItem(Item invItem) {
        this.invItem = invItem;
    }

}
