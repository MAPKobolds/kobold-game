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
            ColorText.setTextBlue("esci")+ " per uscire",
            ColorText.setTextBlue("1)")+ " parla con il re Coboldo di quanto è bello rubare",
            ColorText.setTextBlue("2)")+ " sfidare il Re Coboldo a Sasso Carta Forbici",
            " - " + ColorText.setTextBlue("sasso") + " - " + ColorText.setTextBlue("carta") + " - " + ColorText.setTextBlue("forbici"),
            ColorText.setTextBlue("3)") +" offri da bere al Re Coboldo",
            ColorText.setTextBlue("4)") +" ispeziona sotto il trono"
            ));

    public KingKoboldControl() {
        this.description = "Benvento davanti a me il sommo Re Coboldo colui che è sopra di tutti e sopra tutto" +
                " cosa ti porta da me ?" +
                 String.join("<br>", options);


        this.commands.add(new Command("1", Set.of("uno")));
        this.commands.add(new Command("2", Set.of("due")));
        this.commands.add(new Command("3", Set.of("tre")));
        this.commands.add(new Command("4", Set.of("quattro")));
        this.commands.add(new Command("5", Set.of("cinque")));
        this.commands.add(new Command("6", Set.of("sei")));
        this.commands.add(new Command("carta", Set.of("carta")));
        this.commands.add(new Command("sasso", Set.of("sasso")));
        this.commands.add(new Command("forbici", Set.of("forbici")));
        this.commands.add(new Command("esci", Set.of("esci", "e")));
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
                    interaction.setInfo("Il Re Coboldo ti guarda e ti dice che accoglie la tua proposta e ti chiede di parlarne di più<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                    normalEnding++;
                    if (normalEnding == 3) {
                        options.set(1, ColorText.setTextGreen("1) accetti di diventare un suo capitano ."));
                        interaction.setInfo("Il Re Coboldo ti invita a diventare suo capitano e ti chiede di parlare di più sulle tue idee per il regno<br>");
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                    interaction.addInfo(String.join("<br>", options));
                } else {
                    interaction.setInfo("E' il momento della rivincita vai a rubare auto in tutta la Puglia.<br>");
                    interaction.setResult("captain");
                    interaction.setType(MiniGameInteractionType.END_GAME);
                }
            }

            case "2" -> {
                if (!secondQ) {
                    interaction.setInfo("Il Re Coboldo ti guarda ed accetta la sfida a Sasso Carta Forbici<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = true;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("<br>", options));
            }

            case "sasso", "carta", "forbici" -> {
                if (secondQ) {
                    interaction.setInfo("il Re Coboldo ha scelto sasso e il suo Sasso vince su tutto perchè è il Re.<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                    secondQ = false;
                } else {
                    interaction.setInfo("Il Re Coboldo ti guarda e dice che sta già giocando con te<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("<br>", options));
            }

            case "3" -> {
                if (inventory.contains("GinMoncello")) {
                    interaction.setInfo("Il Re Coboldo ti guarda e ti chiede di bere con lui il GinMoncello ma dopo che lui ha bevuto tutto il GinMoncello cade nel sonno profondo<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                    options.addAll(List.of(ColorText.setTextGreen("5) uccidi il Re Coboldo e prendi il suo trono"),ColorText.setTextGreen( "6) scappa via<br>")));
                    thirdQ = true;
                } else {
                    interaction.setInfo("Non hai nulla da offrire al Re Coboldo<br>");
                    interaction.setType(MiniGameInteractionType.INFO);
                }
                interaction.addInfo(String.join("<br>", options));
            }

            case "4" -> {
                if (!fourthQ){
                    interaction.setInfo("Vedi sotto il trono dell' olio di macchina <br>");
                    options.set(5, ColorText.setTextGreen("4) butta qualcosa di infiammabile sotto il trono"));
                    interaction.addInfo(String.join("<br>", options));
                    interaction.setType(MiniGameInteractionType.INFO);
                    fourthQ = true;

                }else{
                    if (inventory.contains("maglio")) {
                        interaction.setInfo(ColorText.setTextGreen("Cerignolus brucia e tu scappi via<br>"));
                        interaction.setResult("fire");
                        interaction.setType(MiniGameInteractionType.END_GAME);
                    } else {

                        interaction.setInfo(ColorText.setTextRed("Non hai nulla di infiammabile da buttare sotto il trono<br>"));
                        interaction.addInfo(String.join("<br>", options));
                        interaction.setType(MiniGameInteractionType.INFO);
                    }
                }
            }

            case "5" -> {
                if (thirdQ) {
                    interaction.setInfo(ColorText.setTextGreen("uccidi il Re Coboldo e prendi il suo trono<br>"));
                    interaction.setResult("drunking");
                    interaction.setType(MiniGameInteractionType.END_GAME);
                }
            }

            case "6" -> {
                if (thirdQ) {
                    interaction.setInfo(ColorText.setTextGreen("Scappi via con la tua auto<br>"));
                    interaction.setResult("escape");
                    interaction.setType(MiniGameInteractionType.END_GAME);
                }
            }

            case "esci" -> {
                interaction.setInfo("Hai abbandonato il Re Coboldo<br>");
                interaction.setType(MiniGameInteractionType.EXIT);
            }

        }
        this.setDescription(String.join("<br>", options));
        return interaction;
    }
}
