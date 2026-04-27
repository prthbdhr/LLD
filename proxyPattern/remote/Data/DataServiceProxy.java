package proxyPattern.remote.Data;

public class DataServiceProxy implements IDataService {
    private RealDataService realDataService;

    public DataServiceProxy() {
        realDataService = null;
    }

    @Override
    public Data fetchData() {
        if (realDataService == null) {
            System.out.println("[DataServiceProxy] Initializing RealDataService...");
            realDataService = new RealDataService();
        } else {
            System.out.println("[DataServiceProxy] Using cached RealDataService instance.");
        }
        return realDataService.fetchData();
    }
    
}
