package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.inventory.availableItems.Bill;
import org.uniba.kobold.entities.inventory.availableItems.Cloak;
import org.uniba.kobold.entities.room.*;
import org.uniba.kobold.entities.room.avaliableRooms.*;
import org.uniba.kobold.game.minigames.*;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.parser.ParserUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final Parser parser;
    private RoomsMap roomPath;
    private MiniGame currentGame = null;
    private Room currentRoom;

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
                    Arrays.asList(Pair.with(r1, true), Pair.with(r3, false))
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
                    Arrays.asList(Pair.with(r9, true), Pair.with(r4, true))
                )),
                Pair.with(r9, new RoomPath(Arrays.asList(Pair.with(r8, true))))
        ));

        System.out.println(roomPath.getCurrentRoom().getDescription());
    }

    public void executeCommand(String command) {
        currentRoom = roomPath.getCurrentRoom();

        ParserOutput parsedCommand = parser.parse(
            command,
            (currentGame != null) ? currentGame.getCommands() : currentRoom.getCommands(),
            currentRoom.getItems(),
            currentRoom.getItems()
        );

        if (parsedCommand == null || parsedCommand.getCommand() == null) {
            System.out.print("Command not found");

            return;
        }

        if (currentGame == null) {
            RoomInteractionResult result = roomPath.getCurrentRoom().executeCommand(parsedCommand);
            try {
                manageRoomInteraction(result);
            } catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
                e.printStackTrace();
            }
        } else {
           MiniGameInteraction result = currentGame.play(parsedCommand);
           manageMiniGameInteraction(result);
        }
    }

    private void manageMiniGameInteraction(MiniGameInteraction result) {
        System.out.println(result.getInfo());
        MiniGameInteractionType type = result.getType();

        if (result.getType() == MiniGameInteractionType.EXIT || result.getType() == MiniGameInteractionType.WIN_AND_EXIT) {
            currentGame = null;
        }

        if (result.getType() == MiniGameInteractionType.WIN || result.getType() == MiniGameInteractionType.WIN_AND_EXIT) {
            Inventory.addPiece((Item) result.getResult());
        } else {
            Inventory.removePiece((Item) result.getResult());
        }
    }

    public void manageRoomInteraction(RoomInteractionResult result) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        switch (result.getResultType()) {
            case DESCRIPTION -> System.out.println(result.getSubject());
            case UNLOCK -> this.roomPath.unlockPath(result.getSubject());
            case ADD_ITEM -> {
                System.out.println(result.getSubject());
                Inventory.addPiece((Item) result.getArgument());
            }
            case MOVE -> {
                roomPath.moveTo(result.getSubject());
                System.out.println(roomPath.getCurrentRoom().getDescription());
            }
            case PLAY -> {
                switch (result.getSubject()) {
                    case "blackjack" -> {
                        currentGame = new BlackJackControl();
                    }
                    case "rullo" -> {
                        List<Pair<Boolean, Item>> list = Arrays.asList(Pair.with(false, new Bill(20)), Pair.with(true, new Cloak()));
                        currentGame = new SeekerMiniGame(list);
                    }
                }

                System.out.println(currentGame.getDescription());
            }
        }
    }
}

