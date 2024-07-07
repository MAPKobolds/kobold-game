package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.room.*;
import org.uniba.kobold.entities.room.avaliableRooms.*;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.parser.ParserUtils;
import org.uniba.kobold.util.TimeManager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Game {
    private TimeManager timeManager;
    private final Parser parser;
    private RoomsMap roomPath;
    private String playerName;
    private ToGui toGui = new ToGui();

    public Game(String playerName) throws IOException {
        this.timeManager = new TimeManager();
        this.playerName = playerName;

        StartingRoom r1 = new StartingRoom();
        HallwayRoom r2 = new HallwayRoom();
        PubRoom r3 = new PubRoom();
        SquareRoom r4 = new SquareRoom();
        ForgeRoom r5 = new ForgeRoom();
        CircuitRoom r6 = new CircuitRoom();
        PowerHouseRoom r7 = new PowerHouseRoom();
        PalaceEntryRoom r8 = new PalaceEntryRoom();
        ThroneRoom r9 = new ThroneRoom();
        //TODO: Qui per output in gui

        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        roomPath = new RoomsMap(Arrays.asList(
            Pair.with(r1, new RoomPath(Arrays.asList(Pair.with(r2, true)))),
            Pair.with(r2, new RoomPath(
                    Arrays.asList(
                        Pair.with(r1, true),
                        Pair.with(r3, false)
                    )
            )),
            Pair.with(r3, new RoomPath(Arrays.asList(Pair.with(r4, true)))),
            Pair.with(r4, new RoomPath(
                Arrays.asList(
                    Pair.with(r3, true),
                    Pair.with(r5, true),
                    Pair.with(r6, true),
                    Pair.with(r7, true),
                    Pair.with(r8, false)
                )
            )),
            Pair.with(r5, new RoomPath(Arrays.asList(Pair.with(r4, true)))),
            Pair.with(r6, new RoomPath(Arrays.asList(Pair.with(r4, true)))),
            Pair.with(r7, new RoomPath(Arrays.asList(Pair.with(r4, true)))),
            Pair.with(r8, new RoomPath(
                Arrays.asList(
                    Pair.with(r9, true),
                    Pair.with(r4, true)
                ))),
            Pair.with(r9, new RoomPath(Arrays.asList(Pair.with(r8, true))))
        ));

        //TODO: Qui per output in gui
        this.printAndConsole(this.roomPath.getCurrentRoom().getDescription());
    }

    public Game(String playerName, RoomsMap roomPath, String time) throws IOException {
        this.timeManager = new TimeManager(time);
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        this.playerName = playerName;
        this.roomPath = roomPath;

        this.printAndConsole(this.roomPath.getCurrentRoom().getDescription());
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

        RoomInteractionResult result = roomPath.getCurrentRoom().executeCommand(parsedCommand);

        switch (result.getResultType()) {
            case DESCRIPTION -> this.printAndConsole(result.getSubject());
            case UNLOCK -> this.roomPath.unlockPath(result.getSubject());
            case ADD_ITEM -> {
                this.printAndConsole(result.getSubject());
                Inventory.addPiece((Item) result.getArgument());
            }
            case MOVE -> {
                roomPath.moveTo(result.getSubject());
                this.printAndConsole(roomPath.getCurrentRoom().getDescription());
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public RoomsMap getCurrentRoomMap() {
        return roomPath;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    private void printAndConsole(String string) {
        toGui.updateLabel(string);
        System.out.println(roomPath.getCurrentRoom().getDescription());
    }
}
