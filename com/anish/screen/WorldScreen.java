package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[] bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Calabash[16];

        bros[3]     = new Calabash(new Color(245,   222,    179),    1,     world);
        bros[7]     = new Calabash(new Color(244,   164,    96),     2,     world);
        bros[0]     = new Calabash(new Color(210,   180,    140),      3,     world);
        bros[4]     = new Calabash(new Color(210,   105,    30),      4,     world);
        bros[10]    = new Calabash(new Color(178,   34,     34),    5,     world);
        bros[11]    = new Calabash(new Color(173,   127,    168),    6,     world);
        bros[12]    = new Calabash(new Color(165,   42,     42),    7,     world);
        bros[13]    = new Calabash(new Color(233,   150,    122),    8,     world);
        bros[1]     = new Calabash(new Color(250,   128,    114),      9,     world);
        bros[14]    = new Calabash(new Color(255,   160,    122),    10,    world);
        bros[15]    = new Calabash(new Color(255,   165,    0),    11,    world);
        bros[2]     = new Calabash(new Color(255,   140,    0),      12,    world);
        bros[8]     = new Calabash(new Color(255,   127,    80),    13,    world);
        bros[6]     = new Calabash(new Color(240,   128,    128),    14,    world);
        bros[5]     = new Calabash(new Color(255,   99,     71),    15,    world);
        bros[9]     = new Calabash(new Color(255,   69,     0),    16,    world);

        int idx = 0;
        for(int i = 18;i<=21;++i){
            for(int j = 3;j<=6;++j){
                world.put(bros[idx++], i, j);
            }
        }

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");

        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
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
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
