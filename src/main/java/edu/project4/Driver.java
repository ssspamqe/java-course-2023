package edu.project4;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class Driver {

    public static void main(String[] params){
        FractalCreator creator = new FractalCreator();
        creator.create(100, 4,1000000,100,100);
        ImageRenderer.renderImage(creator.pixels);
    }

}
