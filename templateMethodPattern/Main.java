package templateMethodPattern;

import templateMethodPattern.trainers.ModelTrainer;
import templateMethodPattern.trainers.NeuralNetworkTrainer;
import templateMethodPattern.trainers.DecisionTreeTrainer;

public class Main {
    
    public static void main(String[] args) {

        System.out.println("=== Neural Network Training ===");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeline("data/images/");

        System.out.println("\n=== Decision Tree Training ===");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeline("data/iris.csv");
    }
}
