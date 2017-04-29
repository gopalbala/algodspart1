package Heap;

import java.util.Arrays;

/**
 * Created by gopalbala on 29/4/17.
 */
public class App {
    public static void main(String[] args) {
        int[] arr = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };;
        Heap heap = new Heap(arr.length);
        Arrays.stream(arr).forEach(x -> heap.insert(x));
        heap.heapSort();

    }
}
