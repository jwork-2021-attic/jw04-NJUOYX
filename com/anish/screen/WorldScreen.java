package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.print.attribute.standard.Copies;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.DfsSolver;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.MatrixMaze;
import com.anish.calabashbros.Maze;
import com.anish.calabashbros.Solver;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

import mazegen.Pump;
import mazegen.MazeGenerator;

public class WorldScreen implements Screen {

    private World world;
    private Maze<Pump>maze;
    private Calabash bro;
    private String[] steps;
    private int width = 40;

    public WorldScreen() {
        MazeGenerator mazegen = new MazeGenerator(width);
        mazegen.generateMaze();

        world = new World(mazegen.getMaze(), width);

        bro = new Calabash(AsciiPanel.brightYellow, world);
        world.put(bro, 0, 0);

        maze = new MatrixMaze<Pump>(mazegen.getMaze(), width);

        Solver<Pump> s = new DfsSolver<>();
        s.load(maze);
        s.solve();
        s.getplan();

        steps = this.parsePlan(s.getplan());
    }

    private String[] parsePlan(String plan) {
        return plan.split(";");
    }

    private void execute(Calabash bro, String step) {
        String[] couple = step.split(",");
        bro.moveTo(Integer.parseInt(couple[0]), Integer.parseInt(couple[1]));
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.steps.length) {
            this.execute(bro, steps[i]);
            i++;
        }

        return this;
    }

}
