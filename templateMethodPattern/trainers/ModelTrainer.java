package templateMethodPattern.trainers;

public abstract class ModelTrainer {

     // The template method — final so subclasses can’t change the sequence
    public final void trainPipeline(String dataPath) {
        loadData(dataPath);
        preprocessData();
        trainModel();      // subclass-specific
        evaluateModel();   // subclass-specific
        saveModel();       // subclass-specific or default
    }
    
    protected void loadData(String path) {
        System.out.println("[common] Loading data from: " + path + "...");
    }

    protected void preprocessData() {
        System.out.println("[Common] Splitting into train/test and normalizing");
    }

    protected abstract void trainModel();
    protected abstract void evaluateModel();

    // Provide a default save, but subclasses can override if needed
    protected void saveModel() {
        System.out.println("[Common] Saving model to disk as default format");
    }
}
