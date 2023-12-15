import java.util.Arrays;

// For running the GA
public class Runner {

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
        
        // Now using Factory pattern to select the competition strategy
        CompetitionStrategyFactory factory = new TournamentCompetitionFactory();    // Tournament
        CompetitionStrategy strategy = factory.createCompetitionStrategy();
        // Method is called on line 58

        // CompetitionStrategyFactory factory = new RandomCompetitionFactory();    // Random
        // CompetitionStrategy strategy = factory.createCompetitionStrategy();

        // Generate initial population of random routes
        int[][] population = GATools.generatePopulation(populationSize, numCities);
        System.out.println("Initial population: " + Arrays.deepToString(population));
        
        // Main GA loop
        for (int generation = 0; generation < generations; generation++) {

            // Evaluate fitness of each individual in the population
            double[] fitness = new double[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = GATools.calculateDistance(population[i], distanceMatrix);
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
            int[] parents = strategy.compete(fitness); // compete() is from the CompetitionStrategy interface

            // Apply crossover and mutation to create the next generation
            int[][] offspring = new int[2][numCities];
            offspring = GATools.crossover(population[parents[0]], population[parents[1]]);

            for (int i = 0; i < 2; i++) {
                offspring[i] = GATools.mutate(offspring[i]);
            }
            // Replace the old population with the new one
            GATools.replacePopulation(distanceMatrix, population, offspring, fitness);
            System.out.println("Population: " + Arrays.deepToString(population));
        }
    }
}
