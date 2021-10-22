package com.anish.calabashbros;

import mazegen.Pump;

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
        s = _solve_(s);
        plan = s.logOut();
    }

    @Override
    public String getplan() {
        return this.plan;
    }

    private Maze.Status _solve_(Maze.Status status) {

        maze.setStatus(status);

        while (status.oneWay()&&!maze.isExit(status)) {
            status.setFirstDirection();
            status = maze.nextStep(status);
            maze.setStatus(status);
        }

        if(!maze.isExit(status)){
            if(status.noWay()){
                return maze.getEntry();
            }
            status.setFirstDirection();
            do{
                Maze.Status res =  _solve_(maze.nextStep(status));
                if(maze.isExit(res)){
                    return res;
                }
            }while(status.nextDierection());
            return maze.getEntry();
        }
        else{
            return status;
        }
    }
}
