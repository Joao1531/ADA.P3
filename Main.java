import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of regions and direct links
        String[] input = br.readLine().split(" ");
        int numRegions = Integer.parseInt(input[0]);
        int numLinks = Integer.parseInt(input[1]);

        //Initialize class solution
        Solution solution = new Solution(numRegions);

        //LEITURAS

        // Read the population size and departure capacity of each region
        for (int i = 1; i <= numRegions; i++) {
            input = br.readLine().split(" ");
            int population = Integer.parseInt(input[0]);
            int capacity = Integer.parseInt(input[1]);
            solution.initializeRegion(population, capacity, i);
        }

        // Read the direct links between regions
        for (int i = 0; i < numLinks; i++) {
            input = br.readLine().split(" ");
            int region1 = Integer.parseInt(input[0]);
            int region2 = Integer.parseInt(input[1]);
            solution.initializeGraph(region1, region2, i);
        }

        // Read the safe region
        int safeRegion = Integer.parseInt(br.readLine());
        int source = 1; // Assuming source region is always region 1

        // Compute the maximum population that can reach the safe region
        int maxFlow = solution.getSol(source, safeRegion);
        System.out.println(maxFlow);
    }
}
