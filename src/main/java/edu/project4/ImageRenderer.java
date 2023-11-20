package edu.project4;

import edu.project4.fractalGeneration.graphics.PixelCanvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageRenderer {

    public static void renderImage(PixelCanvas canvas) {

        int height = canvas.getHeight();
        int width = canvas.getWidth();


        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var pixel = canvas.getPixel(i,j);
                g.setColor(pixel.getColor());
                g.fillRect(i, j, 1, 1);
            }
        }

        String path = "./sampleFiles/";
        int i = 0;
        String fileName = "img";
        while (Files.exists(Path.of(path + fileName +i+".png"))) {
            i++;
        }
        try {
            ImageIO.write(image, "png", new File(path + fileName + i + ".png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
