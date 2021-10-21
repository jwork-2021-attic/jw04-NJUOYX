package com.anish.calabashbros;

import java.util.Queue;

public class MatrixMaze<T extends Pump> implements Maze<T>{
    public class Status implements Maze.Status{

        private int x;
        private int y;
        private int cur_dir;
        private Queue<Integer> possible_dir;
        private Status from;

        private final int[][]dirs = {{0,-1},{0,1},{-1,0},{1,0}};
        private final String[]dirstr = {"UP","DOWN","LEFT","RIGHT"};

        private Status(int x ,int y){
            this.x = x;
            this.y = y;
        }

        private Status nextStep(int dir){
            //TODO
            return this;
        }
        private Boolean equalTo(Status other){
            return this.x == other.x && this.y == other.y;
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
            return String.format("%d,%d.", x, y);
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
            for(int i = 0;i<4;++i){
                Status res = ms.nextStep(i);
                if(!(res.equals(ms.from))&&matrix[res.y][res.x].pumpable()){
                    ms.possible_dir.add(i);
                }
            }
        }
        assert(false);
    }

    @Override
    public Maze.Status nextStep(Maze.Status s){
        if(s instanceof MatrixMaze.Status){
            Status ms = (Status)s;
            return ms.nextStep(ms.cur_dir);
        }
        assert(false);
        return s;
    }

}
