package com.Ukasz09.github.sortAlgorithms;

import com.Ukasz09.github.utilities.ArrayList;
import com.Ukasz09.github.utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class QuickSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public QuickSort(Comparator<T> comparator, ArrayList<T> listToSort) {
        if (comparator == null || listToSort == null)
            throw new InvalidParameterException();
        this.comparator = comparator;
        this.listToSort = new ArrayList<>(listToSort);
        timeToSort = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public long getTimeToSort() {
        return timeToSort;
    }

    public ArrayList<T> getListToSort() {
        return listToSort;
    }

    @Override
    public ArrayList<T> sort() {
        Long startTime = System.nanoTime();
        quickSort(listToSort, 0, listToSort.size());
        Long endTime = System.nanoTime();
        timeToSort = endTime - startTime;

        return listToSort;
    }

    private void quickSort(ArrayList<T> list, int startIndex, int endIndex) {
        if (endIndex - startIndex > 1) {
            int partitionId = partition(list, startIndex, endIndex);
            quickSort(list, startIndex, partitionId);
            quickSort(list, partitionId + 1, endIndex);
        }
    }

    private int partition(ArrayList<T> list, int nFrom, int nTo) {
        int randomPivot = randomInt(nFrom, nTo);
        list.swap(nFrom, randomPivot);
        T pivot = list.get(nFrom);
        int idFormLeft = nFrom + 1, idFromRight = nTo - 1;
        do {
            while (idFormLeft <= idFromRight && comparator.compare(list.get(idFormLeft), pivot) <= 0)
                idFormLeft++;
            while (comparator.compare(list.get(idFromRight), pivot) > 0)
                idFromRight--;
            if (idFormLeft < idFromRight)
                list.swap(idFormLeft, idFromRight);
        } while (idFormLeft < idFromRight);

        list.swap(idFromRight, nFrom);
        return idFromRight;
    }

    private int randomInt(int min, int max) {
        int range = max - min;
        int randomPivot = (int) (Math.random() * range) + min;
        return randomPivot;
    }
}
