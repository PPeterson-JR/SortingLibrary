package org.csu.cpsc;

public class IntegerSortingLibrary {

    public static int[] bubbleSort(int[] array) {
        for (int stopIndex = array.length - 1; stopIndex > 0; stopIndex--) {
            for (int index = 0; index < stopIndex; index++) {
                if (array[index] > array[index + 1]) {
                    int temp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                }
            }
        }

        return array;
    }

    public static int[] insertionSort(int[] array) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++){
            int current = array[outerIndex];
            int innerIndex = outerIndex - 1;

            while(innerIndex >= 0 && array[innerIndex] > current){
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }

            array[innerIndex + 1] = current;
        }

        /* Adding Sorting Logic for insertion sort here (DONE) */

        return array;
    }

    public static int[] mergeSort(int[] array) {
        int tableSize = array.length;

        if (tableSize > 1) {
            int halfSize = tableSize / 2;

            int[] leftTable = new int[halfSize];
            int[] rightTable = new int[tableSize - halfSize];

            for (int i = 0; i < halfSize; i++) {
                leftTable[i] = array[i];
            }

            for (int i = halfSize; i < tableSize; i++) {
                rightTable[i - halfSize] = array[i];
            }

            leftTable = mergeSort(leftTable);
            rightTable = mergeSort(rightTable);

            /* Adding Merge Method for Merge Sort here */
            int leftTableIndex = 0;
            int rightTableIndex = 0;
            int arrayIndex = 0;

            while (leftTableIndex < leftTable.length && rightTableIndex < rightTable.length) {
                if (leftTable[leftTableIndex] <= rightTable[rightTableIndex]) {
                    array[arrayIndex] = leftTable[leftTableIndex];
                    arrayIndex++;
                    leftTableIndex++;
                } else {
                    array[arrayIndex] = rightTable[rightTableIndex];
                    arrayIndex++;
                    rightTableIndex++;
                }
            }

            while (leftTableIndex < leftTable.length) {
                array[arrayIndex] = leftTable[leftTableIndex];
                arrayIndex++;
                leftTableIndex++;
            }

            while (rightTableIndex < rightTable.length) {
                array[arrayIndex] = rightTable[rightTableIndex];
                arrayIndex++;
                rightTableIndex++;
            }
        }

        return array;

    }

    public static int[] selectionSort(int[] array) {
        for(int outerIndex = 0; outerIndex < array.length - 1; outerIndex++){
            int minIndex = outerIndex;

            for (int innerIndex = outerIndex + 1; innerIndex < array.length; innerIndex++){
                if(array[innerIndex] < array[minIndex]){
                    minIndex = innerIndex;
                }
            }

            int temp = array[outerIndex];
            array[outerIndex] = array[minIndex];
            array[minIndex] = temp;
        }

        /* Adding Sorting Logic for selection sort here (DONE) */
        return array;
    }
}
