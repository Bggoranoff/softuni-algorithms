package Dynamic;
import java.util.*;

public class RodCutting {
    static int[] bestPrice, bestCombo, price;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        bestPrice = bestCombo = price = new int[n];
        price = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        cutRodRecursive(n);
    }
    static int cutRodRecursive(int n) {
        if(bestPrice[n] >= 0) return bestPrice[n];
        if(n == 0) return 0;
        int currentBest = bestPrice[n];
        for(int i = 1; i <= n; i++) {
            currentBest = Math.max(currentBest, price[i] + cutRodRecursive(n - i));
            if(currentBest > bestPrice[n]) {
                bestPrice[n] = currentBest;
                bestCombo[n] = i;
            }
        }
        return bestPrice[n];
    }
    static int cutRodIterative(int n) {
        for(int i = 1; i <= n; i++) {
            int currentBest = bestPrice[i];
            for(int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrice[i], price[j] + bestPrice[i - j]);
                if(currentBest > bestPrice[i]) {
                    bestPrice[i] = currentBest;
                    bestCombo[i] = j;
                }
            }
        }
        return bestPrice[n];
    }
    private static void reconstructSolution(int n) {
        while(n - bestCombo[n] != 0) {
            System.out.print(bestCombo[n] + " ");
            n = n - bestCombo[n];
        }
        System.out.println(bestCombo[n]);
    }
}
