package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySearch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac Griffith
 */
public class BinarySearchTest {

    private ArraySearch fixture;

    public BinarySearchTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fixture = new BinarySearch();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class BinarySearch.
     */
    @Test
    public void testSearch() {
        Integer[] array = {1, 2, 3, 4, 5};
        int val = fixture.search(array, 3);
        assertEquals("Did not return correct index for item in array", 2, val);
    }

    @Test
    public void testSearch_2() {
        int val = fixture.search(null, 0);
        assertEquals("Did not return -1 for null array", -1, val);
    }

    @Test
    public void testSearch_3() {
        int val = fixture.search(new Integer[0], 0);
        assertEquals("Did not return -1 for empty array", -1, val);
    }

    @Test
    public void testSearch_4() {
        Integer[] array = {1, 2, 3, 4, 5};
        int val = fixture.search(array, null);
        assertEquals("Did not return -1 for null search term", -1, val);
    }

    @Test
    public void testIterativeBinarySearch_5() {
        Integer[] array = {1, 2, 3, 4, 5};
        int val = fixture.search(array, 0);
        assertEquals("Did not return -1 for item not in array", -1, val);
    }

}
