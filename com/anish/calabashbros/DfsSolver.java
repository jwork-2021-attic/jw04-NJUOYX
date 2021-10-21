package com.anish.calabashbros;

public class DfsSolver<T extends Pump> implements Solver<T> {

    private String plan = "";

    private Maze<T> maze;

    @Override
    public void load(Maze<T> maze) {
        this.maze = maze;
    }

    @Override
    public void solve() {
        Maze.Status s = maze.getEntry();
        maze.setStatus(s);
        _solve_(s);
    }

    @Override
    public String getplan() {
        return this.plan;
    }

    private Maze.Status _solve_(Maze.Status status) {
        while (status.oneWay()) {
            status.setFirstDirection();
            status = maze.nextStep(status);
            maze.setStatus(status);
        }

        if(!maze.isExit(status)){
            status.setFirstDirection();
            while(status.nextDierection()){
                Maze.Status res =  _solve_(maze.nextStep(status));
                if(maze.isExit(res)){
                    return res;
                }
            }
            return maze.getEntry();
        }
        else{
            return status;
        }
    }
}
