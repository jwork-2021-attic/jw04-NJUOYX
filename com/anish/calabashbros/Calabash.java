package com.anish.calabashbros;

import java.awt.Color;

public class Calabash extends Creature implements Comparable<Calabash> {

    private int rank_x;
    private int rank_y;
    static private final int WIDTH = 4;

    public Calabash(Color color, int rank, World world) {
        super(color, (char) 2, world);
        this.rank_x = rank%WIDTH;
        this.rank_y = rank/WIDTH;
    }

    public int getRank() {
        return this.rank_x+rank_y*WIDTH;
    }

    @Override
    public String toString() {
        return String.valueOf(getRank());
    }

    @Override
    public int compareTo(Calabash o) {
        return Integer.valueOf(getRank()).compareTo(Integer.valueOf(o.getRank()));
    }

    public void swap(Calabash another) {
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
    }

}
