package Graphs;
import java.util.*;

public class StronglyConnectedComponents {
    private static List<List<Integer>> graph;
    private static List<List<Integer>> reversedGraph;
    private static boolean[] visited;
    private static ArrayDeque<Integer> stack;
    private static List<List<Integer>> stronglyConnectedComponents;

    public static void main(String[] args) {

    }
    public static List<List<Integer>> findStronglyConnectedComponents(List<List<Integer>> targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.size()];
        buildReversedGraph();
        for(int node = 0; node < graph.size(); node++)
            if(!visited[node]) dfs(node);
        stronglyConnectedComponents = new ArrayList<>();
        visited = new boolean[graph.size()];
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<Integer>());
                reverseDfs(node);
            }
        }
        return stronglyConnectedComponents;
    }
    private static void buildReversedGraph() {
        reversedGraph = new ArrayList<>();
        for(int node = 0; node < graph.size(); node++)
            reversedGraph.set(node, new ArrayList<>());
        for(int node = 0; node < graph.size(); node++)
            for (int child : graph.get(node))
                reversedGraph.get(child).add(node);

    }
    private static void dfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dfs(child);
            }
            stack.push(node);
        }
    }
    private static void reverseDfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);
            for (int child : reversedGraph.get(node))
                reverseDfs(child);
        }
    }
}
