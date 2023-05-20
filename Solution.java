public class Solution {
    private Graph graph;
    private Region[] regions;

    public Solution(int numRegions) {
        graph = new Graph(numRegions);
        regions = new Region[numRegions + 1];
    }

    public void initializeRegion(int population, int capacity, int i) {
        regions[i] = new Region(population, capacity);
    }

    public void initializeGraph(int region1, int region2, int i) {
        int capacity = Math.min(regions[region1].getCapacity(), regions[region1].getPopulation());
        graph.addEdge(region1, region2, capacity);
        capacity = Math.min(regions[region2].getCapacity(), regions[region2].getPopulation());
        graph.addEdge(region2, region1, capacity);
    }

    public int getSol(int source, int dest) {
        return graph.computeMaxFlow(source, dest);
    }

}