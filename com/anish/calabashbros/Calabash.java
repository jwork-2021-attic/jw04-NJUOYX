package com.anish.calabashbros;

import java.awt.Color;

public class Calabash extends Creature {

    public Calabash(Color color, World world) {
        super(color, (char) 2, world);
    }

    @Override 
    public void moveTo(int ax ,int ay){
        Thing another = world.get(ax, ay);
        int x = this.getX();
        int y = this.getY();
        world.put(this, ax, ay);
        world.put(another, x, y);
    }

}
