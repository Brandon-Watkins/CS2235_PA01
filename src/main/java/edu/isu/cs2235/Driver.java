package edu.isu.cs2235;

import edu.isu.cs2235.algorithms.ArraySearch;
import edu.isu.cs2235.algorithms.impl.LinearSearch;
import edu.isu.cs2235.algorithms.impl.BinarySearch;
import edu.isu.cs2235.algorithms.impl.RecursiveLinearSearch;
import edu.isu.cs2235.algorithms.impl.RecursiveBinarySearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.sql.Time;
import java.util.Random;

// experimentation program
public class Driver {
    public Random random = new Random();
    private ArraySearch fixture;


    public static void main(String[] args) {
        Driver d = new Driver();
        d.init();
    }

    public void init() {
        //adjust these values to your desire.
        int beginningArraySize = 2000;
        int stepSize = 100;
        int endingArraySize = 8000;

        // create the array to house the results.
        Integer[][] results = new Integer[(endingArraySize - beginningArraySize) / stepSize + 1][5];
        // call a recursive method to control size-increments of arrays
        increments(beginningArraySize, stepSize, endingArraySize, results, ((endingArraySize - beginningArraySize) / stepSize + 1), 0);// basically controls everything.
        // post results from the results array.
        finalResults(results);//throws IO exception
    }

    public void increments(int indexStart, int stepSize, int indexEnd, Integer[][] results, int spread, int counter){// basically controls everything, keeping track of a counter while executing all required searches.
        if (counter < spread) {
            //indexStart increases with each iteration (by the amount of stepSize)
            //indexStart is the size of the current array
            if (indexStart <= indexEnd) {//if still producing arrays to search through
                // call a method to generate an array of random numbers of this size(indexStart)
                Integer[] randomUnsortedNumbers = new Integer[indexStart];
                generateRandoms(0, indexStart, randomUnsortedNumbers);
                // call a method to order the array
                sort(0, indexStart - 1, 0, indexStart - 1, randomUnsortedNumbers, 1);
                Integer[] randomSortedNumbers = randomUnsortedNumbers;
                // call a method to fill an array with random numbers to search FOR
                Integer[] searchableRandoms = new Integer[randomSortedNumbers.length];
                generateSearchableRandoms(randomSortedNumbers.length, randomSortedNumbers[randomSortedNumbers.length - 1], 0, searchableRandoms);
                // call each of the searching methods to search for the generated number, with start and end times for computing total time.
                results[counter][0] = indexStart;
                results[counter][1] = search(indexStart, randomSortedNumbers, results, new LinearSearch(), 0, searchableRandoms, System.nanoTime());
                results[counter][2] = search(indexStart, randomSortedNumbers, results, new RecursiveLinearSearch(), 0, searchableRandoms, System.nanoTime());
                results[counter][3] = search(indexStart, randomSortedNumbers, results, new BinarySearch(), 0, searchableRandoms, System.nanoTime());
                results[counter][4] = search(indexStart, randomSortedNumbers, results, new RecursiveBinarySearch(), 0, searchableRandoms, System.nanoTime());

                increments(indexStart + stepSize, stepSize, indexEnd, results, spread, counter + 1);// repeats the process until we've produced the set number of arrays
            }
        }
    }

    public void generateRandoms(int index, int arraySize, Integer[] arrayToBuild){// generates randoms to fill up the array that needs to be sorted. index increases with each iteration.
        if (index <= arraySize - 1){
            generateRandoms(index + 1, arraySize, arrayToBuild);
            arrayToBuild[index] = random.nextInt(10000);
        }
    }

    public void sort(int minVal, int maxVal, int minIndex, int maxIndex, Integer[] array, int counter){
        for (int i = 0; i < maxIndex; i++){
            for (int j = minIndex; j < (maxIndex + minIndex) / 2; j++){
                if (array[counter] <= array[minVal]){
                    minVal = counter;
                }
                if (array[counter] >= array[maxVal]){
                    maxVal = counter;
                }
            }
            Integer tempMin = array[minIndex];
            array[minIndex] = array[minVal];
            array[minVal] = tempMin;
            Integer tempMax = array[maxIndex - 1];
            array[maxIndex - 1] = array[maxVal];
            array[maxVal] = tempMax;
        }
        minIndex ++;
        maxIndex --;
    }

    public void generateSearchableRandoms(int sizeOfArray, Integer maxValueInArray, int counter, Integer[] array){// using this array to ensure all search algorithms have to find the same values.
        if (counter < sizeOfArray){
            array[counter] = random.nextInt(maxValueInArray + 1);// picks a random number to try to find, between 0 and the max value in the array, inclusive
            generateSearchableRandoms(sizeOfArray, maxValueInArray, counter + 1, array);
        }
    }

    public int search(int numberOfSearchesToDo, Integer[] sortedArray, Integer[][] results, ArraySearch fixture, int counter, Integer[]randomNumbersToSearchFor, long startTime){// Need to set a timer on this, and a counter for n.
        if (counter <= numberOfSearchesToDo) {
            if (counter < numberOfSearchesToDo) {
                //fixture = new LinearSearch();
                fixture.search(sortedArray, randomNumbersToSearchFor[counter]);
            }
            search(numberOfSearchesToDo, sortedArray, results, fixture, counter + 1, randomNumbersToSearchFor, startTime);
        }
        //last iteration. stop timer.
        return (int) (System.nanoTime() - startTime);// should return the time it took to complete all iterations, or to find all numbers, with a single search algorithm.
    }

    public void finalResults(Integer[][] results)  {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("results.txt"));
            writer.println("N, IterLin, RecLin, IterBin, RecBin");
            System.out.println("N          IterLin          RecLin         IterBin          RecBin          ");
            saveResults(writer, 0, results);
            writer.close();
        } catch(IOException ioe){
            System.out.println("Exception thrown in finalResults. " + ioe.getMessage());
        }
    }

    public void saveResults(PrintWriter writer, int index, Integer[][] results){
        if (index < results.length){
            System.out.println(results[index][0] + "          " + results[index][1]/results[index][0] + "          " + results[index][2]/results[index][0] +
                    "          " + results[index][3]/results[index][0] + "          " + results[index][4]/results[index][0]);
            writer.println(results[index][0] + ", " + results[index][1]/results[index][0] + ", " + results[index][2]/results[index][0] +
                    ", " + results[index][3]/results[index][0] + ", " + results[index][4]/results[index][0]);
            saveResults(writer, index + 1, results);
        }
    }
}
