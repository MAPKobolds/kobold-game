package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.room.*;
import org.uniba.kobold.entities.room.avaliableRooms.*;
import org.uniba.kobold.errors.RoomNotAccessibleError;
import org.uniba.kobold.game.minigames.*;
import org.uniba.kobold.parser.Parser;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.parser.ParserUtils;
import org.uniba.kobold.util.ColorText;
import org.uniba.kobold.util.TimeManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private TimeManager timeManager;
    private final Parser parser;
    private RoomsMap roomPath;
    private String playerName;
    private ToGui toGui = new ToGui();
    private MiniGame currentGame = null;

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
                Pair.with(r5, new RoomPath(List.of(Pair.with(r4, true)))),
                Pair.with(r6, new RoomPath(List.of(Pair.with(r4, true)))),
                Pair.with(r7, new RoomPath(List.of(Pair.with(r4, true)))),
                Pair.with(r8, new RoomPath(
                    Arrays.asList(Pair.with(r9, true), Pair.with(r4, true))
                )),
                Pair.with(r9, new RoomPath(List.of(Pair.with(r8, true))))
        ));

        //TODO: Qui per output in gui
        this.printAndConsole(this.roomPath.getCurrentRoom().getDescription());
    }

    public Game(String playerName, RoomsMap roomPath, String time, Inventory inventory) throws IOException {
        this.timeManager = new TimeManager(time);
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        this.playerName = playerName;
        this.roomPath = roomPath;
        Inventory.setInventory(inventory);

        this.printAndConsole(this.roomPath.getCurrentRoom().getDescription());
    }

    public void executeCommand(String command) {
        Room currentRoom = roomPath.getCurrentRoom();

        List<Item> items = new ArrayList<>(Inventory.getInventory());
        items.addAll(currentRoom.getItems());

        ParserOutput parsedCommand = parser.parse(
            command,
            (currentGame != null) ? currentGame.getCommands() : currentRoom.getCommands(),
                items,
                currentRoom.getItems()
            );


        if (parsedCommand == null || parsedCommand.getCommand() == null) {
            System.out.print("Cosa vuoi fare non sto capendo?\n");
            return;
        }

        if (currentGame == null) {
            RoomInteractionResult result = roomPath.getCurrentRoom().generalCommands(parsedCommand);
            try {
                manageRoomInteraction(result);
            } catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
                e.printStackTrace();
            }
        } else {
           MiniGameInteraction result = currentGame.play(parsedCommand);
           manageMiniGameInteraction(result);
        }
        System.out.println();
    }

    private void manageMiniGameInteraction(MiniGameInteraction result) {

        System.out.println(result.getInfo());
        MiniGameInteractionType type = result.getType();

        if (result.getType() == MiniGameInteractionType.EXIT || result.getType() == MiniGameInteractionType.WIN_AND_EXIT) {
            currentGame = null;
        }

        if (result.getType() == MiniGameInteractionType.WIN || result.getType() == MiniGameInteractionType.WIN_AND_EXIT) {
            Inventory.addPiece((Item) result.getResult());
        }

        if (result.getType() == MiniGameInteractionType.UNLOCK) {
            roomPath.unlockPath(result.getResult().toString());
            currentGame = null;
        }
    }

    public void manageRoomInteraction(RoomInteractionResult result) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        switch (result.getResultType()) {
            case NOTHING -> this.printAndConsole("Non posso fare nulla");
            case DESCRIPTION -> this.printAndConsole(result.getSubject());
            case UNLOCK -> this.roomPath.unlockPath(result.getSubject());
            case ADD_ITEM -> {
                System.out.println(result.getSubject());
                Inventory.addPiece((Item) result.getArgument());
            }
            case MOVE -> {
                try {
                    roomPath.moveTo(result.getSubject());
                    this.printAndConsole(roomPath.getCurrentRoom().getDescription());
                } catch (RoomNotAccessibleError e) {
                    this.printAndConsole(ColorText.setTextRed("Non puoi andare in quella direzione devi fare prima qualcosa!!!\n"));
                }
            }
            case PLAY -> {
                switch (result.getSubject()) {
                    case "blackjack" -> currentGame = new BlackJackControl();
                    case "cassiera" -> currentGame = new CashierControl();
                    case "guardie" -> currentGame = new TwinGuardsControl();
                    case "trivia" -> currentGame = new TriviaControl();
                    case "barman" -> currentGame = new BarManControl();
                    case "rullo" -> currentGame = new SeekerGameControl();
                    case "re" -> currentGame = new KingKoboldControl();
                }
                this.printAndConsole(currentGame.getDescription());
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
