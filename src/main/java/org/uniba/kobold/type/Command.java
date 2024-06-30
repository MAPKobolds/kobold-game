package org.uniba.kobold.type;

import org.javatuples.Pair;

import java.util.Set;

public class Command {
    private Pair<String, String> command;
    private Set<String> alias;

    public Command(Pair<String, String> command , Set<String> alias) {
        this.command = command;
        this.alias = alias;
    }

    public String getCommand() {
        return command.getValue0();
    }

    public String getArgument() {
        return command.getValue1();
    }

    public String getName() {
        return command.getValue0() + " " + command.getValue1();
    }

    public Set<String> getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Command) {
            return getCommand().equals(((Command) o).getCommand()) && getArgument().equals(((Command) o).getArgument());
        }

        return false;
    }

}
