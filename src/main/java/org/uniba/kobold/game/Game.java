package org.uniba.kobold.game;

import org.javatuples.Pair;
import org.uniba.kobold.api.error.*;
import org.uniba.kobold.api.record.RecordService;
import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.entities.inventory.Item;
import org.uniba.kobold.entities.inventory.availableItems.*;
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

/**
 * The type Game.
 */
public class Game {
    private TimeManager timeManager;
    private final Parser parser;
    private RoomsMap roomPath;
    private String playerName;
    private MiniGame currentGame = null;
    private Inventory inventory;
    private RecordService recordService = new RecordService();

    /**
     * Instantiates a new Game.
     *
     * @param playerName the player name
     * @throws IOException the io exception
     */
    public Game(String playerName ) throws IOException {
        this.timeManager = new TimeManager();
        this.playerName = playerName;
        this.inventory = new Inventory(List.of(), 400);

        IntroductionRoom r0 = new IntroductionRoom();
        StartingRoom r1 = new StartingRoom();
        HallwayRoom r2 = new HallwayRoom();
        TavernRoom r3 = new TavernRoom();
        SquareRoom r4 = new SquareRoom();
        ForgeRoom r5 = new ForgeRoom();
        CircuitRoom r6 = new CircuitRoom();
        PowerHouseRoom r7 = new PowerHouseRoom();
        PalaceEntryRoom r8 = new PalaceEntryRoom();
        ThroneRoom r9 = new ThroneRoom();

        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        roomPath = new RoomsMap(Arrays.asList(
                Pair.with(r0, new RoomPath(Arrays.asList(Pair.with(r1, true)))),
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

    }

    /**
     * Instantiates a new Game.
     *
     * @param playerName the player name
     * @param roomPath   the room path
     * @param time       the time
     * @param inventory  the inventory
     * @throws IOException the io exception
     */
    public Game(String playerName, RoomsMap roomPath, String time, Inventory inventory) throws IOException {
        this.timeManager = new TimeManager(time);
        parser = new Parser(ParserUtils.loadFileListInSet(new File("src/main/resources/stopwords.txt")));
        this.playerName = playerName;
        this.roomPath = roomPath;
        this.inventory = inventory;

    }

    /**
     * Execute command game command result.
     *
     * @param command the command
     * @return the game command result
     */
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
            result.setDescription((currentGame != null ? currentGame.getDescription() : currentRoom.getDescription()) + ColorText.setTextRed("<br>Cosa vuoi fare non sto capendo!"));
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

    /**
     * Manage room interaction game command result.
     *
     * @param result the result
     * @return the game command result
     * @throws HttpInternalServerErrorException the http internal server error exception
     * @throws HttpNotFoundException            the http not found exception
     * @throws HttpUnavailableException         the http unavailable exception
     * @throws HttpBadRequestException          the http bad request exception
     * @throws HttpForbiddenException           the http forbidden exception
     */
    public GameCommandResult manageRoomInteraction(RoomInteractionResult result) throws HttpInternalServerErrorException, HttpNotFoundException, HttpUnavailableException, HttpBadRequestException, HttpForbiddenException {
        GameCommandResult gameCommandResult = new GameCommandResult(GameCommandResultType.DESCRIPTION, "");

        switch (result.getResultType()) {
            case NOTHING -> gameCommandResult.setDescription("Non posso fare nulla");
            case DESCRIPTION -> gameCommandResult.setDescription(result.getSubject());
            case UNLOCK -> {
                gameCommandResult.setDescription( "hai sbloccato il percorso per " + ColorText.setTextPurple( result.getSubject()));
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
                    gameCommandResult.setDescription(roomPath.getCurrentRoom().getDescription() );
                    gameCommandResult.setGameCommandResultType(GameCommandResultType.MOVE);

                } catch (RoomNotAccessibleError e) {
                    gameCommandResult.setDescription(ColorText.setTextRed("Non puoi andare in quella direzione devi fare prima qualcosa!!!<br>"));
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
            case CHANGE_BACKGROUND -> {
                gameCommandResult.setGameCommandResultType(GameCommandResultType.REFRESH_BACKGROUND);
                gameCommandResult.setDescription(result.getSubject());
            }
        }

        return gameCommandResult;
    }

    /**
     * Gets current room description.
     *
     * @return the current room description
     */
    public  GameCommandResult getCurrentRoomDescription() {
        return new GameCommandResult(GameCommandResultType.DESCRIPTION, roomPath.getCurrentRoom().getDescription());
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets current room map.
     *
     * @return the current room map
     */
    public RoomsMap getCurrentRoomMap() {
        return roomPath;
    }

    /**
     * Gets time manager.
     *
     * @return the time manager
     */
    public TimeManager getTimeManager() {
        return timeManager;
    }

    private void printAndConsole(String arg) {
        System.out.println(arg);
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    private GameCommandResult manageMiniGameInteraction(MiniGameInteraction result) {
        GameCommandResult g = new GameCommandResult(GameCommandResultType.DESCRIPTION, result.getInfo());

        if (result.getType() == MiniGameInteractionType.EXIT
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
            g.setDescription(result.getInfo());
            roomPath.unlockPath(result.getResult().toString());
        }

        if (result.getType() == MiniGameInteractionType.END_GAME) {
            g.setGameCommandResultType(GameCommandResultType.END);
            g.setDescription(result.getInfo());
            g.setPath("/img/endings/" + result.getResult().toString() + ".jpg");
            this.recordService.saveGameRecord(this.playerName, timeManager.getMilliSeconds());
        }

        return g;
    }


}
