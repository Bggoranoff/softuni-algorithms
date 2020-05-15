package Recursion;
import java.util.*;

public class CombinationsWithRepetition {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine()), k = Integer.parseInt(s.nextLine());
        int[] vector = new int[k];
        generateCombinations(0, n, vector);
    }
    static void generateCombinations(int index, int length, int[] vector) {
        if(index >= vector.length) print(vector);
        else {
            for(int i = 1; i <= length; i++) {
                if(index == 0 || i >= vector[index - 1]) {
                   vector[index] = i;
                   generateCombinations(index + 1, length, vector);
                }
            }
        }
    }
    static void print(int[] vector) {
        for(int i: vector) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}
