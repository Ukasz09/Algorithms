package com.Ukasz09.github.sortAlgorithms;

import com.Ukasz09.github.utilities.ArrayList;
import com.Ukasz09.github.utilities.IListSorter;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.Iterator;

public class MergeSort<T> implements IListSorter<T> {
    private final Comparator<T> comparator;
    private ArrayList<T> listToSort;
    private long timeToSort;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MergeSort(Comparator<T> comparator, ArrayList<T> listToSort) {

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
        listToSort = mergesort(listToSort, 0, listToSort.size() - 1);
        Long endTime = System.nanoTime();
        timeToSort = endTime - startTime;

        return listToSort;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<T> mergesort(ArrayList<T> list, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            ArrayList<T> resultList = new ArrayList<>();
            resultList.add(list.get(startIndex));
            return resultList;
        }
        int splitIndex = startIndex + (endIndex - startIndex) / 2;
        return merge(mergesort(list, startIndex, splitIndex), mergesort(list, splitIndex + 1, endIndex));
    }

    @SuppressWarnings("unchecked")
    private ArrayList<T> merge(ArrayList<T> leftList, ArrayList<T> rightList) {
        ArrayList<T> resultList = new ArrayList<>();
        Iterator<T> leftIter = leftList.iterator();
        Iterator<T> rightIter = rightList.iterator();
        T elemL = null, elemR = null;
        boolean contL, contR;
        if (contL = leftIter.hasNext()) elemL = leftIter.next();
        if (contR = rightIter.hasNext()) elemR = rightIter.next();

        while (contL && contR) {
            if (comparator.compare(elemL, elemR) <= 0) {
                resultList.add(elemL);
                if (contL = leftIter.hasNext())
                    elemL = leftIter.next();
                else resultList.add(elemR);
            } else {
                resultList.add(elemR);
                if (contR = rightIter.hasNext())
                    elemR = rightIter.next();
                else resultList.add(elemL);
            }
        }

        while (leftIter.hasNext()) resultList.add(leftIter.next());
        while (rightIter.hasNext()) resultList.add(rightIter.next());

        return resultList;
    }

}
