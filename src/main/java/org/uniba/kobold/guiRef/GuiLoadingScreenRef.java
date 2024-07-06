package org.uniba.kobold.guiRef;
import org.uniba.kobold.game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GuiLoadingScreenRef extends JPanel{

    /**
     * Attributes of the class GuiLoadingScreenRef
     */
    private static final String BACKGROUND_PATH = "/img/pporc.png";
    private JProgressBar progressBar;

    public GuiLoadingScreenRef(Game game) {
        initComponents(game);
    }

    public void initComponents(Game game) {
        progressBar = new JProgressBar(0, 100);

        //Progress Bar Settings
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setFont(new Font("Arial", Font.BOLD, 20));
        progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        progressBar.setForeground(new Color(40, 0, 5));
        progressBar.setBackground(Color.BLACK);
        add(progressBar);

        //Progress bar thread logic and card layout management
        Thread progressBarThread = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(progress);
                        progressBar.setString("Loading..." + progress + "%");
                    });
                    Thread.sleep(10);
                }
                progressBar.setString("Kobold is ready!");
                Thread.sleep(1000);

                SwingUtilities.invokeLater(() -> GuiHubRef.changeTo(PagesEnum.NEW_GAME, game));
            } catch (InterruptedException ex) {
                System.err.println("Thread was interrupted: " + ex.getMessage());
            }
        });

        progressBarThread.start();
        setLayout();
    }

    /**
     * Method to paint the background image
     * @param g the graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(BACKGROUND_PATH)));
        Image image = backgroundImage.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void setLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(450, Short.MAX_VALUE)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
        );
    }
}
