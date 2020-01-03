package sortAlgorithms;

import utilities.ArrayList;
import utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class BubbleSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BubbleSort(Comparator<T> comparator, ArrayList<T> listToSort) {
        if (comparator == null || listToSort == null)
            throw new InvalidParameterException();
        this.comparator = comparator;
        this.listToSort = new ArrayList<>(listToSort);
        timeToSort = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<T> getListToSort() {
        return listToSort;
    }

    public long getTimeToSort() {
        return timeToSort;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //rationalization:
    // 1) sorted from last change place
    // 2) sorted until any change had done
    @Override
    public ArrayList<T> sort() {
        long start = System.nanoTime();
        int lastSwap = listToSort.size() - 1;
        while (lastSwap > 0) {
            int end = lastSwap;
            lastSwap = 0;
            for (int left = 0; left < end; left++)
                if (comparator.compare(listToSort.get(left), listToSort.get(left + 1)) > 0) {
                    listToSort.swap(left, left + 1);
                    lastSwap = left;
                }
        }
        long end = System.nanoTime();
        timeToSort = end - start;

        return listToSort;
    }
}
