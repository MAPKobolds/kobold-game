package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;

import java.util.List;
import java.util.Set;

public class KingKoboldControl extends MiniGame {

    private boolean firstQ = false;
    private boolean secondQ = false;
    private boolean thirdQ = false;
    private boolean fourthQ = false;

    private int normalEnding = 0;

    private List<String> options = List.of(
            "<esci> per uscire",
            "1) parlare con il Re Coboldo",
            "2) sfidare il Re Coboldo a Sasso Carta Forbici",
            "3) offrire qualcosa al Re Coboldo",
            "4) guardare sotto il trono"
    );

    public void RockRockRockControl() {
        this.description = "Benvento davanti a me il sommo Re Coboldo colui che è sopra di tutti e sopra tutto\n" +
                "Cosa ti porta da me :? \n" +
                "Scegli tra le seguenti opzioni: " + String.join("\n", options);


        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("quattro")));
        this.commands.add(new Command("carta", Set.of("carta")));
        this.commands.add(new Command("sasso", Set.of("sasso")));
        this.commands.add(new Command("forbici", Set.of("forbici")));
        this.commands.add(new Command("exit", Set.of("exit", "e")));
    }

    @Override
    public MiniGameInteraction play(ParserOutput output) {
        MiniGameInteraction interaction = new MiniGameInteraction(
                "Cosa vuoi fare?",
                null,
                MiniGameInteractionType.INFO
        );

        switch (output.getCommand().getName()) {
            case "1" -> {

                if (normalEnding < 3) {
                    interaction.setInfo("Il Re Coboldo ti guarda e ti dice che accoglie la tua proposta e ti chiede di parlarne di più");
                    interaction.setType(MiniGameInteractionType.INFO);
                    normalEnding++;
                    if (normalEnding == 3) {
                        options.set(0, "1) accetti di diventare suo consigliere personale e parli di più sulle tue idee per il regno");
                        interaction.setInfo("Il Re Coboldo ti guarda e ti invita a diventare suo consigliere personale e ti chiede di parlare di più sulle tue idee per il regno" +
                                String.join("\n", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                } else {
                    interaction.setInfo("Il re Coboldo ti prende in braccio dicendo che sei il suo consigliere personale e che ti vuole bene");
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }

            case "2" -> {
                if (!secondQ) {
                    interaction.setInfo("Il Re Coboldo ti guarda ed accetta la sfida a Sasso Carta Forbici");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = true;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
            }

            case "sasso", "carta", "forbici" -> {
                if (secondQ) {
                    interaction.setInfo("il Re Coboldo ha scelto sasso e il suo Sasso vince su tutto perchè è il Re.");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = false;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
            }

            case "3" -> {
                if (Inventory.contains("GinMoncello")) {
                    interaction.setInfo("Il Re Coboldo ti guarda e ti chiede di bere con lui il GinMoncello ma dopo che lui ha bevuto tutto il GinMoncello cade nel sonno profondo");
                    interaction.setType(MiniGameInteractionType.INFO);
                    options.add("5) uccidi il Re Coboldo e prendi il suo trono");
                    options.add("6) scappa via");
                    thirdQ = true;
                } else {
                    interaction.setInfo("Non hai nulla da offrire al Re Coboldo");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
            }

            case "4" -> {
                if (!fourthQ){
                    interaction.setInfo("Vedi sotto il trono dell' olio di macchina ");
                    interaction.setType(MiniGameInteractionType.INFO);
                    fourthQ = true;
                    options.set(4, "4) butta qualcosa di infiammabile sotto il trono");
                }else{
                    if (Inventory.contains("FireMaul")) {
                        interaction.setInfo("Cerignolus brucia e tu scappi via");
                        interaction.setType(MiniGameInteractionType.EXIT);
                    } else {
                        interaction.setInfo("Non hai nulla di infiammabile da buttare sotto il trono");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                }
            }

            case "5" -> {
                if (thirdQ) {
                    interaction.setInfo("uccidi il Re Coboldo e prendi il suo trono");
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }

            case "6" -> {
                if (thirdQ) {
                    interaction.setInfo("scappi via con la tua auto");
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }
        }

        interaction.addInfo(String.join("\n", options));
        return interaction;
    }
}
