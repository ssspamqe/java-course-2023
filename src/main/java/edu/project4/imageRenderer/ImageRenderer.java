package edu.project4.imageRenderer;

import edu.project4.fractalGeneration.graphics.PixelCanvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageRenderer {
    private ImageRenderer() {
    }

    public static void renderImage(PixelCanvas canvas, Path directoryPath, String fileName, FileFormat fileFormat) {
        if (!Files.isDirectory(directoryPath)) {
            throw new IllegalArgumentException("directoryPath should be a path of some directory");
        }
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillGraphics(canvas, graphics);

        saveFile(image, directoryPath, fileName, fileFormat);
    }

    private static void fillGraphics(PixelCanvas canvas, Graphics graphics) {
        for (int i = 0; i < canvas.getHeight(); i++) {
            for (int j = 0; j < canvas.getWidth(); j++) {
                var pixel = canvas.getPixel(i, j);
                graphics.setColor(pixel.getColor());
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }

    private static void saveFile(BufferedImage image, Path directoryPath, String fileName, FileFormat fileFormat) {
        String fullFileName = directoryPath + "\\" + fileName + "." + fileFormat;
        int i = 0;
        while (Files.exists(Path.of(fullFileName))) {
            i++;
            fullFileName = directoryPath + "\\" + fileName + i + "." + fileFormat;
        }

        try {
            ImageIO.write(image, fileFormat.toString(), new File(fullFileName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
