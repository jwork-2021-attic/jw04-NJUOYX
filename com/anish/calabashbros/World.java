package com.anish.calabashbros;

import mazegen.Pump;

public class World {

    public int WIDTH;
    public int HEIGHT;

    public Tile<Thing>[][] tiles;

    public World(Pump[][] p, int width) {

        this.WIDTH = width;
        this.HEIGHT = width;

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }


        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if (p[i][j].pumpable()) {
                    tiles[i][j].setThing(new Wall(this));
                }
                else{
                    tiles[i][j].setThing(new Floor(this));
                }
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

}
