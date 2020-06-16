package Graphs;
import java.util.*;

public class MakeGraphStronglyConnected {
    private static List<List<Integer>> graph;
    private static List<List<Integer>> reversedGraph;
    private static boolean[] visited;
    private static ArrayDeque<Integer> stack;
    private static List<List<Integer>> stronglyConnectedComponents;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int nodes = Integer.parseInt(s.nextLine().split(": ")[1]);
        int edges = Integer.parseInt(s.nextLine().split(": ")[1]);
        graph = new ArrayList<>();
        stronglyConnectedComponents = new ArrayList<>();
        stack = new ArrayDeque<>();
        for(int i = 0; i < nodes; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < edges; i++) {
            int[] input = Arrays.stream(s.nextLine().split(" -> ")).mapToInt(Integer::parseInt).toArray();
            graph.get(input[0]).add(input[1]);
        }
        stronglyConnectedComponents = findStronglyConnectedComponents();
        int inDegree = 0, outDegree = 0;
        ArrayList<Integer> needIn = new ArrayList<Integer>(), needOut = new ArrayList<Integer>();
        stronglyConnectedComponents.sort((a, b) -> Integer.compare(b.size(), a.size()));
        for(List<Integer> cmp : stronglyConnectedComponents) {
            if(!hasIn(cmp)) {
                needIn.add(stronglyConnectedComponents.indexOf(cmp));
                inDegree++;
            }
            if(!hasOut(cmp)) {
                needOut.add(stronglyConnectedComponents.indexOf(cmp));
                outDegree++;
            }
        }
        System.out.printf("New edges needed: %d\n", Math.max(inDegree, outDegree));
        if(needIn.size() < needOut.size()) {
            while(!needIn.isEmpty()) {
                int in = needIn.remove(0), out = -1;
                for(int i = 0; i < needOut.size(); i++) {
                    if(needOut.get(i) != in) {
                        out = needOut.remove(i);
                        break;
                    }
                }
                System.out.printf("%d -> %d\n", stronglyConnectedComponents.get(out).get(0), stronglyConnectedComponents.get(in).get(0));
            }
            int indexToAdd = 0;
            for(int i = 0; i < stronglyConnectedComponents.size(); i++) {
                if(!needOut.contains(i)) {
                    indexToAdd = i;
                    break;
                }
            }
            while(!needOut.isEmpty()) {
                int out = needOut.remove(0);
                System.out.printf("%d -> %d\n", stronglyConnectedComponents.get(out).get(0), stronglyConnectedComponents.get(indexToAdd).get(0));
            }
        } else {
            while(!needOut.isEmpty()) {
                int in = needIn.remove(0), out = -1;
                for(int i = 0; i < needOut.size(); i++) {
                    if(needOut.get(i) != in) {
                        out = needOut.remove(i);
                        break;
                    }
                }
                System.out.printf("%d -> %d\n", stronglyConnectedComponents.get(out).get(0), stronglyConnectedComponents.get(in).get(0));
            }
            int indexToAdd = 0;
            for(int i = 0; i < stronglyConnectedComponents.size(); i++) {
                if(!needIn.contains(i)) {
                    indexToAdd = i;
                    break;
                }
            }
            while(!needIn.isEmpty()) {
                int in = needIn.remove(0);
                System.out.printf("%d -> %d\n", stronglyConnectedComponents.get(indexToAdd).get(0), stronglyConnectedComponents.get(in).get(0));
            }
        }
    }
    private static boolean hasIn(List<Integer> cmp) {
        for(int node : cmp) {
            for(List<Integer> nodeChildren : graph) {
                if(!nodeChildren.isEmpty() && (nodeChildren.contains(node) && graph.indexOf(nodeChildren) != node) && !cmp.containsAll(nodeChildren))
                    return true;
            }
        }
        return false;
    }
    private static boolean hasOut(List<Integer> cmp) {
        for(int node : cmp) {
            if(!graph.get(node).isEmpty() && !(graph.get(node).size() == 1 && graph.get(node).get(0) == node) && !cmp.containsAll(graph.get(node)))
                return true;
        }
        return false;
    }
    public static List<List<Integer>> findStronglyConnectedComponents() {
        visited = new boolean[graph.size()];
        buildReversedGraph();
        for(int node = 0; node < graph.size(); node++) {
            if(!visited[node]) dfs(node);
        }
        visited = new boolean[graph.size()];
        stronglyConnectedComponents = new ArrayList<>();
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDfs(node);
            }
        }
        return stronglyConnectedComponents;
    }
    private static void buildReversedGraph() {
        reversedGraph = new ArrayList<>();
        for(int node = 0; node < graph.size(); node++)
            reversedGraph.add(new ArrayList<>());
        for(int node = 0; node < graph.size(); node++) {
            for(int child : graph.get(node))
                reversedGraph.get(child).add(node);
        }
    }
    private static void dfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            for(int child : graph.get(node))
                dfs(child);
            stack.push(node);
        }
    }
    private static void reverseDfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);
            for(int child : reversedGraph.get(node)) {
                reverseDfs(child);
            }
        }
    }
}
