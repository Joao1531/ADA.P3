
import java.util.ArrayDeque;
import java.util.ArrayList;
/**
 * Terceiro trabalho pratico de ADA
 *
 * @author Jose Romano 59241
 * @author Joao Lopes 60055
 */
import java.util.List;

/**
 * This class represents a solution to the problem given in the assignment.
 * It provides multiple methods that combined allows the user to find the maximum number of people
 * that can be saved from the regions in danger to the safe region.
 * It is used the BFS (Breadth-First-Search) algorithm to explore the adjacent regions and to return the shortest path
 * from the source to sink.
 */
public class Solution {

    private final int INF = Integer.MAX_VALUE;
    private int numNodes;
    private int numRegions;
    private List<Region> regions;
    private List<List<Edge>> adjacencyList;


    /**
     * Constructs a new Solution with the number of regions that are going to be created to the problem.
     *
     * @param numRegions the number of regions in the problem
     */
    public Solution(int numRegions) {
        this.numRegions = numRegions;
        this.numNodes = (2 * numRegions) + 1;
        adjacencyList = new ArrayList<>();
        fillAdj();
        this.regions = new ArrayList<>();
        regions.add(new Region(0, 0, 0));

    }

    /**
     * Creates a region with the specified population, capacity, and region number and adds
     * the edges between A and A' to the adjacency list.
     *
     * @param population the population of the region
     * @param capacity   the capacity of the region
     * @param region     the region number Id
     */
    public void addRegion(int population, int capacity, int region) {
        regions.add(new Region(capacity, population, region));
        int currentRegion = 2 * region - 1;

        adjacencyList.get(currentRegion).add(new Edge(currentRegion + 1, capacity));
        adjacencyList.get(currentRegion + 1).add(new Edge(currentRegion, 0));
    }

    /**
     * Adds an adjacency between the specified source and destination regions, from the source to the destination
     * and vice-versa.
     *
     * @param source      the source region Id
     * @param destination the destination region Id
     */
    public void addAdj(int source, int destination) {
        int regionSource = 2 * source - 1;
        int regionDestination = 2 * destination;

        adjacencyList.get(regionSource).add(new Edge(regionDestination, 0));
        adjacencyList.get(regionDestination).add(new Edge(regionSource, INF));
    }

    /**
     * Calculates the maximum number of people that can be saved from the regions in danger to the safe region.
     *
     * @param safeRegion the safeRegion ID
     * @return the maximum number of people that can be accommodated in the safeRegion
     */
    public int getMaxPeople(int safeRegion) {
        int source = 0;
        int sink = safeRegion * 2 - 1;

        int maxFlow = 0;
        int[] parent = new int[numNodes];

        while (hasPathBfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, getCapacity(u, v));
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];

                updateCapacity(u, v, pathFlow);
                updateCapacity(v, u, -pathFlow);
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    /**
     * Initializes the super source of the graph that connects to all the other sources.
     */
    public void initSuperSource() {
        for (int i = 1; i <= numRegions; i++) {
            int regionNode = (2 * i) - 1;
            int population = regions.get(i).population;
            adjacencyList.get(0).add(new Edge(regionNode, population));
            adjacencyList.get(regionNode).add(new Edge(0, 0));
        }
    }


    /**
     * Checks if there is a path from the source to the sink (safeRegion) using the breadth-first search algorithm.
     *
     * @param source the source node Id
     * @param sink   the sink node Id (safeRegion)
     * @param parent an array to store the parent nodes of each node along the path
     * @return true if there is a path from the source to the sink, false otherwise
     */
    private boolean hasPathBfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[numNodes];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.remove();

            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.dest;
                int capacity = edge.capacity;
                if (!visited[v] && capacity > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[sink];
    }

    /**
     * Fills the adjacency list with empty lists for each node (Initialization of the List).
     */
    private void fillAdj() {
        for (int i = 0; i < numNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    /**
     * Returns the capacity of the edge between the specified source and destination nodes.
     *
     * @param source      the source node Id
     * @param destination the destination node Id
     * @return the capacity of the edge between the source and destination nodes
     */
    private int getCapacity(int source, int destination) {
        for (Edge edge : adjacencyList.get(source)) {
            if (edge.dest == destination) {
                return edge.capacity;
            }
        }
        return 0;
    }

    /**
     * Updates the capacity of the edge between the specified source and destination nodes the current flow from
     * the edge capacity.
     *
     * @param source      the source node Id
     * @param destination the destination node Id
     * @param flow        the flow to subtract from the capacity
     */
    private void updateCapacity(int source, int destination, int flow) {
        for (Edge edge : adjacencyList.get(source)) {
            if (edge.dest == destination) {
                edge.capacity -= flow;
                return; // found the edge, so we can return
            }
        }
    }


}
