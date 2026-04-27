package proxyPattern.remote;

import proxyPattern.remote.Data.DataServiceProxy;
import proxyPattern.remote.Data.IDataService;

public class Main {
    public static void main(String[] args) {
        IDataService dataService = new DataServiceProxy();
        System.out.println("=============================================");
        System.out.println("First call to fetchData:");
        dataService.fetchData();
        System.out.println("=============================================");
        System.out.println("Second call to fetchData:");
        dataService.fetchData();
        System.out.println("=============================================");
        System.out.println("Third call to fetchData:");
        dataService.fetchData();
        System.out.println("=============================================");
    }
}
