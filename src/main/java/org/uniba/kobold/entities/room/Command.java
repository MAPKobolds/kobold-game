package org.uniba.kobold.entities.room;

import org.javatuples.Pair;

public class Command {
    private Pair<String, String> command;

    public Command(Pair<String, String> command) {
        this.command = command;
    }

    public String getCommand() {
        return command.getValue0();
    }

    public String getArgument() {
        return command.getValue1();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Command) {
            return getCommand().equals(((Command) o).getCommand()) && getArgument().equals(((Command) o).getArgument());
        }

        return false;
    }

}
