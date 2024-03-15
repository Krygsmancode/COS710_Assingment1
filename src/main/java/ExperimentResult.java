import java.util.ArrayList;
import java.util.List;

class ExperimentResult {
    double mutationProb;
    int maxDepth;
    int popSize;
    int tournamentSize;
    double crossoverProb;
    String bestIndividual;
    double bestFitness;
    List<Double> fitnessProgression;

    public ExperimentResult(double mutationProb, int maxDepth, int popSize, int tournamentSize, double crossoverProb) {
        this.mutationProb = mutationProb;
        this.maxDepth = maxDepth;
        this.popSize = popSize;
        this.tournamentSize = tournamentSize;
        this.crossoverProb = crossoverProb;
        this.fitnessProgression = new ArrayList<>();
    }


}
