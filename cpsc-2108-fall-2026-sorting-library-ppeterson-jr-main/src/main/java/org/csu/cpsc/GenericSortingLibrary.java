package org.csu.cpsc;

import java.util.Comparator;
import java.util.List;

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

        /* Add Sorting Logic for insertion sort here */

        return array;
    }

    public List<E> mergeSort(List<E> array){

        /* Add Sorting Logic for merge sort */

        return array;
    }

    public List<E> selectionSort(List<E> array){

        /* Add Sorting Logic for selection sort here */

        return array;
    }
}
