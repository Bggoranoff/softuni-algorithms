package Dynamic;
import java.util.*;

public class FibonacciRecursive {
    static HashMap<Integer, Long> foundNumbers = new HashMap<>();
    public static void main(String[] args) {
        foundNumbers.put(0, (long) 1);
        foundNumbers.put(1, (long) 1);
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        System.out.println(fib(n));
    }
    static long fib(int n) {
        if(foundNumbers.size() > n) return foundNumbers.get(n);
        foundNumbers.put(n, fib(n - 1) + fib(n - 2));
        return foundNumbers.get(n);
    }
}
