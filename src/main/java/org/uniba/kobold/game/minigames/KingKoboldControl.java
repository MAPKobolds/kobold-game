package org.uniba.kobold.game.minigames;

import org.uniba.kobold.entities.inventory.Inventory;
import org.uniba.kobold.parser.ParserOutput;
import org.uniba.kobold.type.Command;
import org.uniba.kobold.util.ColorText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class KingKoboldControl extends MiniGame {

    private boolean secondQ = false;
    private boolean thirdQ = false;
    private boolean fourthQ = false;

    private int normalEnding = 0;

    private List<String> options = new ArrayList<>
            (Arrays.asList(
            "\n Scegli tra le seguenti opzioni: ",
            "\n "   + ColorText.setTextBlue("<esci>")+ " per uscire",
            ColorText.setTextBlue("1)")+ " parla con il re Coboldo di quanto è bello rubare",
            ColorText.setTextBlue("2)")+ " sfidare il Re Coboldo a Sasso Carta Forbici",
            "\t - " + ColorText.setTextBlue("sasso"),
            "\t - " + ColorText.setTextBlue("carta"),
            "\t - " + ColorText.setTextBlue("forbici"),
            ColorText.setTextBlue("3)") +" offri da bere al Re Coboldo",
            ColorText.setTextBlue("4)") +" ispeziona sotto il trono"
            ));

    public KingKoboldControl() {
        this.description = "Benvento davanti a me il sommo Re Coboldo colui che è sopra di tutti e sopra tutto\n" +
                "Cosa ti porta da me :? \n" +
                 String.join("\n", options);


        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("quattro")));
        this.commands.add(new Command("5", Set.of("cinque")));
        this.commands.add(new Command("6", Set.of("sei")));
        this.commands.add(new Command("carta", Set.of("carta")));
        this.commands.add(new Command("sasso", Set.of("sasso")));
        this.commands.add(new Command("forbici", Set.of("forbici")));
        this.commands.add(new Command("exit", Set.of("exit", "e")));
    }

    @Override
    public MiniGameInteraction play(ParserOutput output, Inventory inventory) {
        MiniGameInteraction interaction = new MiniGameInteraction(
                ColorText.setTextBlue("Cosa vuoi fare?"),
                null,
                MiniGameInteractionType.INFO
        );

        switch (output.getCommand().getName()) {
            case "1" -> {

                if (normalEnding < 3) {
                    interaction.setInfo("Il Re Coboldo ti guarda e ti dice che accoglie la tua proposta e ti chiede di parlarne di più\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                    normalEnding++;
                    if (normalEnding == 3) {
                        options.set(2, ColorText.setTextGreen("1) accetti di diventare suo consigliere personale e parli di più sulle tue idee per il regno"));
                        interaction.setInfo("Il Re Coboldo ti guarda e ti invita a diventare suo consigliere personale e ti chiede di parlare di più sulle tue idee per il regno\n" +
                                String.join("\n", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                    interaction.addInfo(String.join("\n", options));
                } else {
                    interaction.setInfo("Il re Coboldo ti prende in braccio dicendo che sei il suo consigliere personale e che ti vuole bene\n");
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }

            case "2" -> {
                if (!secondQ) {
                    interaction.setInfo("Il Re Coboldo ti guarda ed accetta la sfida a Sasso Carta Forbici\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = true;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("\n", options));
            }

            case "sasso", "carta", "forbici" -> {
                if (secondQ) {
                    interaction.setInfo("il Re Coboldo ha scelto sasso e il suo Sasso vince su tutto perchè è il Re.\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = false;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("\n", options));
            }

            case "3" -> {
                if (inventory.contains("GinMoncello")) {
                    interaction.setInfo("Il Re Coboldo ti guarda e ti chiede di bere con lui il GinMoncello ma dopo che lui ha bevuto tutto il GinMoncello cade nel sonno profondo\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                    options.addAll(List.of(ColorText.setTextGreen("5) uccidi il Re Coboldo e prendi il suo trono"),ColorText.setTextGreen( "6) scappa via\n")));
                    thirdQ = true;
                } else {
                    interaction.setInfo("Non hai nulla da offrire al Re Coboldo\n");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("\n", options));
            }

            case "4" -> {
                if (!fourthQ){
                    interaction.setInfo("Vedi sotto il trono dell' olio di macchina \n");
                    options.set(8, ColorText.setTextGreen("4) butta qualcosa di infiammabile sotto il trono"));
                    interaction.addInfo(String.join("\n", options));
                    interaction.setType(MiniGameInteractionType.INFO);
                    fourthQ = true;

                }else{
                    if (inventory.contains("maglio")) {
                        interaction.setInfo(ColorText.setTextGreen("Cerignolus brucia e tu scappi via\n"));
                        interaction.setType(MiniGameInteractionType.EXIT);
                    } else {

                        interaction.setInfo(ColorText.setTextRed("Non hai nulla di infiammabile da buttare sotto il trono\n"));
                        interaction.addInfo(String.join("\n", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                }
            }

            case "5" -> {
                if (thirdQ) {
                    interaction.setInfo(ColorText.setTextGreen("uccidi il Re Coboldo e prendi il suo trono\n"));
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }

            case "6" -> {
                if (thirdQ) {
                    interaction.setInfo(ColorText.setTextGreen("scappi via con la tua auto\n"));
                    interaction.setType(MiniGameInteractionType.EXIT);
                }
            }

            case "exit" -> {
                interaction.setInfo("Hai abbandonato il Re Coboldo\n");
                interaction.setType(MiniGameInteractionType.EXIT);
            }
        }

        return interaction;
    }
}
