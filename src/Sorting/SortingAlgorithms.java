package Sorting;
import java.util.*;

public class SortingAlgorithms {
    public static void main(String[] args) {

    }
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int value : arr) System.out.print(value + " ");
        System.out.println();
    }
    // Merge Sort
    static void mergeSort(int[] arr, int left, int right) {
        if(left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }
    static void merge(int[] arr,int left, int middle, int right) {
        // Find subarray sizes
        int firstSize = middle - left + 1;
        int secondSize = right - middle;
        // Create temp arrays
        int[] leftArray = new int[firstSize];
        int[] rightArray = new int[secondSize];
        // Copy data
        if (firstSize >= 0) System.arraycopy(arr, left, leftArray, 0, firstSize);
        for(int i = 0; i < secondSize; i++) {
            rightArray[i] = arr[middle + 1 + i];
        }
        // Merge temp arrays
        int i = 0, j = 0;
        int initial = left;
        while(i < firstSize && j < secondSize) {
            if(leftArray[i] <= rightArray[j]) {
                arr[initial] = leftArray[i];
                i++;
            } else {
                arr[initial] = rightArray[j];
                j++;
            }
            initial++;
        }
        // Copy remaining elements of leftArray if any
        while(i < firstSize) {
            arr[initial] = leftArray[i];
            i++;
            initial++;
        }
        // Copy remaining elements of rightArray if any
        while(j < secondSize) {
            arr[initial] = rightArray[j];
            j++;
            initial++;
        }
    }
    // Quicksort
    static void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
    }
    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for(int j = left; j < right; j++) {
            if(arr[i] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;
        return i;
    }
    // Counting Sort
    static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).min().getAsInt();
        int[] output = new int[arr.length + 1];
        int[] count = new int[max + 1];
        for(int j = 0; j < max; j++) {
            count[j] = 0;
        }
        // Store the count of each element
        for (int value : arr) {
            count[value]++;
        }
        // Store the cumulative count of each array
        for(int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        for(int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]]] = arr[i];
            count[arr[i]] --;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    // Heap Sort
    static void heapSort(int[] arr) {
        int n = arr.length;
        // Build heap (rearrange array)
        for(int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for(int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    static void heapify(int[] arr, int n, int i) {
       // Heapifies a subtree rooted with node i which is an index in arr[]
        int largest = i; //root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root
        if(left < n && arr[left] > arr[largest])
            largest = left;
        if(right < n && arr[right] > arr[largest])
            largest = right;
        if(largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }
    // Binary Search
    static int binarySearch(int[] arr, int key, int start, int end) {
        if(end < start) return -1;
        else {
            int mid = (start + end) / 2;
            if(arr[mid] > key) return binarySearch(arr, key, start, mid - 1);
            else if(arr[mid] < key) return binarySearch(arr, key, mid + 1, end);
            else return mid;
        }
    }
    // Interpolation Search
    static int interpolationSearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while(arr[left] <= key && arr[right] >= key) {
            int mid = left + ((key - arr[left]) * (right - left)) / (arr[right] - arr[left]);
            if(arr[mid] < key)
                left = mid + 1;
            else if(arr[mid] > key)
                right = mid - 1;
            else return mid;
        }
        if(arr[left] == key) return left;
        else return -1;
    }
    // Fibonacci Search
    public static int min(int x, int y) { return Math.min(x, y); }

    /* Returns index of x if present, else returns -1 */
    public static int fibonacciSearch(int[] arr, int x, int n)
    {
        /* Initialize fibonacci numbers */
        int fibMMm2 = 0; // (m-2)'th Fibonacci No.
        int fibMMm1 = 1; // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        /* fibM is going to store the smallest
        Fibonacci Number greater than or equal to n */
        while (fibM < n)
        {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        // Marks the eliminated range from front
        int offset = -1;
        while (fibM > 1) {
            int i = Math.min(offset+fibMMm2, n-1);
            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
            else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
            else return i;
        }
        if(fibMMm1 == 1 && arr[offset+1] == x)
            return offset+1;
        return -1;
    }
}
