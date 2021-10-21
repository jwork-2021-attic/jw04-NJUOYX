package com.anish.calabashbros;

public interface Sorter<T extends Comparable<T>> {
    public void load(Container<T> container);

    public void sort();

    public String getPlan();
}
