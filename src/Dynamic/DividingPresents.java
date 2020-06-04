package Dynamic;
import java.util.*;

public class DividingPresents {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] presents = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int sum = Arrays.stream(presents).reduce(Integer::sum).getAsInt(), maxCapacity = sum / 2;
        int[][] matrix = new int[presents.length + 1][maxCapacity + 1];
        boolean[][] itemsIncluded = new boolean[presents.length + 1][maxCapacity + 1];
        for(int i = 1; i <= presents.length; i++) {
            int item = presents[i - 1];
            for(int capacity = 1; capacity <= maxCapacity; capacity++) {
                if(item > capacity) {
                    matrix[i][capacity] = matrix[i - 1][capacity];
                    continue;
                }
                int including = matrix[i - 1][capacity - item] + item,
                        excluding = matrix[i - 1][capacity];
                if(including > excluding) {
                    itemsIncluded[i][capacity] = true;
                    matrix[i][capacity] = including;
                } else {
                    matrix[i][capacity] = excluding;
                }
            }
        }
        System.out.printf("Difference: %d\n", Math.abs(sum - 2 * matrix[presents.length][maxCapacity]));
        System.out.printf("Alan: %d Bob: %d\n", matrix[presents.length][maxCapacity], sum - matrix[presents.length][maxCapacity]);
    }
}
