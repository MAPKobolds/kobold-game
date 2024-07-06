package org.uniba.kobold.guiRef;

import org.uniba.kobold.game.Game;
import org.uniba.kobold.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GuiLoadRef extends JPanel {

    private static final String BACKGROUND_PATH = "/img/pporc.png";
    private JPanel scrollContainer;
    private JButton menuButton;
    private static JPanel savesContainer;
    private JScrollPane scroller;

    public GuiLoadRef() {
        initComponents();
    }

    private void initComponents() {
        scrollContainer = new JPanel();
        scroller = new JScrollPane();
        savesContainer = new JPanel();
        menuButton = new GuiGenericButton(
        "Torna al Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();
        menuButton.addActionListener(_ -> GuiHubRef.changeTo(PagesEnum.MENU, null));

        scrollContainer.setLayout(new BoxLayout(scrollContainer, BoxLayout.Y_AXIS));
        scrollContainer.setOpaque(false);

        savesContainer.setBackground(Color.BLACK);
        scroller.setOpaque(false);
        scroller.getViewport().setOpaque(false);
        scroller.setBorder(null);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        initSaves();
        setLayout();
    }

    private void initSaves() {
        List<GameSaveInstance> saves = GameSave.getSavingName();
        savesContainer.setLayout(new BoxLayout(savesContainer, BoxLayout.Y_AXIS));
        savesContainer.removeAll();

        for(GameSaveInstance save : saves) {
            JPanel savePanel = createSavePanel(save);
            savesContainer.add(savePanel);
        }
        savesContainer.revalidate();
        savesContainer.repaint();
    }

    private JPanel createSavePanel(GameSaveInstance save) {
        JPanel savePanel = new JPanel(new BorderLayout());
        savePanel.setBackground(new Color(40, 0, 5));
        savePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        savePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        //Initializations
        JButton loadButton = new GuiGenericButton(
                "Carica",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(100, 50)
        ).getButton();
        JButton deleteButton = new GuiGenericButton(
                "Elimina",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(100, 50)
        ).getButton();
        JLabel loadInfoLabel = new JLabel();

        //loadButton logic
        loadButton.addActionListener(_ -> {
            try {
                this.loadScreen(save);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //deleteButton settings
        deleteButton.addActionListener(_ -> {
            try {
                this.deleteGame(save);

                this.initSaves();
            } catch (Error e) {
                System.err.println(e.getMessage());
            }
        });

        //loadInfoLabel settings
        loadInfoLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        loadInfoLabel.setForeground(Color.WHITE);
        loadInfoLabel.setText(save.getPlayerName() + "-" + save.getSaveDate());
        loadInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        savePanel.add(loadInfoLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loadButton);
        buttonPanel.add(deleteButton);
        savePanel.add(buttonPanel, BorderLayout.EAST);
        return savePanel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_PATH))).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    private void setLayout() {
        scroller.setViewportView(savesContainer);
        scrollContainer.add(scroller);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(237, 237)
                                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 530, Short.MAX_VALUE)
                                .addGap(237, 237, 237))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollContainer, GroupLayout.PREFERRED_SIZE, 534, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(menuButton, GroupLayout.DEFAULT_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
        );
    }

    private void deleteGame(GameSaveInstance save) {
        Object[] options = {"Sì", "No"};
        int response = JOptionPane.showOptionDialog(
                null,
                "Vuoi eliminare questo salvataggio?",
                "Elimina",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (response != JOptionPane.YES_OPTION) {
            throw new Error("Cannot open dialog");
        }

        try {
            GameSave.deleteSave(save);
            JOptionPane.showMessageDialog(null, "File cancellato correttamente",
                    "Conferma", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            throw new Error("Cannot delete file");
        }
    }

    private void loadScreen(GameSaveInstance save) throws IOException {
        Object[] options = {"Sì", "No"};
        int response = JOptionPane.showOptionDialog(
                null,
                "Vuoi caricare questo salvataggio?",
                "Carica",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (response == JOptionPane.YES_OPTION) {
            GameState g = GameConverter.deserialize(save.getFilePath());


            GuiHubRef.changeTo(PagesEnum.NEW_GAME, new Game("CIAO"));
        } else {
            throw new Error("Cannot open dialog");
        }
    }
}
