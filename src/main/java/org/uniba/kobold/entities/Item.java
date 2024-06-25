package org.uniba.kobold.entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private String description;
    private BufferedImage image;

    Item(String description, String imagePath) throws IOException {
        this.description = description;
        this.image = ImageIO.read(new File(imagePath));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
