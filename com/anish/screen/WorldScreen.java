package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.Container;
import com.anish.calabashbros.Matrix;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Container<Calabash>mons;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        int m = 16;
        Calabash [][]raw = new Calabash[m][m];
        mons = new Matrix<Calabash>(raw,m);

        Set<Integer>set = new HashSet<>();

        Random random = new Random();

        int [][][]colors = new int[16][16][3];
        for(int i = 0;i<16;++i){
            for(int j = 0;j<16;++j){
                colors[i][j][0] = 16 * i;
                colors[i][j][1] = 16 * j;
                colors[i][j][2] = 140;
            }
        }

        int i = 0;
        int n = mons.length();
        while (set.size() < n) {
            int s = random.nextInt(n);
            Boolean res = set.add(s);
            if (res) {
                mons.set(s, new Calabash(new Color(colors[i/16][i%16][0],colors[i/16][i%16][1],colors[i/16][i%16][2]), i, world));
                world.put(mons.index(s), 12+s%m, 2+s/m);
                i++;
            }
        }

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(mons);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Container<Calabash>mons, String step) {
        String[] couple = step.split("<->");
        getBroByRank(mons, Integer.parseInt(couple[0])).swap(getBroByRank(mons, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Container<Calabash>mons, int rank) {
        for(int i = 0;i<mons.length();++i){
            if(mons.index(i).getRank() == rank){
                return mons.index(i);
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(mons, sortSteps[i]);
            i++;
        }

        return this;
    }

}
