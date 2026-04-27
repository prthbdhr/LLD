package proxyPattern.protectedProxy;

public class Main {
    public static void main(String[] args) {
        ISensitiveDataService proxy = new SensitiveDataServiceProxy();

        System.out.println("=============================================");
        System.out.println("Testing Protected Proxy Pattern");
        System.out.println("=============================================");

        // Authorized access
        try {
            System.out.println("Attempting access with ADMIN role:");
            proxy.accessData("ADMIN");
        } catch (SecurityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("---------------------------------------------");

        // Authorized access
        try {
            System.out.println("Attempting access with MANAGER role:");
            proxy.accessData("MANAGER");
        } catch (SecurityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("---------------------------------------------");

        // Unauthorized access
        try {
            System.out.println("Attempting access with USER role:");
            proxy.accessData("USER");
        } catch (SecurityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("=============================================");
    }
}
