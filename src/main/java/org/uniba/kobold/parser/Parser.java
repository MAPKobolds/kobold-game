/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uniba.kobold.parser;

import java.util.List;
import java.util.Set;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.entities.inventory.Item;

/**
 * The type Parser.
 */
public class Parser {

    private final Set<String> stopwords;

    /**
     * Instantiates a new Parser.
     *
     * @param stopwords the stopwords
     */
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForItem(String token, List<Item> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getName().equals(token) || obejcts.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Parse parser output.
     *
     * @param command   the command
     * @param commands  the commands
     * @param Items     the items
     * @param inventory the inventory
     * @return the parser output
     */
    public ParserOutput parse(String command, List<Command> commands, List<Item> Items, List<Item> inventory) {
        List<String> tokens = ParserUtils.parseString(command, stopwords);
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);

            int aa = -1;
            if(tokens.toArray().length > 1) {
                aa = checkForCommand(tokens.get(0) + " " + tokens.get(1), commands);
            }

            if (ic > -1) {
                if (tokens.size() > 1) {
                    int io = checkForItem(tokens.get(1), Items);
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForItem(tokens.get(2), Items);
                    }
                    if (io < 0) {
                        ioinv = checkForItem(tokens.get(1), inventory);
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForItem(tokens.get(2), inventory);
                        }
                    }
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), Items.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), Items.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else if (aa > -1){
                return new ParserOutput(commands.get(aa),null, null);
            }else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
