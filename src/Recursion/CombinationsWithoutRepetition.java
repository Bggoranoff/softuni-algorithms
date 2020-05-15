package Recursion;
import java.util.*;

public class CombinationsWithoutRepetition {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine()), k = Integer.parseInt(s.nextLine());
        int[] vector = new int[k];
        generateCombinations(0, n, vector, 0);
    }
    static void generateCombinations(int index, int length, int[] vector, int currentIndex) {
        if(index >= vector.length) print(vector);
        else {
            for(int i = currentIndex + 1; i <= length; i++) {
                    vector[index] = i;
                    generateCombinations(index + 1, length, vector, i);
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