package Graphs;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class MinimumSpanningTree {
    static class Edge {
        public int first, second, weight;
        Edge() {
            this.first = -1;
            this.second = -1;
            this.weight = -1;
        }
        Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

    }
    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
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
        primAlgorithm(graph);
    }
    static void kruskalAlgorithm(ArrayList<Edge> graph) {
        HashSet<Integer> nodes = new HashSet<>();
        graph.forEach(e -> {
            nodes.add(e.first);
            nodes.add(e.second);
        });
        int[] parents = new int[Collections.max(nodes) + 1];
        for(Integer node : nodes)
            parents[node] = node;
        ArrayList<Edge> edges = new ArrayList<Edge>(graph);
        edges.sort((a, b) -> a.weight - b.weight);
        while(edges.size() != 0) {
            Edge edge = edges.get(0);
            int firstNode = edge.first;
            int secondNode = edge.second;

            int firstRoot = findRoot(firstNode, parents);
            int secondRoot = findRoot(secondNode, parents);
            if(firstRoot != secondRoot) {
                System.out.printf("%d - %d\n", firstNode, secondNode);
                parents[firstRoot] = secondRoot;
            }
            edges.remove(0);
        }
    }
    static void primAlgorithm(ArrayList<Edge> graph) {
        HashSet<Integer> spanningTree = new HashSet<>();
        HashSet<Integer> uniqueNodes = new HashSet<>();
        graph.forEach(e -> {
            uniqueNodes.add(e.first);
            uniqueNodes.add(e.second);
        });
        List<Integer> nodes = new ArrayList<>(uniqueNodes);
        nodes.sort((a, b) -> a - b);
        HashMap<Integer, List<Edge>> nodeToEdges = new HashMap<>();
        for(Edge edge : graph) {
            nodeToEdges.putIfAbsent(edge.first, new ArrayList<Edge>());
            nodeToEdges.putIfAbsent(edge.second, new ArrayList<Edge>());
            nodeToEdges.get(edge.first).add(edge);
            nodeToEdges.get(edge.second).add(edge);
        }
        for(int node : nodes) {
            if(!spanningTree.contains(node)) {
                prim(node, spanningTree, nodeToEdges);
            }
        }
    }
    static void prim(int startingNode, HashSet<Integer> spanningTree, HashMap<Integer, List<Edge>> nodeToEdges) {
        spanningTree.add(startingNode);
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(nodeToEdges.get(startingNode));
        while(!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.remove();
            int firstNode = minEdge.first;
            int secondNode = minEdge.second;
            int noneTreeNode = -1;
            if(spanningTree.contains(firstNode) && !spanningTree.contains(secondNode))
                noneTreeNode = secondNode;
            if(spanningTree.contains(secondNode) && !spanningTree.contains(firstNode))
                noneTreeNode = firstNode;
            if(noneTreeNode == -1)
                continue;
            spanningTree.add(noneTreeNode);
            System.out.printf("%d - %d\n", minEdge.first, minEdge.second);
            priorityQueue.addAll(nodeToEdges.get(noneTreeNode));
        }

    }
    static int findRoot(int node, int[] parents) {
        while(parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}
