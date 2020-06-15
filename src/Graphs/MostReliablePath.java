package Graphs;
import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class MostReliablePath {
    static double[] reliabilities;
    static class Edge {
        public int first, second;
        public double weight;
        public Edge() {
            this.first = -1;
            this.second = -1;
            this.weight = -1;
        }
        public Edge(int first, int second, double weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }
    static class NodeComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) Math.round(reliabilities[o2] * 100 - reliabilities[o1] * 100);
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine().split("\\s")[1]);
        int[] pathInput = Arrays.stream(s.nextLine().split(": ")[1].split(" – ")).mapToInt(Integer::parseInt).toArray();
        int startNode =  pathInput[0], endNode = pathInput[1];
        int edgesCount = Integer.parseInt(s.nextLine().split(": ")[1]);
        ArrayList<Edge> graph = new ArrayList<>();
        for(int i = 0; i < edgesCount; i++) {
            int[] input = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            graph.add(new Edge(input[0], input[1], (double) input[2] / 100));
        }
        HashSet<Integer> uniqueNodes = new HashSet<>();
        graph.forEach(e -> {
            uniqueNodes.add(e.first);
            uniqueNodes.add(e.second);
        });
        ArrayList<Integer> nodes = new ArrayList<>(uniqueNodes);
        nodes.sort((a, b) -> a - b);
        reliabilities = new double[n];
        Arrays.fill(reliabilities, -1);
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        reliabilities[startNode] = 1;
        prev[startNode] = -1;
        ArrayList<Integer> priorityQueue = new ArrayList<>();
        priorityQueue.add(nodes.get(0));
        HashMap<Integer, List<Edge>> nodeToEdges = new HashMap<>();
        for(Edge edge : graph) {
            nodeToEdges.putIfAbsent(edge.first, new ArrayList<Edge>());
            nodeToEdges.putIfAbsent(edge.second, new ArrayList<Edge>());
            nodeToEdges.get(edge.first).add(edge);
            nodeToEdges.get(edge.second).add(edge);
        }
        while(!priorityQueue.isEmpty()) {
            priorityQueue.sort(new NodeComparator());
            int bestNode = priorityQueue.remove(0);
            if(reliabilities[bestNode] == -1) break;
            for(Edge edge : nodeToEdges.get(bestNode)) {
                int otherNode = edge.first == bestNode ? edge.second : edge.first;
                if(reliabilities[otherNode] == (double) -1) {
                    priorityQueue.add(otherNode);
                }
                double newReliability = reliabilities[bestNode] * edge.weight;
                if(newReliability > reliabilities[otherNode]) {
                    reliabilities[otherNode] = newReliability;
                    prev[otherNode] = bestNode;
                }
            }
        }
        System.out.println();
        for(double r : reliabilities) System.out.printf("%.2f%% ", r * 100);
        System.out.println();
        System.out.printf("Most reliable path reliability: %.2f%%", reliabilities[endNode] * 100);
    }
}
