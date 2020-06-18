package ExamPrep;
import java.util.*;

public class CheapTownTour {
    static int[] distances, parents;
    static class Street {
        public int first, second, weight;
        public Street(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }
    static class NodeComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(distances[o1], distances[o2]);
        }
    }
    static ArrayList<Street> graph = new ArrayList<>();
    static int minimumSpanningTree;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int towns = Integer.parseInt(s.nextLine());
        int streets = Integer.parseInt(s.nextLine());
        for(int i = 0; i < streets; i++) {
            int[] input = Arrays.stream(s.nextLine().split(" - ")).mapToInt(Integer::parseInt).toArray();
            graph.add(new Street(input[0], input[1], input[2]));
        }
        System.out.printf("Total cost: %d", kruksal());
    }
    static int kruksal() {
        HashSet<Integer> nodes = new HashSet<Integer>();
        graph.forEach(e -> {
            nodes.add(e.first);
            nodes.add(e.second);
        });
        parents = new int[Collections.max(nodes) + 1];
        for(Integer node : nodes)
            parents[node] = node;
        ArrayList<Street> edges = new ArrayList<Street>(graph);
        edges.sort((a, b) -> a.weight - b.weight);
        for (Street edge : edges) {
            int firstRoot = findRoot(edge.first);
            int secondRoot = findRoot(edge.second);
            if (firstRoot != secondRoot) {
                minimumSpanningTree += edge.weight;
                parents[secondRoot] = firstRoot;
            }
        }
        return minimumSpanningTree;
    }
    static int findRoot(int node) {
        while(parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}
