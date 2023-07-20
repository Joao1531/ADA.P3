/**
 * Terceiro trabalho pratico de ADA
 *
 * @author Jose Romano 59241
 * @author Joao Lopes 60055
 */

/**
 * This class represents a edge in the graph, where each edge connects two regions and has
 * the destination Id and the capacity.
 **/
public class Edge {
    int dest;
    int capacity;

    /**
     * Constructs a new Edge with the specified destination and capacity.
     *
     * @param dest     the destination node Id  of the edge
     * @param capacity the capacity of the edge
     **/
    public Edge(int dest, int capacity) {
        this.dest = dest;
        this.capacity = capacity;
    }

}
