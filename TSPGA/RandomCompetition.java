import java.util.Random;
// Completely random Selection or Competition strategy
// Just picks two random individuals from the population

public class RandomCompetition implements CompetitionStrategy {

    @Override
    public int[] compete(double[] fitness) {

        Random random = new Random();
        int[] parents = new int[2];

        for (int i = 0; i < 2; i++) {
            parents[i] = random.nextInt(fitness.length);
        }

        return parents;
    }
}