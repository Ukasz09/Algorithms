package sortAlgorithms;

import utilities.ArrayList;
import utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class InsertSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public InsertSort(Comparator<T> comparator, ArrayList<T> listToSort) {
        if(comparator==null || listToSort==null)
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

    @Override
    public ArrayList<T> sort() {
        long start = System.nanoTime();
        for (int i = 1; i < listToSort.size(); i++) {
            T valueToSort = listToSort.get(i), valueToCompare;
            int j;
            for (j = i; j > 0 && comparator.compare(valueToSort, valueToCompare = listToSort.get(j - 1)) < 0; --j)
                listToSort.set(j, valueToCompare);
            listToSort.set(j, valueToSort);
        }
        long end = System.nanoTime();
        timeToSort = end - start;
        return listToSort;
    }

}
