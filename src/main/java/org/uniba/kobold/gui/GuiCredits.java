package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class GuiCredits
 */
public class GuiCredits extends GuiAbstractPanel{

    /**
     * Attributes of the class GuiCredits
     */
    private static final String bgURL = "/img/pporc.png";
    private static final GuiBackgroundPanel backgroundPanel = new GuiBackgroundPanel(bgURL);
    private static final String porcelliURL = "/img/PorcelliToken.png";
    private static final String sgaramellaURL = "/img/SgaramellaToken.png";
    private static final String zippoURL = "/img/ZippoToken.png";
    private static final int tokenSize = 150;
    private static final JPanel sgaramellaPanel = new GuiGenericToken(sgaramellaURL, tokenSize).getToken();
    private static final JPanel porcelliPanel = new GuiGenericToken(porcelliURL, tokenSize).getToken();
    private static final JPanel zippoPanel = new GuiGenericToken(zippoURL, tokenSize).getToken();
    private static JButton menuButton;
    private static JLabel creditsText;
    private static final JToggleButton muteMusicButton = new JToggleButton();


    /**
     * Constructor of the class GuiCredits
     */
    public GuiCredits() {
        initTokens();
        panelManager();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                UtilMusic.initButton(muteMusicButton);
            }
        });
    }

    /**
     * Method to initialize the tokens of the class GuiCredits
     */
    private void initTokens() {
        GuiGenericToken.manageTokenLayout(zippoPanel, tokenSize);
        GuiGenericToken.manageTokenLayout(sgaramellaPanel, tokenSize);
        GuiGenericToken.manageTokenLayout(porcelliPanel, tokenSize);
    }

    /**
     * Method to initialize the components of the class GuiCredits
     */
    @Override
    public void initComponents() {

        //Initializations
        creditsText = new JLabel();
        menuButton = new GuiGenericButton(
                "Torna al Menu",
                new Color(40, 0, 5),
                Color.WHITE,
                new Dimension(800, 100)
        ).getButton();

        //muteMusicButton logic
        UtilMusic.initButton(muteMusicButton);

        //menuButton logic
        menuButton.addActionListener(_ -> {
            CardLayout menu = (CardLayout) getParent().getLayout();
            menu.show(getParent(), "Menu");
        });

        //creditsText settings
        creditsText.setText("Ci siamo impegnati molto porco dio");

        //Adding components to the panel
        add(muteMusicButton);
        add(menuButton);
        add(sgaramellaPanel);
        add(porcelliPanel);
        add(zippoPanel);
        add(creditsText);
        add(backgroundPanel);

        //Background panel management
        super.manageBackgroundLayout(this, backgroundPanel);
    }

    /**
     * Method to update the layout of the class GuiCredits
     */
    @Override
    public void updateLayout() {
        int width = getWidth();
        int height = getHeight();
        int buttonHeight = 50 * height / 600;
        int buttonWidth = 50 * height / 600;
        double widthOffset = width * 0.10;
        double heightOffset = height * 0.20;
        double tokenWidth = width * 0.20;
        double tokenHeight = height * 0.20;
        int offset = 70 * height / 600;

        //This is where the magic happens
        sgaramellaPanel.setBounds((int) widthOffset, (int) heightOffset, (int) tokenWidth, (int) tokenHeight);
        porcelliPanel.setBounds((int) widthOffset + (int) tokenWidth + offset, (int) heightOffset, (int) tokenWidth, (int) tokenHeight);
        zippoPanel.setBounds((int) widthOffset + ((int) tokenWidth + offset) * 2, (int) heightOffset, (int) tokenWidth, (int) tokenHeight);
        creditsText.setBounds((int) widthOffset, (int) (height * 0.60), (int) (width * 0.80), 50);
        menuButton.setBounds((int) widthOffset, (int) (height * 0.85) , (int) (width * 0.80), 50);
        muteMusicButton.setBounds(0, 0, buttonWidth, buttonHeight);
    }
}
