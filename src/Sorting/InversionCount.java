package Sorting;
import java.util.*;

public class InversionCount {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] arr = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        countInversions(arr, 0, 0);
    }
    static void countInversions(int[] arr, int index, int inversions) {
        if(index == arr.length - 1) System.out.printf("%d\n", inversions);
        else {
            for(int i = index + 1; i < arr.length; i++) {
                if(arr[index] > arr[i]) inversions++;
            }
            countInversions(arr, index + 1, inversions);
        }
    }
}
