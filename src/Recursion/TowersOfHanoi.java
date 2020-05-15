package Recursion;
import java.util.*;

public class TowersOfHanoi {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        ArrayDeque<Integer> source = new ArrayDeque<>();
        ArrayDeque<Integer> destination = new ArrayDeque<>();
        ArrayDeque<Integer> spare = new ArrayDeque<>();
        fill(source, n);
        move(n, source, destination, spare);
        System.out.println("SOLVED!");
    }
    public static void fill(ArrayDeque<Integer> stack, int n) {
        for(int i = n; i > 0; i--) {
            stack.push(i);
        }
    }
    static void move(int bottomDisk, ArrayDeque<Integer> source, ArrayDeque<Integer> destination, ArrayDeque<Integer> spare) {
        if(bottomDisk == 1) step(1, source, destination);
        else {
            move(bottomDisk - 1, source, spare, destination);
            step(bottomDisk, source, destination);
            move(bottomDisk - 1, spare, destination, source);
        }
    }
    static void step(int disk, ArrayDeque<Integer> source, ArrayDeque<Integer> destination) {
        System.out.printf("Moved disk %d\n", disk);
        destination.push(source.pop());
    }
}
