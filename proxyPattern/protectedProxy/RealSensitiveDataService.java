package proxyPattern.protectedProxy;

public class RealSensitiveDataService implements ISensitiveDataService {

    @Override
    public void accessData(String userRole) {
        System.out.println("[RealSensitiveDataService] Accessing sensitive data for user with role: " + userRole);
        // Simulate accessing sensitive data
        System.out.println("Sensitive data: Confidential information...");
    }
}