package org.uniba.kobold.type;

import java.util.Set;

/**
 * The type Command.
 */
public class Command {
    private final String command;
    private final Set<String> alias;

    /**
     * Instantiates a new Command.
     *
     * @param command the command
     * @param alias   the alias
     */
    public Command(String command, Set<String> alias) {
        this.command = command;
        this.alias = alias;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return command;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Command) {
            return ((Command) o).getName().equals(this.getName());
        }
        return false;
    }

}
