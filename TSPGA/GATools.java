import java.util.Random;

// Just put all the methods in one class for now to make everything easier to read
// This is not the best way to do it but it will do for now
// Feel free to change any methods

// Might be a good idea to use the Strategy Design Pattern to implement the different Mutation methods maybe?
// Right now there's just one 'mutate' method but could make more variations of it and swap between them

public class GATools {
    
    public static double calculateDistance(int[] route, double[][] distanceMatrix) {

        double totalDistance = 0;

        for (int i = 0; i < route.length - 1; i++) {
            totalDistance += distanceMatrix[route[i]][route[i + 1]];// Add the distance between the current city and the next city
        }

        totalDistance += distanceMatrix[route[route.length - 1]][route[0]]; // Return to the starting city
        return totalDistance;   // Return the total distance
    }
    // Generate a population of different and random routes
    public static int[][] generatePopulation(int size, int numCities) {

        int[][] population = new int[size][numCities];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            population[i] = random.ints(0, numCities).distinct().limit(numCities).toArray();
        }
        return population;
    }
    // Randomly select parents for reproduction
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
}