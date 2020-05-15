package Recursion;
import java.util.*;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        String[] input = s.nextLine().split("\\s");
        for(String num: input) {
            stack.push(Integer.parseInt(num));
        }
        reverse(stack);
    }
    static void reverse(ArrayDeque<Integer> stack) {
        if(stack.isEmpty()) System.out.println();
        else {
            System.out.printf("%d ", stack.pop());
            reverse(stack);
        }
    }
}
