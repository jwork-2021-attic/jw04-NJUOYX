package com.maze;

public class Character {

    private int x;
    private int y;

    private Move move;

    public Character(Move move,int x ,int y){
        this.move = move;
        this.x = x;
        this.y = y;
    }

    public char gr(){
        return (char)2;
    }

    public int x(){
        return this.x;
    }

    public int y(){
        return this.y;
    }

    public void moveUp(){
        moveStep(x, y-1);
    }

    public void moveDown(){
        moveStep(x, y+1);
    }

    public void moveLeft(){
        moveStep(x-1, y);
    }

    public void moveRight(){
        moveStep(x+1, y);
    }

    private void moveStep(int tx, int ty){
        if(move.moveTo(tx, ty)){
            this.x = tx;
            this.y = ty;
        }
    }
}
