package edu.isu.cs2235.algorithms;

/**
 *
 * @author Isaac Griffith
 */
public interface ArraySearch {

    /**
     * Generic array search method.
     *
     * @param <E> Type of data searched must implement Comparable interface.
     * @param array Array to be search for the provided item
     * @param item Item to be found
     * @return The index of the provided item in the array. Returns -1 if any of
     * the following conditions are met:
     * <ul>
     * <li>The provided array is null</li>
     * <li>The provided array is empty</li>
     * <li>The provided item is null</li>
     * <li>The provided array does not contain the item</li>
     * </ul>
     */
    <E extends Comparable> int search(E[] array, E item);
}
