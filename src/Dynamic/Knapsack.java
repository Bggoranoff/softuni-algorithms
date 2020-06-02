package Dynamic;
import java.util.*;

public class Knapsack {
    static class Item {
        public String name;
        public int price;
        public int weight;

        public Item(String name, int price, int weight) {
            this.name = name;
            this.price = price;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int maxCapacity = Integer.parseInt(s.nextLine());
        ArrayList<Item> items = new ArrayList<>();
        while (true) {
            String line = s.nextLine();
            if (line.equals("end")) break;
            String[] parts = line.split("\\s");
            items.add(new Item(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        int[][] prices = new int[items.size() + 1][maxCapacity + 1];
        boolean[][] itemsIncluded = new boolean[items.size() + 1][maxCapacity + 1];

        for (int i = 0; i < items.size(); i++) {
            int rowIndex = i + 1;
            Item item = items.get(rowIndex);
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                if (item.getWeight() > capacity) continue;
                int excluding = prices[rowIndex - 1][capacity];
                int including = item.getPrice() + prices[rowIndex - 1][capacity - item.getWeight()];
                if (including > excluding) {
                    prices[rowIndex][capacity] = including;
                    itemsIncluded[rowIndex][capacity] = true;
                } else {
                    prices[rowIndex][capacity] = excluding;
                    itemsIncluded[rowIndex][capacity] = false;
                }
            }
        }
        System.out.println(prices[items.size()][maxCapacity]);
        int capacity = maxCapacity;
        ArrayList<Item> result = new ArrayList<>();
        for(int itemIndex = items.size() - 1; itemIndex >= 0; itemIndex--) {
            if (itemsIncluded[itemIndex + 1][capacity]) {
                Item currentItem = items.get(itemIndex);
                result.add(currentItem);
                capacity -= currentItem.getWeight();
            }
        }
        result.forEach(item -> System.out.printf("%s\n", item.getName()));
    }
}
