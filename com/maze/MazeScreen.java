package com.maze;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

import mazegen.MazeGenerator;

public class MazeScreen implements Move, Screen {

    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    private Character mainc;
    private int[][] raw;

    private boolean succeed = false;

    public MazeScreen() {
        MazeGenerator mazegen = new MazeGenerator(this.WIDTH);
        mazegen.generateMaze();
        raw = mazegen.getIntMaze();

        mainc = new Character(this, 0, 0);

    }

    @Override
    public int getWIDTH() {
        return this.WIDTH;
    }

    @Override
    public int getHEIGHT() {
        return this.HEIGHT;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        if (succeed) {
            terminal.setCursorPosition(0, HEIGHT / 2);
            terminal.write("          You passed the maze!          ");
        } else {
            for (int r = 0; r < this.HEIGHT; ++r) {
                for (int c = 0; c < this.WIDTH; ++c) {
                    terminal.write(getGr(raw[r][c]), c, r,AsciiPanel.white);
                }
            }
            terminal.write(mainc.gr(), mainc.x(), mainc.y(),AsciiPanel.brightRed);
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (succeed) {
            return this;
        }
        switch (key.getKeyCode()) {
        case KeyEvent.VK_UP:
            mainc.moveUp();
            break;
        case KeyEvent.VK_DOWN:
            mainc.moveDown();
            break;
        case KeyEvent.VK_LEFT:
            mainc.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            mainc.moveRight();
            break;
        default:
            break;
        }
        if (mainc.x() + 1 == WIDTH && mainc.y() + 1 == HEIGHT) {
            this.succeed = true;
        }
        return this;
    }

    @Override
    public boolean moveTo(int x, int y) {
        return (posCheck(x, y)) && (raw[y][x] == 1);
    }

    private char getGr(int id) {
        return id == 1 ? (char) 0 : (char) 219;
    }

    private boolean posCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < this.WIDTH && y < this.HEIGHT;
    }
}
