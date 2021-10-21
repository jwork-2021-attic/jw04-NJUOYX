package com.anish.calabashbros;

public class Matrix<T extends Comparable<T>> implements Container<T>{

    private T[][]matrix;
    private int width;

    public Matrix(T [][]matrix, int width){
        this.matrix = matrix;
        this.width = width;
    }

    @Override
    public T index(int idx){
        int []res = translate(idx);
        return matrix[res[0]][res[1]];
    }

    @Override
    public void set(int idx, T val){
        int []res = translate(idx);
        matrix[res[0]][res[1]] = val;
    }

    @Override
    public int length(){
        return matrix.length*width;
    }

    private int[] translate(int idx){
        int []res = new int[2];
        res[0] = idx/width;
        res[1] = idx%width;
        return res;
    }
}
