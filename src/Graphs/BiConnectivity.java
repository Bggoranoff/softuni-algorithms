package Graphs;
import java.util.*;

public class BiConnectivity {
    private static List<Integer[]> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int[] depths, lowpoint, parents;
    private static List<Integer> articulationPoints;
    public static void main(String[] args) {
        System.out.println(findArticulationPoint(graph).toString());
    }
    public static List<Integer> findArticulationPoint(List<Integer[]> targetGraph) {
        graph = targetGraph;
        visited = new boolean[targetGraph.size()];
        depths = lowpoint = parents = new int[targetGraph.size()];
        Arrays.fill(parents, -1);
        articulationPoints = new ArrayList<Integer>();
        for(int node = 0; node < graph.size(); node++)
            if(!visited[node]) findArticulationPoints(node, 1);
        return articulationPoints;
    }
    private static void findArticulationPoints(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoint[node] = depth;
        int childCount = 0;
        boolean isAP = false;

        for (int child : graph.get(node)) {
            if(!visited[child]) {
                parents[child] = node;
                findArticulationPoints(child, depth + 1);
                childCount++;
                isAP = lowpoint[child] >= depths[node];
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);
            } else if(child != parents[node])
                lowpoint[node] = Math.min(lowpoint[node], depths[child]);
        }
        if((parents[node] == -1 && childCount > 1) || (parents[node] != -1 && isAP))
            articulationPoints.add(node);
    }
}
