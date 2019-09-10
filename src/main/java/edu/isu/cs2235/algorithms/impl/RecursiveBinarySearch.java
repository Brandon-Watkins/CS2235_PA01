package edu.isu.cs2235.algorithms.impl;

public class RecursiveBinarySearch implements edu.isu.cs2235.algorithms.ArraySearch {
    @Override
    public <E extends Comparable> int search(E[] array, E item) {
        if (array == null)
            return -1;
        if (item == null)
            return -1;
        if (array.length == 0)
            return -1;
        return recBinarySearch(array, item, 0, (array.length - 1));
    }

    public <E extends Comparable> int recBinarySearch(E[] array, E item, int low, int high){
        int index = (low + high) / 2;
        if (low > high)
            return -1;
        if (item.compareTo(array[index]) == 0)
            return index;
        if (item.compareTo(array[index]) < 0)
            return recBinarySearch(array, item, low, (index - 1));
        else if (item.compareTo(array[index]) > 0)
            return recBinarySearch(array, item, (index + 1), high);
        else {
            System.out.print("Something broke in the RecursiveBinarySearch Class.");
            return -2;
        }
    }
}
