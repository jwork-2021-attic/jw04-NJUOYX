package com.anish.calabashbros;

public class MatrixMaze<T extends Pump> implements Maze<T>{
    public class Status implements Maze.Status{

        private int x;
        private int y;

        private Status(int x ,int y){
            this.x = x;
            this.y = y;
        }

        private Status nextStep(){
            //TODO
            return this;
        }

        @Override
        public void setFirstDirection(){

        }
        
        @Override 
        public Boolean nextDierection(){
            return false;
        }   

        @Override
        public Boolean oneWay(){
            return false;
        }

        @Override
        public String toString(){
            return "";
        }
    }

    private T[][]matrix;
    private int width;
    private final Status entry;

    MatrixMaze(T[][]matrix, int width){
        this.matrix = matrix;
        this.width = width;
        entry = new Status(0,0);

    }

    @Override
    public Maze.Status getEntry(){
        return entry;
    }

    @Override
    public Boolean isExit(Maze.Status s){
        if(s instanceof MatrixMaze.Status){
            Status ms = (Status)s;
            return ms.x + 1 == width && ms.y + 1 == width;
        }
        assert(false);
        return false;
    }

    @Override
    public void setStatus(Maze.Status s){
        if(s instanceof MatrixMaze.Status){
            Status ms = (Status)s;
            
        }
        assert(false);
    }

    @Override
    public Maze.Status nextStep(Maze.Status s){
        if(s instanceof MatrixMaze.Status){
            Status ms = (Status)s;
            return ms.nextStep();
        }
        assert(false);
        return s;
    }

}
