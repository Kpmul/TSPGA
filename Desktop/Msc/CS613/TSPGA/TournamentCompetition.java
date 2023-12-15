import java.util.Random;
// Selects two parents for reproduction by randomly selecting two individuals from the population and comparing their fitness
// The individual with the highest fitness is selected as a parent
// Fitness is the total distance of the route so smaller values are better

public class TournamentCompetition implements CompetitionStrategy {

    private static final int TOURNAMENT_SIZE = 5; 

    @Override
    public int[] compete(double[] fitness) {

    Random random = new Random();
    int[] parents = new int[2];

    for (int i = 0; i < 2; i++) {
        int best = random.nextInt(fitness.length); 

        for (int j = 0; j < TOURNAMENT_SIZE; j++) {
            int competitor = random.nextInt(fitness.length);

            if (fitness[competitor] > fitness[best]) {
                best = competitor;
            }
        }

        parents[i] = best;
    }

    return parents;
    }
}