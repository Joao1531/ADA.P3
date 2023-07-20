/**
 * Terceiro trabalho pratico de ADA
 *
 * @author Jose Romano 59241
 * @author Joao Lopes 60055
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* Main class */

/**
 * The main method takes input from the user and calls the Solution class to find the maximum number of people that can
 * be saved from the regions in danger to the safe region. If there are no people that can be saved from those regions, the output returns 0.
 *
 * @param args command-line arguments
 * @throws IOException If an I/O error occurs.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of regions and direct links
        String[] firstLine = br.readLine().split(" ");
        int numRegions = Integer.parseInt(firstLine[0]);
        int links = Integer.parseInt(firstLine[1]);
        Solution solution = new Solution(numRegions);

        for (int i = 1; i <= numRegions; i++) {
            String[] line = br.readLine().split(" ");
            int population = Integer.parseInt(line[0]);
            int capacity = Integer.parseInt(line[1]);
            solution.addRegion(population, capacity, i);

        }
        for (int i = 0; i < links; i++) {
            String[] line = br.readLine().split(" ");
            int link1 = Integer.parseInt(line[0]);
            int link2 = Integer.parseInt(line[1]);
            solution.addAdj(link1, link2);
            solution.addAdj(link2, link1);
        }
        solution.initSuperSource();
        int maxFlow = Integer.parseInt(br.readLine());
        System.out.println(solution.getMaxPeople(maxFlow));
    }
}