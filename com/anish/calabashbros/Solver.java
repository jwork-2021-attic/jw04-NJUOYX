package com.anish.calabashbros;

import mazegen.Pump;

public interface Solver<T extends Pump> {
    public void load(Maze<T> maze);

    public void solve();

    public String getplan();

}
