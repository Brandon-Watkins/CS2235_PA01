package edu.isu.cs2235.algorithms.impl;

public class BinarySearch implements edu.isu.cs2235.algorithms.ArraySearch{
    @Override
    public <E extends Comparable> int search(E[] array, E item) {
        if (array == null)
            return -1;
        if (item == null)
            return -1;
        if (array.length == 0)
            return -1;
        int n = array.length;
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int index = (low + high) / 2;
            if (item.compareTo(array[index]) == 0)
                return index;
            else if (item.compareTo(array[index]) < 0)
                high = index - 1;
            else if (item.compareTo(array[index]) > 0)
                low = index + 1;
            else {
                System.out.print("Something broke in the BinarySearch Class.");
                return -2;
            }
        }
        return -1;
    }
}
