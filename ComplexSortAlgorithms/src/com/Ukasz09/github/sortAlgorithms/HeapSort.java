package com.Ukasz09.github.sortAlgorithms;

import com.Ukasz09.github.utilities.ArrayList;
import com.Ukasz09.github.utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class HeapSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HeapSort(Comparator<T> comparator, ArrayList<T> listToSort) {
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

    private void heapify(ArrayList<T> heap, int actualId, int n) {
        int child = 2 * actualId + 1;
        if (child < n) {
            if (child + 1 < n && comparator.compare(heap.get(child), heap.get(child + 1)) < 0)
                child++;
            if (comparator.compare(heap.get(actualId), heap.get(child)) < 0) {
                heap.swap(actualId, child);
                heapify(heap, child, n);
            }
        }
    }

    private void heapAdjustment(ArrayList<T> heap, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--)
            heapify(heap, i, n);
    }

    private void heapsort(ArrayList<T> heap, int n) {
        heapAdjustment(heap, n);
        for (int i = n - 1; i > 0; i--) {
            heap.swap(i, 0);
            heapify(heap, 0, i);
        }
    }

    @Override
    public ArrayList<T> sort() {
        Long startTime = System.nanoTime();
        heapsort(listToSort, listToSort.size());
        Long endTime = System.nanoTime();
        timeToSort = endTime - startTime;
        return listToSort;
    }

}
