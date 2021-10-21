package com.anish.calabashbros;

public interface Solver<T extends Pump> {
    public void load(Maze<T> maze);

    public void solve();

    public String getplan();
}
