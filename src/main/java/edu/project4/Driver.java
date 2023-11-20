package edu.project4;

import edu.project4.fractalGeneration.FractalCreator;

public class Driver {

    public static void main(String[] params){
        FractalCreator creator = new FractalCreator();
        creator.create(1_000_000, 7,50,2000,2000,true,true);
        ImageRenderer.renderImage(creator.pixels);
    }

}
