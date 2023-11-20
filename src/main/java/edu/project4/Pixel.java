package edu.project4;

import java.awt.Color;

public class Pixel {
    private Color color = Color.BLACK;
    private int hits = 0;
    public double normal =0;

    public void incrementHits(){
        hits++;
    }
    public int getHits() {
        return hits;
    }
    public void setHits(int hits){
        this.hits = hits;
    }
}
