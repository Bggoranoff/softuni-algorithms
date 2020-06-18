package ExamPrep;

import java.util.*;

public class RoadTrip {
    static class Item {
        public int value, space;
        public Item(int value, int space) {
            this.value = value;
            this.space = space;
        }
    }
    static ArrayList<Item> items = new ArrayList<Item>();
    static int maxCapacity = 0;
    static int[][] optimalValues;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] values = Arrays.stream(s.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] spaces = Arrays.stream(s.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < values.length; i++) {
            items.add(new Item(values[i], spaces[i]));
        }
        maxCapacity = Integer.parseInt(s.nextLine());
        optimalValues = new int[items.size() + 1][maxCapacity + 1];
        int maximumValue = findMaxValue();
        System.out.printf("Maximum value: %d\n", maximumValue);
    }
    static int findMaxValue() {
        for(int i = 0; i < items.size(); i++) {
            int rowIndex = i + 1;
            Item item = items.get(i);
            for(int capacity = 0; capacity <= maxCapacity; capacity++) {
                if(item.space > capacity) {
                    optimalValues[rowIndex][capacity] = optimalValues[rowIndex - 1][capacity];
                    continue;
                }
                int excluding = optimalValues[rowIndex - 1][capacity];
                int including = item.value + optimalValues[rowIndex - 1][capacity - item.space];
                optimalValues[rowIndex][capacity] = Math.max(including, excluding);
            }
        }
        return optimalValues[items.size()][maxCapacity];
    }
}
