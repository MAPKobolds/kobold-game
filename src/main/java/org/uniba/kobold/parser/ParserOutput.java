/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uniba.kobold.parser;

import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.type.Command;


/**
 *
 * @author pierpaolo
 */
public class ParserOutput {

    private Command command;

    private Item item;
    
    private Item invItem;

    /**
     *
     * @param command
     * @param item
     */
    public ParserOutput(Command command, Item item) {
        this.command = command;
        this.item = item;
    }

    /**
     *
     * @param command
     * @param item
     * @param invObejct
     */
    public ParserOutput(Command command, Item item, Item invObejct) {
        this.command = command;
        this.item = item;
        this.invItem = invObejct;
    }

    /**
     *
     * @return
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     *
     * @return
     */
    public Item getInvItem() {
        return invItem;
    }

    /**
     *
     * @param invItem
     */
    public void setInvItem(Item invItem) {
        this.invItem = invItem;
    }

}
