
import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Individual> individuals;

    public Population() {
        individuals = new ArrayList<>();
    }

    public void initialise(int populationSize, PrimitiveSet primitiveSet, int maxDepth) {
        PopulationInitialiser initialiser = new PopulationInitialiser(primitiveSet, maxDepth, 42);
        individuals = initialiser.initialisePopulation(populationSize);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    // Additional methods for selection, reproduction, and mutation
}
