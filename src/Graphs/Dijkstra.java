package Graphs;
import java.util.*;

public class Dijkstra {
    static int[] distances;
    static class Edge {
        public int first, second, weight;
        public Edge() {
            this.first = -1;
            this.second = -1;
            this.weight = 0;
        }
        public Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }
    static class NodeComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return distances[o1] - distances[o2];
        }
    }
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        ArrayList<Edge> graph = new ArrayList<Edge>(){
            {
                add(new Edge(2, 4, 2));
                add(new Edge(1, 2, 4));
                add(new Edge(1, 3, 5));
                add(new Edge(3, 5, 7));
                add(new Edge(8, 9, 7));
                add(new Edge(4, 5, 8));
                add(new Edge(7, 8, 8));
                add(new Edge(1, 4, 9));
                add(new Edge(7, 9, 10));
                add(new Edge(5, 6, 12));
                add(new Edge(3, 4, 20));
            }
        };
        HashSet<Integer> uniqueNodes = new HashSet<>();
        graph.forEach(e -> {
            uniqueNodes.add(e.first);
            uniqueNodes.add(e.second);
        });
        ArrayList<Integer> nodes = new ArrayList<>(uniqueNodes);
        nodes.sort((a, b) -> a - b);
        distances = new int[Collections.max(nodes) + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        int[] prev = new int[Collections.max(nodes) + 1];
        Arrays.fill(prev, -1);
        int startNode = 1;
        distances[startNode] = 0;
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
            int minNode = priorityQueue.remove(0);
            if(distances[minNode] == Integer.MAX_VALUE) break;
            for(Edge edge : nodeToEdges.get(minNode)) {
                int otherNode = edge.first == minNode ? edge.second : edge.first;
                if(distances[otherNode] == Integer.MAX_VALUE) {
                    priorityQueue.add(otherNode);
                }
                int newDistance = distances[minNode] + edge.weight;
                if(newDistance < distances[otherNode]) {
                    distances[otherNode] = newDistance;
                    prev[otherNode] = minNode;
                }
            }
        }
        for (int distance : distances)
            System.out.printf("%d ", distance);
        System.out.println();
        for(int pr : prev)
            System.out.printf("%d ", pr);
    }
}
