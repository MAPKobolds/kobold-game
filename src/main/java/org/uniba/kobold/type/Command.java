package org.uniba.kobold.type;

import java.util.Set;

public class Command {
    private final String command;
    private final Set<String> alias;

    public Command(String command, Set<String> alias) {
        this.command = command;
        this.alias = alias;
    }

    public String getName() {
        return command;
    }

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
