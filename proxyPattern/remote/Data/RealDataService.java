package proxyPattern.remote.Data;

public class RealDataService implements IDataService {

    public RealDataService() {
        // Simulate fetching data from a remote service
        try {
            Thread.sleep(5000); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[RealDataService] Initialized (simulating remote setup)");
    }

    @Override
    public Data fetchData() {
        return new Data("Real data fetched from remote service...");
    }
    
}
