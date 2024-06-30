package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.entities.room.Room;
import org.uniba.kobold.entities.room.RoomPath;
import org.uniba.kobold.entities.room.RoomsMap;
import org.uniba.kobold.entities.room.avaliableRooms.StartingRoom;
import org.uniba.kobold.errors.RoomNotAccessibleError;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.parser.ParserUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Game {
    private final Parser parser;
    private RoomsMap roomPath = new RoomsMap(Arrays.asList(
        Pair.with(new StartingRoom(), new RoomPath(Arrays.asList()))
    ));

    public Game() throws IOException {
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
    }

    public void executeCommand(String command) {
        Room currentRoom = roomPath.getCurrentRoom();
        ParserOutput parsedCommand = parser.parse(
            command,
            currentRoom.getCommands(),
            currentRoom.getItems(),
            currentRoom.getItems()
        );

        if(parsedCommand.getCommand() == null) {
            System.out.print("Command not found");

            return;
        }

        String[] splittedCommand = parsedCommand.getCommand().getName().split(" ");
        if(splittedCommand[0].equals("vai")) {
            try {
                roomPath.moveTo(splittedCommand[1]);
            } catch (RoomNotAccessibleError e) {
                System.out.print("Non puoi andarci");
            }

            return;
        }

        roomPath.getCurrentRoom().executeCommand(parsedCommand);
    }
}
