package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.inventory.availableItems.Car;
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
    private MiniGame currentGame = null;
    private Inventory inventory;

    public Game(String playerName ) throws IOException {
        this.timeManager = new TimeManager();
        this.playerName = playerName;
        this.inventory = new Inventory(List.of(new Car()), 800);

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
    }

    public Game(String playerName, RoomsMap roomPath, String time, Inventory inventory) throws IOException {
        this.timeManager = new TimeManager(time);
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        this.playerName = playerName;
        this.roomPath = roomPath;
        this.inventory = inventory;

    }

    public GameCommandResult executeCommand(String command) {
        GameCommandResult result = new GameCommandResult(GameCommandResultType.DESCRIPTION, "");
        Room currentRoom = roomPath.getCurrentRoom();

        List<Item> items = new ArrayList<>(inventory.getItems().stream().toList());
        items.addAll(currentRoom.getItems());

        ParserOutput parsedCommand = parser.parse(
            command,
            (currentGame != null) ? currentGame.getCommands() : currentRoom.getCommands(),
                items,
                currentRoom.getItems()
            );


        if (parsedCommand == null || parsedCommand.getCommand() == null) {
            result.setDescription("Cosa vuoi fare non sto capendo?");
            return result;
        }

        if (currentGame == null) {
            RoomInteractionResult interactionResult = roomPath.getCurrentRoom().generalCommands(parsedCommand, inventory);
            try {
                result = manageRoomInteraction(interactionResult);
            } catch (HttpInternalServerErrorException | HttpNotFoundException | HttpUnavailableException | HttpBadRequestException | HttpForbiddenException e) {
                e.printStackTrace();
            }
        } else {
           MiniGameInteraction miniGameInteraction = currentGame.play(parsedCommand, inventory);
           result = manageMiniGameInteraction(miniGameInteraction);
        }

        return result;
    }

    private GameCommandResult manageMiniGameInteraction(MiniGameInteraction result) {
        GameCommandResult g = new GameCommandResult(GameCommandResultType.DESCRIPTION, result.getInfo());

        if (       result.getType() == MiniGameInteractionType.EXIT
                || result.getType() == MiniGameInteractionType.WIN
                || result.getType() == MiniGameInteractionType.UNLOCK
        ) {
            currentGame = null;
        }

        if (result.getType() == MiniGameInteractionType.WIN) {
            inventory.addPiece((Item) result.getResult());
            g.setGameCommandResultType(GameCommandResultType.REFRESH_INVENTORY);
        }

        if (result.getType() == MiniGameInteractionType.UNLOCK) {
            roomPath.unlockPath(result.getResult().toString());
        }

        return g;
    }

    public GameCommandResult manageRoomInteraction(RoomInteractionResult result) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        GameCommandResult gameCommandResult = new GameCommandResult(GameCommandResultType.DESCRIPTION, "");

        switch (result.getResultType()) {
            case NOTHING -> gameCommandResult.setDescription("Non posso fare nulla");
            case DESCRIPTION -> gameCommandResult.setDescription(result.getSubject());
            case UNLOCK -> {
                this.printAndConsole( "hai sbloccato il percorso per " +ColorText.setTextPurple( result.getSubject()));
                this.roomPath.unlockPath(result.getSubject());
            }
            case ADD_ITEM -> {
                gameCommandResult.setDescription(result.getSubject());
                gameCommandResult.setGameCommandResultType(GameCommandResultType.REFRESH_INVENTORY);

                inventory.addPiece((Item) result.getArgument());
            }
            case MOVE -> {
                try {
                    roomPath.moveTo(result.getSubject());
                    gameCommandResult.setDescription(roomPath.getCurrentRoom().getDescription());
                    gameCommandResult.setGameCommandResultType(GameCommandResultType.MOVE);

                } catch (RoomNotAccessibleError e) {
                    gameCommandResult.setDescription(ColorText.setTextRed("Non puoi andare in quella direzione devi fare prima qualcosa!!!\n"));
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

                gameCommandResult.setDescription(currentGame.getDescription());
            }
        }

        return gameCommandResult;
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

    private void printAndConsole(String arg) {
        System.out.println(arg);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
