import java.util.*;

class Edge {
    int source;
    int destination;
    int capacity;
    int flow;

    public Edge(int source, int destination, int capacity) {
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.flow = 0;
    }
}

public class Graph {
    private int numRegions;
    private List<List<Integer>> adjacencyList;
    private List<Edge> edges;

    public Graph(int numRegions) {
        this.numRegions = numRegions;
        adjacencyList = new ArrayList<>(numRegions + 1); // Index 0 is not used
        edges = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i <= numRegions; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int capacity) {
        Edge forwardEdge = new Edge(source, destination, capacity);
        Edge backwardEdge = new Edge(destination, source, capacity); // Fix: Set capacity to the forward edge capacity

        adjacencyList.get(source).add(edges.size());
        edges.add(forwardEdge);

        adjacencyList.get(destination).add(edges.size());
        edges.add(backwardEdge);
    }

    private boolean hasAugmentingPath(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[numRegions + 1];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int currentRegion = queue.poll();

            for (int edgeIndex : adjacencyList.get(currentRegion)) {
                Edge edge = edges.get(edgeIndex);
                int neighbor = edge.destination;

                if (!visited[neighbor] && edge.capacity > edge.flow) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = edgeIndex;

                    if (neighbor == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int computeMaxFlow(int source, int sink) {
        int[] parent = new int[numRegions + 1];
        int maxFlow = 0;

        while (hasAugmentingPath(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = sink;

            while (currentNode != source) {
                int edgeIndex = parent[currentNode];
                Edge edge = edges.get(edgeIndex);
                pathFlow = Math.min(pathFlow, edge.capacity - edge.flow);
                currentNode = edge.source;
            }

            currentNode = sink;

            while (currentNode != source) {
                int edgeIndex = parent[currentNode];
                Edge forwardEdge = edges.get(edgeIndex);
                Edge backwardEdge = edges.get(edgeIndex ^ 1); // XOR with 1 to get the corresponding backward edge
                forwardEdge.flow += pathFlow;
                backwardEdge.flow -= pathFlow;
                currentNode = forwardEdge.source;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}


