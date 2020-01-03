package sortAlgorithms;

import utilities.ArrayList;
import utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ShellSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private Integer[] distances;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ShellSort(Comparator<T> comparator, Integer[] distances, ArrayList<T> listToSort) {
        if (comparator == null || listToSort == null || distances == null)
            throw new InvalidParameterException();
        this.comparator = comparator;
        this.distances = distances;
        this.listToSort = new ArrayList<>(listToSort);
        timeToSort = 0;
    }

    public ArrayList<T> getListToSort() {
        return listToSort;
    }

    public long getTimeToSort() {
        return timeToSort;
    }

    @Override
    public ArrayList<T> sort() {
        long start = System.nanoTime();
        int listSize = listToSort.size();
        int numberIteration = 1;
        int actualDistance = distances[distances.length - numberIteration];
        T current;
        int otherIndex;
        while (actualDistance >= 1) {
            for (int i = actualDistance; i < listSize; i++) {
                current = listToSort.get(i);
                otherIndex = i;
                while (otherIndex >= actualDistance && comparator.compare(current, listToSort.get(otherIndex - actualDistance)) < 0) {
                    listToSort.set(otherIndex, listToSort.get(otherIndex - actualDistance));
                    otherIndex -= actualDistance;
                }
                listToSort.set(otherIndex, current);
            }
            actualDistance = distances[distances.length - ++numberIteration];
        }
        long end = System.nanoTime();
        timeToSort = end - start;

        return listToSort;
    }

    // Shell pattern for distance (n/2^k); n- amount of elements to sort, k- iteration number
    public static Integer[] shellDistance(int numberOfElements) {
        if (numberOfElements < 0)
            throw new InvalidParameterException();
        LinkedList<Integer> distances = new LinkedList<>();
        int iteration = 1;
        int generatedDistance;
        do {
            generatedDistance = (int) (numberOfElements / Math.pow(2, iteration));
            distances.add(generatedDistance);
            iteration++;
        } while (generatedDistance > 0);
        Collections.reverse(distances);
        return distances.toArray(new Integer[distances.size()]);
    }

    // Sedgewicka pattern for distance (4^k + 3*2^(k-1) + 1); k- iteration number
    public static Integer[] sedgewickDistance(int numberOfElements) {
        if (numberOfElements < 0)
            throw new InvalidParameterException();
        int generatedDistance = 0;
        LinkedList<Integer> distances = new LinkedList<>();
        distances.add(0);
        distances.add(1);
        int k = 1;
        while (generatedDistance < numberOfElements) {
            generatedDistance = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
            if (generatedDistance < numberOfElements)
                distances.add(generatedDistance);
            k++;
        }
        return distances.toArray(new Integer[distances.size()]);
    }

}