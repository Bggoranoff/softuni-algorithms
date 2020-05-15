package Sorting;
import java.util.*;

public class Needles {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] dimensions = s.nextLine().split("\\s");
        int length = Integer.parseInt(dimensions[0]), needlesCount = Integer.parseInt(dimensions[1]);
        int[] sequence = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int[] needles = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        for(int needle: needles) {
            boolean swapped = false;
            for(int i = length - 1; i >= 0; i--) {
                if(sequence[i] < needle && sequence[i] != 0) {
                    swapped = true;
                    System.out.printf("%d ", i + 1);
                    break;
                }
            }
            if(!swapped) System.out.printf("%d ", 0);
        }
    }
}
