package Heap;

/**
 * Created by gopalbala on 29/4/17.
 */
public class Heap {
    Integer[] heap;
    int currentPosition = -1;

    public Heap(int size) {
        heap = new Integer[size];
    }

    public void insert(int item) {
        if (isFull())
            throw new RuntimeException("Heap is full");
        heap[++currentPosition] = item;
        fixUp(currentPosition);
    }

    //whenever an element is added fixup will be called since the element is inserted at the end
    private void fixUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && heap[parentIndex] < heap[index]) {
            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public int getMax() {
        int result = heap[0];
        heap[0] = heap[currentPosition--];
        heap[currentPosition + 1] = null;
        fixDown(0, -1);
        return result;
    }

    //will be called after removal of each item and during heap sort
    private void fixDown(int index, int upto) {
        upto = upto < 0 ? currentPosition : upto;
        while (index <= upto) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            if (index <= upto) {
                int childToSwap;
                if (rightChild > upto)
                    childToSwap = leftChild;
                else {
                    childToSwap = heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
                }
                if (heap[index] < heap[childToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = temp;
                } else
                    break;

                index = childToSwap;
            }
        }
    }

    public void heapSort() {
        for (int i = 0; i < currentPosition; i++) {
            int temp = heap[0];
            System.out.println(temp);
            heap[0] = heap[currentPosition - 1];
            fixDown(0, currentPosition - i - 1);
        }
    }

    private boolean isFull() {
        return currentPosition == heap.length;
    }
}
