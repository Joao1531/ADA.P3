/**
 * Terceiro trabalho pratico de ADA
 *
 * @author Jose Romano 59241
 * @author Joao Lopes 60055
 */

/**
 * This class represents a region in the problem, where each region has its capacity, population, and ID.
 **/
public class Region {
    int capacity;
    int population;
    int id;

    /**
     * Constructs a new Region with its specified capacity, population, and ID.
     *
     * @param capacity   the capacity of the region
     * @param population the population of the region
     * @param id         the ID of the region
     **/
    public Region(int capacity, int population, int id) {
        this.capacity = capacity;
        this.population = population;
        this.id = id;
    }
}