# Sorting Library Project

The goal of this project is to create two sorting libraries: one for `int` arrays and one for generic Lists. Both libraries contain the same four algorithms: bubble sort, insertion sort, selection sort, and merge sort.

## What you are trying to accomplish

**Part 1** is about the algorithms. These functions are available in the slides. I have demonstrated BubbleSort and provide the Integer MergeSort.

**Part 2** is about making those same algorithms work on *any* type of data. Sorting integers is the easy case, because Java already knows how to compare two `int` values:

```java
if (array[index] > array[index + 1]) { /* swap */ }
```

That only works for primitive numbers. If the list holds objects, `>` will not compile, and Java has no way of knowing which object should come first. A generic sort solves this by handing the comparison to a **comparator**, an object whose only job is to answer "which of these two comes first?"

The algorithm does not change between Part 1 and Part 2. Wherever your integer version compares two values with `<` or `>`, your generic version asks the comparator instead. Getting comfortable with that comparison is the point of Part 2.

## Checklist

Everything you need to complete is marked in the code with a placeholder comment. Replace each comment with your implementation.

### Part 1: `IntegerSortingLibrary.java`

- [ ] `insertionSort` — replace `/* Adding Sorting Logic for insertion sort here */`
- [ ] `selectionSort` — replace `/* Adding Sorting Logic for selection sort here */`
- [ ] `mergeSort` — add the merge step marked by `/* Adding Merge Method for Merge Sort here */`
- [ ] All Integer tests pass

### Part 2: `GenericSortingLibrary.java`

- [ ] `insertionSort` — replace `/* Add Sorting Logic for insertion sort here */`
- [ ] `selectionSort` — replace `/* Add Sorting Logic for selection sort here */`
- [ ] `mergeSort` — replace `/* Add Sorting Logic for merge sort */`
- [ ] All Generic tests pass

### Before you submit

- [ ] All 20 tests pass
- [ ] The test files in `src/test/java` are unchanged
- [ ] Code is pushed to GitHub
- [ ] Screenshot of the code running on your machine is in CougarView

`bubbleSort` is already complete in both libraries. You do not need to change it.

## What is in the starter code

| File | What it is | What you do with it |
| --- | --- | --- |
| `src/main/java/org/csu/cpsc/IntegerSortingLibrary.java` | Sorts `int[]` arrays. `bubbleSort` is complete and `mergeSort` is complete. | Complete Part 1 of the checklist. |
| `src/main/java/org/csu/cpsc/GenericSortingLibrary.java` | Sorts a `List` of any type using a comparator. `bubbleSort` is complete. | Complete Part 2 of the checklist. |
| `src/main/java/org/csu/cpsc/Main.java` | A small program that runs bubble sort from each library and prints the result. | Use it to try out your sorts as you write them. |
| `src/test/java/Test*.java` | JUnit tests, one file per algorithm. | Run them. **Do not change them.** |

## Part 1: Integer Sorting Library

The methods in `IntegerSortingLibrary.java` have been defined for you. You need to complete them by translating the Python functions we wrote into Java. Use the same variable names and methodology.

Things to know:

- **Sort the array you are given.** The tests check the original array after calling your method, so the sorted values must end up in `array` itself.
- **Merge sort is partly done.** We began this method together in class. The code that splits `array` into `leftTable` and `rightTable` and sorts each half recursively is already there. What is missing is the merge: combining the two sorted halves back into `array`. Your merge needs `leftTable` and `rightTable`, so it has to run inside the `if` block, after the two recursive calls. You may write it as a separate helper method.
- The methods are `static`, so they are called on the class: `IntegerSortingLibrary.insertionSort(array)`.

## Part 2: Generic Sorting Library

Open `GenericSortingLibrary.java`. The top of the class looks like this:

```java
public class GenericSortingLibrary<E> {
    private Comparator<? super E> c;

    public GenericSortingLibrary(Comparator<? super E> c){
        this.c = c;
    }
```

- `E` is the type of item being sorted. Whoever creates the library chooses it.
- `c` is the comparator. It is passed in through the constructor and saved so every sort method can use it.

`Main.java` shows how the library gets created and used:

```java
GenericSortingLibrary<Double> genericSortingLibrary = new GenericSortingLibrary<Double>(new DoubleComparator());
list = genericSortingLibrary.bubbleSort(list);
```

Here `E` is `Double`, and the comparator is the `DoubleComparator` class defined at the bottom of `Main.java`:

```java
private static class DoubleComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return Double.compare((double) o1, (double) o2);
    }
}
```

Notice that `GenericSortingLibrary` never mentions `Double` anywhere. It only knows it has a list of `E` and a comparator `c` that can compare two of them.

Please review the video in CougarView for more details on this portion of the assignment.

### A short guide to `compare` and `compareTo`

Your generic sorts will call `c.compare(a, b)`. It returns an `int`, and the sign of that number tells you the order:

| Result | Meaning | Like writing |
| --- | --- | --- |
| negative | `a` comes before `b` | `a < b` |
| zero | `a` and `b` are equal in order | `a == b` |
| positive | `a` comes after `b` | `a > b` |

You may also have seen `a.compareTo(b)`. It follows exactly the same rule. The difference is where the method lives:

- `a.compareTo(b)` is a method on the object itself. Classes like `String`, `Integer`, and `Double` have it built in.
- `c.compare(a, b)` is a method on a separate comparator object. Because it lives outside the class, the same sorting code can sort the same data in different ways just by being given a different comparator.

A quick example of the return values:

```java
"apple".compareTo("banana");   // negative: apple comes before banana
"banana".compareTo("apple");   // positive: banana comes after apple
"apple".compareTo("apple");    // 0
```

To translate an integer comparison into a comparator call, keep the same operator and compare the result against zero:

| Integer version | Generic version |
| --- | --- |
| `a > b` | `c.compare(a, b) > 0` |
| `a < b` | `c.compare(a, b) < 0` |
| `a <= b` | `c.compare(a, b) <= 0` |

### Compare the two bubble sorts

Bubble sort is complete in both libraries, so you can see exactly what changes between an integer sort and a generic sort.

Integer version:

```java
public static int[] bubbleSort(int[] array){
    for(int stopIndex = array.length -1; stopIndex > 0; stopIndex--){
        for(int index = 0; index < stopIndex; index++){
            if(array[index] > array[index + 1]){
                int temp = array[index];
                array[index] = array[index + 1];
                array[index + 1] = temp;
            }
        }
    }

    return array;
}
```

Generic version:

```java
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
```

The loops are identical. Only three things change, and they are the same three things you will change in your other sorts:

| | Integer version | Generic version |
| --- | --- | --- |
| Reading and writing elements | `array[i]`, `array[i] = value`, `array.length` | `array.get(i)`, `array.set(i, value)`, `array.size()` |
| Comparing elements | `>` and `<` | `c.compare(...)` |
| Temporary variables | `int temp` | `E temp` |

Look at the `if` line in the generic version. It is the integer comparison `array[index] > array[index + 1]` rewritten using the translation table above: same operator, compared against zero.

### Requirements for Part 2

- Every comparison between elements must go through `c.compare(...)`.
- Each method must return the sorted list. The tests use the returned value.
- Merge sort needs a place to put the two halves while it works. You are free to add private helper methods and create new lists inside the class.

## Rules for both parts

- Each method must implement the algorithm it is named for.
- Do not change the method signatures or the constructor. The tests depend on them.
- Do not use Java's built-in sorting (`Arrays.sort`, `Collections.sort`, `List.sort`, and so on).
- **Do not touch the test cases.** Do not modify, delete, or add to the files in `src/test/java`. If a test fails, the fix belongs in your sorting library, not in the test file.

## Running and testing

To try a sort by hand, change the methods called in `Main.java` and run it. With the starter code as provided, it prints:

```
1,2,4,5,5,10,
[1.0, 1.5, 2.5, 3.2]
```

Each algorithm has five tests: two for the Integer library and three for the Generic library. Run them from IntelliJ, VSCode, or from the terminal:

```
./gradlew test
```

The bubble sort tests pass from the start. For the other algorithms, a few tests also pass before you write any code, because their input is already in sorted order. Do not count a sort as finished until **all** of its tests pass.
#   S o r t i n g L i b r a r y  
 