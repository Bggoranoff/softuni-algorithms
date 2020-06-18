package Cobinatorial;

import java.util.*;

public class CombinatorialAlgorithms {
    public static void main(String[] args) {
        combinations(new int[5], 0, 0, 3, new int[3]);
    }
    static void permutation(int[] elements, int index, boolean[] used, int[] perm) {
        if(index >= elements.length) print(perm);
        else {
            for(int i = 0; i < elements.length; i++) {
                used[i] = true;
                perm[index] = elements[i];
                permutation(elements, index + 1, used, perm);
                used[i] = false;
            }
        }
    }
    static void swapPermutation(int[] elements, int index) {
        if(index >= elements.length) print(elements);
        else {
            swapPermutation(elements, index + 1);
            for(int i = index + 1; i < elements.length; i++) {
                swap(elements, index, i);
                swapPermutation(elements, index + 1);
                swap(elements, index, i);
            }
        }
    }
    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    static void print(int[] arr) {
        Arrays.stream(arr).forEach(el -> System.out.printf("%d ", el));
    }
    static void permutationsWithRepetition(int[] elements, int start, int end) {
        print(elements);
        for(int left = end - 1; left >= start; left--) {
            for(int right = left + 1; right <= end; right++) {
                if(elements[left] != elements[right]) {
                    swap(elements, left, right);
                    permutationsWithRepetition(elements, left + 1, end);
                }
            }
            int firstElement = elements[left];
            for(int i = left; i <= end - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[end] = firstElement;
        }
    }
    static void variation(int[] arr, int index, int k, int[] variations, boolean[] used) {
        if(index >= k) print(variations);
        else {
            for(int i = 0; i < arr.length; i++) {
                if(!used[i]) { // Remove when with algorithms
                    used[i] = true;
                    variations[index] = arr[i];
                    variation(arr, index + 1, k, variations, used);
                    used[i] = false;
                }
            }
        }
    }
    static void variationIterative(int[] arr, int k, int[] vars) {
        while(true) {
            print(vars);
            int index = k - 1;
            while(index >= 0 && vars[index] == arr.length - 1) {
                index--;
            }
            if(index < 0) break;
            vars[index]++;
            for(int i = index + 1; i < k; i++) {
                vars[i] = 0;
            }
        }
    }
    static void combinations(int[] arr, int index, int start, int k, int[] combs) {
        if(index >= k) print(combs);
        else {
            for(int i = start; i < arr.length; i++) {
                combs[index] = i;
                combinations(arr, index + 1, i + 1, k, combs);
            }
        }
    }

    static int binom(int n, int k) {
        if(k > n) return 0;
        if(k == 0 || k == n) return 1;
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
