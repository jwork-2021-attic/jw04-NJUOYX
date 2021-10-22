package com.anish.calabashbros;

import java.util.LinkedList;
import java.util.Queue;

import mazegen.Pump;

public class MatrixMaze<T extends Pump> implements Maze<T> {

    private static final int[][] dirs = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    private static final String[] dirstr = { "UP", "DOWN", "LEFT", "RIGHT" };

    public class Status implements Maze.Status {

        private int x;
        private int y;
        private int cur_dir = -1;
        private Queue<Integer> possible_dir;
        private int iterator = 0;
        private Status from;

        protected void _init_() {
            possible_dir = new LinkedList<>();
        }

        private Status(int x, int y) {
            _init_();
            this.x = x;
            this.y = y;
        }

        private Status(Status s, int dir) {
            _init_();
            this.x = s.x + dirs[dir][0];
            this.y = s.y + dirs[dir][1];
            from = s;
        }

        private Status nextStep(int dir) {
            return new Status(this, dir);
        }

        private Boolean equalTo(Status other) {
            if (other == null) {
                return false;
            }
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public void setFirstDirection() {
            cur_dir = possible_dir.poll();
            possible_dir.add(cur_dir);
        }

        @Override
        public Boolean nextDierection() {
            if (cur_dir == -1) {
                setFirstDirection();
                return true;
            } else if (iterator < possible_dir.size()) {
                cur_dir = possible_dir.poll();
                possible_dir.add(cur_dir);
                iterator++;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Boolean oneWay() {
            return possible_dir.size() == 1;
        }

        @Override
        public String toString() {
            return String.format("%d,%d;", x, y);
        }

        @Override
        public Boolean noWay() {
            return possible_dir.isEmpty();
        }

        @Override
        public String logOut(){
            if(from == null){
                return this.toString();
            }
            else{
                return from.logOut()+this.toString();
            }
        }

    }

    private T[][] matrix;
    private int width;
    private final Status entry;

    public MatrixMaze(T[][] matrix, int width) {
        this.matrix = matrix;
        this.width = width;
        entry = new Status(0, 0);

    }

    private Boolean checkPos(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < width;
    }

    @Override
    public Maze.Status getEntry() {
        return entry;
    }

    @Override
    public Boolean isExit(Maze.Status s) {
        if (s instanceof MatrixMaze.Status) {
            Status ms = (Status) s;
            return ms.x + 1 == width && ms.y + 1 == width;
        }
        assert (false);
        return false;
    }

    @Override
    public void setStatus(Maze.Status s) {
        if (s instanceof MatrixMaze.Status) {
            Status ms = (Status) s;
            for (int i = 0; i < 4; ++i) {
                Status res = ms.nextStep(i);
                if (!(res.equalTo(ms.from)) && checkPos(res.x, res.y) && !matrix[res.x][res.y].pumpable()) {
                    ms.possible_dir.add(i);
                }
            }
        }
        assert (false);
    }

    @Override
    public Maze.Status nextStep(Maze.Status s) {
        if (s instanceof MatrixMaze.Status) {
            Status ms = (Status) s;
            return ms.nextStep(ms.cur_dir);
        }
        assert (false);
        return s;
    }

}
