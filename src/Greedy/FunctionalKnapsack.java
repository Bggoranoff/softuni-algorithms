package Greedy;
import java.util.*;

public class FunctionalKnapsack {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double capacity = Integer.parseInt(s.nextLine().split("\\s")[1]), itemsLength = Integer.parseInt(s.nextLine().split("\\s")[1]);
        ArrayList<double[]> data = new ArrayList<>();
        for(int i = 0; i < itemsLength; i++) {
            String[] input = s.nextLine().split(" -> ");
            data.add(Arrays.stream(input).mapToDouble(Integer::parseInt).toArray());
        }
        data.sort((a, b) -> Double.compare(b[0] / b[1], a[0] / a[1]));
        int i = 0;
        double price = 0;
        while(capacity > 0 && i < itemsLength) {
                double prop = (capacity / data.get(i)[1]);
                prop = prop > 1 ? 1 : prop;
                capacity -= prop * data.get(i)[1];
                price += prop * data.get(i)[0];
                System.out.printf("Take %.2f%% of item with price %.2f and weight %.2f\n", (prop * 100), data.get(i)[0], data.get(i)[1]);
                i++;
        }
        System.out.printf("Total price: %.2f\n", price);
    }
}
