package edu.isu.cs2235.algorithms.impl;

public class RecursiveLinearSearch implements edu.isu.cs2235.algorithms.ArraySearch {
    @Override
    public <E extends Comparable> int search(E[] array, E item) {
        if (array == null)
            return -1;
        if (item == null)
            return -1;
        if (array.length == 0)
            return -1;
        return recLinearSearch(array, item, 0);
    }
    public <E extends Comparable> int recLinearSearch(E[] array, E item, int index){
        if (index >= array.length)
            return -1;
        else if (array[index].compareTo(item) == 0)
            return index;
        return recLinearSearch(array, item, (index + 1));
    }
}
