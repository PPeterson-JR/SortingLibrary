package org.csu.cpsc;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


public class GenericSortingLibrary<E> {
    private Comparator<? super E> c;

    public GenericSortingLibrary(Comparator<? super E> c){
        this.c = c;
    }

    public List<E> bubbleSort(List<E> array){
        for(int stopIndex = array.size() -1; stopIndex > 0; stopIndex--) {
            for (int index = 0; index < stopIndex; index++) {
                if (c.compare(array.get(index), array.get(index+1)) > 0) {
                    E temp = array.get(index);
                    array.set(index, array.get(index+1));
                    array.set(index + 1, temp);
                }
            }
        }
        return array;
    }

    public List<E> insertionSort(List<E> array){

        for (int outerIndex = 1; outerIndex < array.size(); outerIndex++){
            E current = array.get(outerIndex);
            int innerIndex = outerIndex - 1;

            while (innerIndex >= 0 && c.compare(array.get(innerIndex), current) > 0){
                array.set(innerIndex + 1, array.get(innerIndex));
                innerIndex--;
            }

            array.set(innerIndex + 1, current);
        }

        /* Add Sorting Logic for insertion sort here (DONE) */

        return array;
    }

    public List<E> mergeSort(List<E> array){
        int tableSize = array.size();

        if(tableSize > 1){
            int halfSize = tableSize / 2;

            List<E> leftTable = new ArrayList<>(array.subList(0, halfSize));
            List<E> rightTable = new ArrayList<>(array.subList(halfSize, tableSize));

            leftTable = mergeSort((leftTable));
            rightTable = mergeSort(rightTable);

            int leftIndex = 0;
            int rightIndex = 0;
            int arrayIndex = 0;

            while (leftIndex < leftTable.size() && rightIndex < rightTable.size()) {
                if (c.compare(leftTable.get(leftIndex), rightTable.get(rightIndex)) <= 0) {
                    array.set(arrayIndex, leftTable.get(leftIndex));
                    arrayIndex++;
                    leftIndex++;
                } else {
                    array.set(arrayIndex, rightTable.get(rightIndex));
                    arrayIndex++;
                    rightIndex++;
                }
            }

            while(leftIndex < leftTable.size()){
                array.set(arrayIndex, leftTable.get(leftIndex));
                arrayIndex++;
                leftIndex++;
            }

            while(rightIndex < rightTable.size()){
                array.set(arrayIndex, rightTable.get(rightIndex));
                arrayIndex++;
                rightIndex++;
            }
        }

        /* Add Sorting Logic for merge sort (DONE) */

        return array;
    }

    public List<E> selectionSort(List<E> array){
        for(int outerIndex = 0; outerIndex < array.size() - 1; outerIndex++){
            int minIndex = outerIndex;

            for(int innerIndex = outerIndex + 1; innerIndex < array.size(); innerIndex++){
                if (c.compare(array.get(innerIndex), array.get(minIndex)) < 0) {
                    minIndex = innerIndex;
                }
            }

            E temp = array.get(outerIndex);
            array.set(outerIndex, array.get(minIndex));
            array.set(minIndex, temp);
        }

        /* Add Sorting Logic for selection sort here (DONE) */

        return array;
    }
}
