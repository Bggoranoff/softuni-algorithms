package Sorting;
import java.util.*;

public class Words {
    private static long combinations = 0;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split("");
        int[] table = new int[26];
        int length = 0;
        fillTable(input, table, length);
        int opts = options(table);
        if(isPermutation(table)) {
            System.out.println(factorial(opts));
        } else {
            countCombinations(table, -1);
            System.out.println(combinations);
        }
    }
    static void fillTable(String[] arr, int[] table, int length) {
        Arrays.fill(table, 0);
        for(String c: arr) {
            table[(int) c.charAt(0) - 97] ++;
            length++;
        }
    }
    static int options(int[] table) {
        int opts = 0;
        for (int value : table) {
            if (value != 0) opts++;
        }
        return opts;
    }
    static long factorial(int n) {
        long fact = 1;
        for(int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    static void countCombinations(int[] table, int previousIndex) {
        if(isEmpty(table)) combinations += 1;
        else {
            for(int i = 0; i < table.length; i++) {
                if(table[i] != 0 && i != previousIndex) {
                    table[i] -= 1;
                    countCombinations(table, i);
                    table[i] += 1;
                }
            }
        }
    }
    static boolean isEmpty(int[] table) {
        for (int value : table) {
            if (value != 0) return false;
        }
        return true;
    }
    static boolean isPermutation(int[] table) {
        for(int value: table) {
            if(value > 1) return false;
        }
        return true;
    }
}
