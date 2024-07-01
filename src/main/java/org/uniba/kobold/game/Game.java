package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.entities.room.*;
import org.uniba.kobold.entities.room.avaliableRooms.HallwayRoom;
import org.uniba.kobold.entities.room.avaliableRooms.PubRoom;
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
    private RoomsMap roomPath;

    public Game() throws IOException {
        StartingRoom r1 = new StartingRoom();
        HallwayRoom r2 = new HallwayRoom();
        PubRoom r3 = new PubRoom();

        roomPath = new RoomsMap(Arrays.asList(
            Pair.with(r1, new RoomPath(Arrays.asList(Pair.with(r2, true)))),
            Pair.with(r2, new RoomPath(Arrays.asList(Pair.with(r1, true), Pair.with(r3, false)))),
            Pair.with(r3, new RoomPath(Arrays.asList()))
        ));
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));

        System.out.println(roomPath.getCurrentRoom().getDescription());
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

        String[] splitCommand = parsedCommand.getCommand().getName().split(" ");
        if(splitCommand[0].equals("vai")) {
            try {
                roomPath.moveTo(splitCommand[1]);

                System.out.println(roomPath.getCurrentRoom().getDescription());
            } catch (RoomNotAccessibleError e) {
                System.out.print("Non puoi andarci");
            }

            return;
        }

        RoomInteractionResult result = roomPath.getCurrentRoom().executeCommand(parsedCommand);

        if(result.getResultType() == RoomInteractionResultType.UNLOCK) {
            this.roomPath.unlockPath(result.getSubject());
        }
    }
}
