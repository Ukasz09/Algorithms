package sortAlgorithms;

import utilities.AbstractList;
import utilities.ArrayList;
import utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class SelectSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SelectSort(Comparator<T> comparator, ArrayList<T> listToSort) {
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
        int size = listToSort.size();
        for (int slot = 0; slot < size - 1; slot++) {
            int smallestId = findIdMinValue(listToSort, slot);
            listToSort.swap(smallestId, slot);
        }
        long end = System.nanoTime();
        timeToSort = end - start;

        return listToSort;
    }

    private int findIdMinValue(AbstractList<T> list, int startId) {
        int smallestId = startId;
        for (int checkValueId = startId + 1; checkValueId < list.size(); checkValueId++)
            if (comparator.compare(list.get(checkValueId), list.get(smallestId)) < 0)
                smallestId = checkValueId;
        return smallestId;
    }
}
