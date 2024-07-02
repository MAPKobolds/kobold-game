package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.entities.room.*;
import org.uniba.kobold.entities.room.avaliableRooms.*;
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
        SquareRoom r4 = new SquareRoom();
        ForgeRoom r5 = new ForgeRoom();
        CircuitRoom r6 = new CircuitRoom();
        PowerHouseRoom r7 = new PowerHouseRoom();
        PalaceEntryRoom r8 = new PalaceEntryRoom();
        ThroneRoom r9 = new ThroneRoom();

        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        roomPath = new RoomsMap(Arrays.asList(
            Pair.with(r1, new RoomPath(Arrays.asList(Pair.with(r2, true)))),
            Pair.with(r2, new RoomPath(
                    Arrays.asList(
                        Pair.with(r1, true),
                        Pair.with(r3, false)
                    )
            )),
            Pair.with(r3, new RoomPath(Arrays.asList())),
            Pair.with(r4, new RoomPath(
                Arrays.asList(
                    Pair.with(r3, true),
                    Pair.with(r5, true),
                    Pair.with(r6, true),
                    Pair.with(r7, true),
                    Pair.with(r8, false)
                )
            )),
            Pair.with(r8, new RoomPath(Arrays.asList(Pair.with(r9, true)))),
            Pair.with(r9, new RoomPath(Arrays.asList(Pair.with(r8, true))))
        ));

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
