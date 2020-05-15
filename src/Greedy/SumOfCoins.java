package Greedy;
import java.util.*;

public class SumOfCoins {
    static void main(String[] args) {

    }
    static void chooseCoins(ArrayList<Integer> coins, int sum) {
        HashMap<Integer, Integer> result = new HashMap<>();
        coins.sort(Comparator.comparingInt(a -> a));
        int coinIndex = 0, currentSum = 0;
        while(coinIndex < coins.size() && currentSum != sum) {
            int currentValue = coins.get(coinIndex);
            if(currentSum + currentValue > sum) {
                coinIndex++;
                continue;
            }
            int remainingSum = sum - currentSum;
            int coinsToTake = remainingSum / currentValue;
            if(coinsToTake > 0) {
                result.put(currentValue, result.containsKey(currentValue) ? result.get(currentValue) + coinsToTake : coinsToTake);
                currentSum += currentValue * coinsToTake;
            }
        }
        printMap(result);
    }
    static void printMap(HashMap<Integer, Integer> map) {
        map.forEach((key, value) -> System.out.printf("%d: %d times\n", key, value));
    }
}
