package Graphs;
import java.util.*;

public class TraversalAlgorithms {
    public static void main(String[] args) {
        ArrayList<int[]> graph = new ArrayList<>(){
            {
                add(new int[] { 1, 2 });
                add(new int[] { 3, 4 });
                add(new int[] { 5 });
                add(new int[] { 2, 5 });
                add(new int[] { 3 });
                add(new int[] { });
            }
        };
        boolean[] visited = new boolean[graph.size()];
        for(boolean v : visited) v = false;
        topologicalSortingDFS(graph, new HashSet<>(), new ArrayList<>());
    }
    static void dfs(ArrayList<int[]> graph, int node, boolean[] visited) {
        if(!visited[node]) {
            visited[node] = true;
            for(int i = 0; i < graph.get(node).length; i++) dfs(graph, graph.get(node)[i], visited);
            System.out.printf("%d ", node);
        }
    }
    @SuppressWarnings("SameParameterValue")
    static void bfs(ArrayList<int[]> graph, int node, boolean[] visited, ArrayDeque<Integer> queue) {
        // DFS - with stack
        queue.add(node);
        visited[node] = true;
        while(!queue.isEmpty()) {
            int v = queue.remove();
            System.out.printf("%d ", v);
            for(int i = 0; i < graph.get(v).length; i++) {
                if(!visited[graph.get(v)[i]]) {
                    queue.add(graph.get(v)[i]);
                    visited[graph.get(v)[i]] = true;
                }
            }
        }
    }
    static void sourceRemovalTopologicalSort(ArrayList<int[]> originalGraph) {
        ArrayList<int[]> graph = new ArrayList<>(originalGraph);
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> nodes = new HashSet<>();
        HashSet<Integer> nodeWithIncomingEdges = getNodesWithIncomingEdges(graph);

        for(int i = 0; i < graph.size(); i++) {
            if(!nodeWithIncomingEdges.contains(i)) {
                nodes.add(i);
            }
        }
        while(nodes.size() != 0) {
            for (Integer currentNode : nodes) {
                nodes.remove(currentNode);
                result.add(currentNode);
                int[] children = graph.get(currentNode).clone();
                graph.set(currentNode, new int[0]);
                HashSet<Integer> leftNodesWithIncomingEdges = getNodesWithIncomingEdges(graph);
                for (int child : children) {
                    if (!leftNodesWithIncomingEdges.contains(child)) {
                        nodes.add(child);
                    }
                }
            }
        }
        boolean cyclic = false;
        for(int[] ints : graph) {
            if(ints.length != 0) {
                System.out.println("The graph has at least one cycle!");
                cyclic = true;
                break;
            }
        }
        if(!cyclic) {
            for(int res : result) {
                System.out.printf("%d ", res);
            }
        }
    }
    static HashSet<Integer> getNodesWithIncomingEdges(ArrayList<int[]> graph) {
        HashSet<Integer> nodeWithIncomingEdges = new HashSet<>();
        for (int[] ints : graph) {
            for (int anInt : ints) {
                nodeWithIncomingEdges.add(anInt);
            }
        }
        return nodeWithIncomingEdges;
    }
    static void topologicalSortingDFS(ArrayList<int[]> graph, HashSet<Integer> visitedNodes, ArrayList<Integer> sortedNodes) {
        for(int i = 0; i < graph.size(); i++) {
            topSortDFS(graph, i, visitedNodes, sortedNodes);
        }
        for(int node : sortedNodes) {
            System.out.printf("%d ", node);
        }
    }
//    Can add list of cycle nodes and check for recurring ones
    static void topSortDFS(ArrayList<int[]> graph, int node, HashSet<Integer> visitedNodes, ArrayList<Integer> sortedNodes) {
        if(!visitedNodes.contains(node)) {
            visitedNodes.add(node);
            for(int child : graph.get(node)) {
                topSortDFS(graph, child, visitedNodes, sortedNodes);
            }
            sortedNodes.add(node);
        }
    }
}
