package org.uniba.kobold.guiRef;

import org.uniba.kobold.api.record.RecordService;
import org.uniba.kobold.rest.models.Record;
import org.uniba.kobold.util.TimeManager;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiGameRecord extends JPanel {

    private static final String BACKGROUND_PATH = "/img/wall.png";
    private JPanel scrollContainer;
    private JButton menuButton;
    private JPanel recordContainer;
    private JScrollPane scroller;
    private RecordService recordService = new RecordService();

    public GuiGameRecord() {
        initComponents();
    }

    private void initComponents() {
        scrollContainer = new JPanel();
        scroller = new JScrollPane();
        recordContainer = new JPanel();
        menuButton = new GuiGenericButton(
                "Torna al Menu",
                new Color(40, 0, 5),
                Color.WHITE
        ).getButton();
        menuButton.addActionListener(_ -> GuiHubRef.changeTo(PagesEnum.MENU, null));

        scrollContainer.setLayout(new BoxLayout(scrollContainer, BoxLayout.Y_AXIS));
        scrollContainer.setOpaque(false);

        recordContainer.setBackground(Color.BLACK);
        scroller.setOpaque(false);
        scroller.getViewport().setOpaque(false);
        scroller.setBorder(null);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        initRecords();
        setLayout();
    }

    private void initRecords() {
        List<Record> records = recordService.getBestRecord();
        recordContainer.setLayout(new BoxLayout(recordContainer, BoxLayout.Y_AXIS));
        recordContainer.removeAll();

        int index = 1;
        for(Record record : records) {
            JPanel recordPanel = createRecordPanel(record, index);
            recordContainer.add(recordPanel);

            index++;
        }

        recordContainer.revalidate();
        recordContainer.repaint();
    }

    private JPanel createRecordPanel(Record record, int index) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 0, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel loadInfoLabel = new JLabel();

        //loadInfoLabel settings
        loadInfoLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        loadInfoLabel.setForeground(Color.WHITE);
        loadInfoLabel.setText(index + "° posto -> " + record.getName() + ", TEMPO: " + TimeManager.fromLongToString(record.getTime()));
        loadInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panel.add(loadInfoLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        panel.add(buttonPanel, BorderLayout.EAST);
        return panel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_PATH))).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    private void setLayout() {
        scroller.setViewportView(recordContainer);
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

}
