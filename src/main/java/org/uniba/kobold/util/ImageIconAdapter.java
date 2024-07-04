package org.uniba.kobold.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * ImageIconAdapter class to serialize and deserialize the ImageIcon
 */
public class ImageIconAdapter extends TypeAdapter<ImageIcon> {
    @Override
    public void write(JsonWriter out, ImageIcon value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = toBufferedImage(value.getImage());
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        out.value(base64Image);
    }

    @Override
    public ImageIcon read(JsonReader in) throws IOException {
        String base64Image = in.nextString();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        return new ImageIcon(image);
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage buffImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        //Draw on the buffered image
        Graphics2D bGr = buffImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return buffImage;
    }
}