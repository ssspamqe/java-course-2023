package edu.project4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageRenderer {
    public static void main(String[] params) {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);

        for (int i = 0; i < 100; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(i, i, 10, 10);
        }

        try {
            ImageIO.write(image, "png", new File("./sampleFiles/img.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void renderImage(List<List<Pixel>> pixels) {
        int height = pixels.size();
        int width = pixels.get(0).size();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var pixel = pixels.get(i).get(j);
                g.setColor(new Color(pixel.red,pixel.green,pixel.blue));
                g.fillRect(i,j,1,1);
            }
        }

        try {
            ImageIO.write(image, "png", new File("./sampleFiles/img.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
