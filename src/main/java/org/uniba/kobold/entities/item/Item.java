package org.uniba.kobold.entities.item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private String name;
    private String description;
    private BufferedImage image;

    Item(String name, String description, String imagePath) throws IOException {
        this.name = name;
        this.description = description;
        this.image = ImageIO.read(new File(imagePath));
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }
}
