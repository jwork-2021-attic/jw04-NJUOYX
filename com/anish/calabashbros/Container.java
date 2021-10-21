package com.anish.calabashbros;

public interface Container<T extends Comparable<T>> {
    public T index(int idx);

    public void set(int idx, T val);

    public int length();
    
}
