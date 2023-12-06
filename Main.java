import java.util.ArrayList;
import java.util.List;
import java.util.*;

class Edge {
    int source, dest;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
}

class Graph {
    List<List<Integer>> adjList = null;

    Graph(List<Edge> edges, int n) {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            int src = edge.source;
            int dest = edge.dest;

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}

public class Main {
    public static void BFS(Graph graph, int v, boolean[] discovered, int[] distances) {
        Queue<Integer> q = new ArrayDeque<>();
      //  List<Integer> dList = new ArrayList<>();
        discovered[v] = true;
        distances[v] = 0;
        q.offer(v);
        while (!q.isEmpty()) {
            v = q.poll();
            System.out.print(v + " ");
            for (int u : graph.adjList.get(v)) {
                if (!discovered[u]) {
                    discovered[u] = true;
                    distances[u] = distances[v] + 1;
                    q.offer(u);
                   // System.out.println("Distance: " + distances[u]);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(new Edge(1, 2), new Edge(1, 3), new Edge(1, 4), new Edge(2, 5), new Edge(2, 6),
                new Edge(5, 9), new Edge(5, 10), new Edge(4, 7), new Edge(4, 8), new Edge(7, 11), new Edge(7, 12));
        // vertex 0, 13, and 14 are single nodes
        int n = 15;
        Graph graph = new Graph(edges, n);
        int[] distances = new int[n];

        boolean[] discovered = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (discovered[i] == false) {
                BFS(graph, i, discovered, distances);
            }
        }
    }
}

