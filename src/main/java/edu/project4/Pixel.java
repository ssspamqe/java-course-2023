package edu.project4;

public class Pixel {

    private int x;
    private int y;

    public int red = 0;
    public int green = 0;
    public int blue = 0;

    private int hits = 0;

    public double normal =0;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public void incrementHits(){
        hits++;
    }
    public int getHits() {
        return hits;
    }
}
