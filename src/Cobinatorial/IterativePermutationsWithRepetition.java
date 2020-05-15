package Cobinatorial;
import java.util.*;

public class IterativePermutationsWithRepetition {
    static boolean end = false;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] seq = s.nextLine().split("\\s");
        permutations(seq);
    }
    static void print(String[] arr) {
        for(String value : arr) {
            System.out.printf("%s ", value);
        }
        System.out.println();
    }
    static void permutations(String[] arr) {
        int[] permSwappings = new int[arr.length];
        for(int i = 0; i < permSwappings.length; i++) permSwappings[i] = i;
        HashSet<String> results = new HashSet<>();
        String[] perm = next(arr, permSwappings);
        while(perm.length != 0) {
            results.add(String.join(" ", perm));
            perm = next(arr, permSwappings);
        }
        for(String p : results) {
            System.out.printf("%s\n", p);
        }
    }
    static String[] next(String[] arr, int[] permSwappings) {
        if(end) return new String[0];
        String[] res = new String[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        int i = permSwappings.length - 1;
        while(i >= 0 && permSwappings[i] == arr.length - 1) {
            swap(arr, i, permSwappings[i]);
            permSwappings[i] = i;
            i--;
        }
        if(i < 0) end = true;
        else {
            int prev = permSwappings[i];
            swap(arr, i, prev);
            int next = prev + 1;
            permSwappings[i] = next;
            swap(arr, i, next);
        }
        return res;
    }
    static void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}