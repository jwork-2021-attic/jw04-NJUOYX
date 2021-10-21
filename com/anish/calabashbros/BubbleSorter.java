package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private Container<T> container;

    @Override
    public void load(Container<T> container) {
        this.container = container;
    }

    private void swap(int i, int j) {
        T temp;
        temp = container.index(i);
        container.set(i, container.index(j));
        container.set(j, temp);
        plan += "" + container.index(i) + "<->" + container.index(j) + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < container.length() - 1; i++) {
                if (container.index(i).compareTo(container.index(i+1)) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}