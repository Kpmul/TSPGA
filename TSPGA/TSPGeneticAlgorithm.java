import java.util.Arrays;
import java.util.Random;

public class TSPGeneticAlgorithm {

    // Calculate the total distance of a route
    private static double calculateDistance(int[] route, double[][] distanceMatrix) {

        double totalDistance = 0;

        for (int i = 0; i < route.length - 1; i++) {
            totalDistance += distanceMatrix[route[i]][route[i + 1]];// Add the distance between the current city and the next city
        }

        totalDistance += distanceMatrix[route[route.length - 1]][route[0]]; // Return to the starting city
        return totalDistance;   // Return the total distance
    }
    // Generate a population of different and random routes
    private static int[][] generatePopulation(int size, int numCities) {

        int[][] population = new int[size][numCities];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            population[i] = random.ints(0, numCities).distinct().limit(numCities).toArray();
        }
        return population;
    }
    // Randomly select parents for reproduction
    public static int[] selectParents(double[] fitness) {

        int[] parents = new int[2];
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            parents[i] = random.nextInt(fitness.length);
        }
        return parents;
    }
    // Perform crossover between two parents by mixing the genes of two 'offspring'
    public static int[][] crossover(int[] parent1, int[] parent2) { 

        int[][] offspring = new int[2][parent1.length]; // Create two offspring
        Random random = new Random();
        int crossoverPoint = random.nextInt(parent1.length); // Randomly select a crossover point

        for (int i = 0; i < crossoverPoint; i++) { // The first part of the route is copied from the first parent
            offspring[0][i] = parent1[i]; 
            offspring[1][i] = parent2[i];
        }
        for (int i = crossoverPoint; i < parent1.length; i++) { // The second part of the route is copied from the second parent
            offspring[0][i] = parent2[i];
            offspring[1][i] = parent1[i];
        }
        return offspring;
    }
    // Mutate an individual by randomly swapping two cities within the route
    public static int[] mutate(int[] individual) {

        Random random = new Random();
        int mutationPoint1 = random.nextInt(individual.length);
        int mutationPoint2 = random.nextInt(individual.length);
        int temp = individual[mutationPoint1];
        individual[mutationPoint1] = individual[mutationPoint2];
        individual[mutationPoint2] = temp;
        return individual;
    }
    // Replace the worst individuals in the population with the offspring
    public static void replacePopulation(double[][] distanceMatrix, int[][] population, int[][] offspring, double[] fitness) {

        double[] offspringFitness = new double[2];  // Calculate the fitness of the offspring
        offspringFitness[0] = calculateDistance(offspring[0], distanceMatrix); 
        offspringFitness[1] = calculateDistance(offspring[1], distanceMatrix);
        int worstIndex = 0;
        for (int i = 0; i < fitness.length; i++) { // Find the worst individual in the population
            if (fitness[i] > fitness[worstIndex]) {
                worstIndex = i;
            }
        }
        if (offspringFitness[0] < fitness[worstIndex]) { // Replace the worst individual with the first offspring
            population[worstIndex] = offspring[0];
            fitness[worstIndex] = offspringFitness[0];
        }
        worstIndex = 0;

        for (int i = 0; i < fitness.length; i++) { // Find the worst individual in the population
            if (fitness[i] > fitness[worstIndex]) {
                worstIndex = i;
            }
        }
        if (offspringFitness[1] < fitness[worstIndex]) { // Replace the worst individual with the second offspring
            population[worstIndex] = offspring[1];
            fitness[worstIndex] = offspringFitness[1];
        }
    }

    public static void main(String[] args) {
        // Each array represents a city, and each element in the array represents a distance to another city
        int numCities = 10;
        double[][] distanceMatrix = {
            {0.0, 2.3, 8.7, 10.5, 5.1, 3.2, 9.8, 7.6, 6.4, 1.9},
            {2.3, 0.0, 4.5, 8.9, 1.2, 6.7, 2.4, 9.1, 5.3, 3.6},
            {8.7, 4.5, 0.0, 6.2, 3.5, 7.9, 5.6, 1.3, 8.0, 2.8},
            {10.5, 8.9, 6.2, 0.0, 7.3, 1.4, 4.9, 3.0, 9.7, 5.8},
            {5.1, 1.2, 3.5, 7.3, 0.0, 9.4, 6.1, 2.7, 8.3, 4.6},
            {3.2, 6.7, 7.9, 1.4, 9.4, 0.0, 8.5, 4.3, 5.9, 2.1},
            {9.8, 2.4, 5.6, 4.9, 6.1, 8.5, 0.0, 3.7, 7.2, 1.8},
            {7.6, 9.1, 1.3, 3.0, 2.7, 4.3, 3.7, 0.0, 8.8, 6.6},
            {6.4, 5.3, 8.0, 9.7, 8.3, 5.9, 7.2, 8.8, 0.0, 4.2},
            {1.9, 3.6, 2.8, 5.8, 4.6, 2.1, 1.8, 6.6, 4.2, 0.0}
        };

        // GA parameters
        int populationSize = 50;
        int generations = 1000;
        double lastBestFitness = Double.MAX_VALUE;

        // Generate initial population of random routes
        int[][] population = generatePopulation(populationSize, numCities);
        System.out.println("Initial population: " + Arrays.deepToString(population));
        
        // Main GA loop
        for (int generation = 0; generation < generations; generation++) {

            // Evaluate fitness of each individual in the population
            double[] fitness = new double[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = calculateDistance(population[i], distanceMatrix);
            }
            // Find the best individual in the population
            double bestFitness = Arrays.stream(fitness).min().orElse(0);
            // Minimum improvement threshold, if the improvement is less than this value, the algorithm has finished
            double improvementThreshold = 0.01; 
            // If the improvement is less than the threshold, stop the algorithm
            if ((lastBestFitness - bestFitness) / lastBestFitness < improvementThreshold) {
                System.out.println("Converged! Best Fitness: " + bestFitness); // Fitness is the total distance of the best route
                break;
            }
            lastBestFitness = bestFitness;

            // Select individuals for reproduction
            int[] parents = selectParents(fitness);

            // Apply crossover and mutation to create the next generation
            int[][] offspring = new int[2][numCities];
            offspring = crossover(population[parents[0]], population[parents[1]]);

            for (int i = 0; i < 2; i++) {
                offspring[i] = mutate(offspring[i]);
            }
            // Replace the old population with the new one
            replacePopulation(distanceMatrix, population, offspring, fitness);
            System.out.println("Population: " + Arrays.deepToString(population));
        }
    }
}
