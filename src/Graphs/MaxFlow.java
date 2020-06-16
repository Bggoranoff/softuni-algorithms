package Graphs;
import java.util.*;

public class MaxFlow {
    private static int[][] graph;
    private static int[] parents;
    public static void main(String[] args) {

    }
    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;
        parents = new int[graph.length];
        Arrays.fill(parents, -1);
        int maxFlow = 0;
        int startNode = 0, endNode = graph.length - 1;
        while(bfs(startNode, endNode)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = endNode;
            while(currentNode != startNode) {
                int prevNode = parents[currentNode];
                int currentFlow = graph[prevNode][currentNode];
                if(currentFlow > 0 && currentFlow < pathFlow)
                    pathFlow = currentFlow;
                currentNode = prevNode;
            }
            maxFlow += pathFlow;
            currentNode = endNode;
            while(currentNode != startNode) {
                int prevNode = parents[currentNode];
                graph[prevNode][currentNode] -= pathFlow;
                graph[currentNode][prevNode] += pathFlow;
                currentNode = prevNode;
            }
        }
        return maxFlow;
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()) {
            int node = queue.remove();
            for(int child = 0; child < graph[node].length; child++) {
                if(graph[node][child] > 0 && !visited[child]) {
                    queue.add(child);
                    parents[child] = node;
                    visited[child] = true;
                }
            }
        }
        return visited[end];
    }
}
