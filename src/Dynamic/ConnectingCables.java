package Dynamic;
import java.util.*;
import java.util.stream.Collectors;

public class ConnectingCables {
    static class Node {
        public int row, col, price;
        public boolean blocked;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            this.price = 1;
            this.blocked = false;
        }
        public void setPrice(int price) {
            this.price += price;
        }
        public void block() {
            this.blocked = true;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] permutation = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int[] sortedPermutation = Arrays.stream(permutation).sorted().toArray();
        int length = permutation.length;
        ArrayList<Node> prices = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(permutation[i] == sortedPermutation[j]) prices.add(new Node(i, j));
            }
        }
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(isOverlapped(prices, i, j)) prices.get(i).setPrice(1);
            }
            prices.get(i).setPrice(-1);
        }
        prices.sort((a, b) -> Integer.compare(a.price, b.price));
        int connectedCables = 0;
        for(int i = 0; i < prices.size(); i++) {
            if(!prices.get(i).blocked) {
                connectedCables++;
                prices.get(i).block();
                for(int j = i; j < length; j++) {
                    if(isOverlapped(prices, i, j) && !prices.get(j).blocked) prices.get(j).block();
                }
            }
        }
        System.out.printf("Maximum pairs connected: %d", connectedCables);
    }
    static boolean isOverlapped(ArrayList<Node> prices, int i, int j) {
        return (prices.get(j).row > prices.get(i).row && prices.get(j).col < prices.get(i).col)
                || (prices.get(j).row < prices.get(i).row && prices.get(j).col > prices.get(i).col);
    }
}
