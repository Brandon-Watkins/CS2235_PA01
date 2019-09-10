package edu.isu.cs2235.algorithms.impl;

public class LinearSearch implements edu.isu.cs2235.algorithms.ArraySearch{

    @Override
    public <E extends Comparable> int search(E[] array, E item) {
        if (array == null)
            return -1;
        if (item == null)
            return -1;
        if (array.length == 0)
            return -1;
        int n = array.length;
        for (int i = 0; i <= n - 1; i++){
            if (array[i].compareTo(item) == 0)
                return i;
        }
        return -1;
    }
}
