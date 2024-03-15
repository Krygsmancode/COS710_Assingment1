import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TournamentSelection {
    private final Random random;
    private final int tournamentSize;

    public TournamentSelection(int tournamentSize, int randomSeed) {
        this.random = new Random(randomSeed);
        this.tournamentSize = tournamentSize;
    }

    public List<Individual> select(List<Individual> population, int numParents) {
        List<Individual> parents = new ArrayList<>();
        while (parents.size() < numParents) {
            List<Individual> tournament = new ArrayList<>();
            // Randomly select individuals for the tournament
            for (int i = 0; i < tournamentSize; i++) {
                int randomIndex = random.nextInt(population.size());
                tournament.add(population.get(randomIndex));
            }
            // Find the individual with the highest fitness in the tournament
            Individual winner = tournament.stream()
                    .max((individual1, individual2) -> Double.compare(individual1.getFitness(), individual2.getFitness()))
                    .orElseThrow(() -> new RuntimeException("Tournament selection failed to find a winner."));
            parents.add(winner);
        }
        return parents;
    }
}
