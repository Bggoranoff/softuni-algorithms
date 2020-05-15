package Recursion;
import java.util.*;

public class NestedLoops {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int[] vector = new int[n];
        loop(0, vector);
    }
    static void loop(int index, int[] vector) {
        if(index >= vector.length) print(vector);
        else {
            for(int i = 1; i <= vector.length; i++) {
                vector[index] = i;
                loop(index + 1, vector);
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
