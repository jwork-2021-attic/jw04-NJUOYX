package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class Wall extends Thing implements Pump{

    Wall(World world) {
        super(AsciiPanel.cyan, (char) 177, world);
    }

    @Override
    public Boolean pumpable(){
        return false;
    }
}
