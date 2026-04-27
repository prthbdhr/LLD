package proxyPattern.protectedProxy;

public class SensitiveDataServiceProxy implements ISensitiveDataService {
    private RealSensitiveDataService realService;
    private String[] allowedRoles = {"ADMIN", "MANAGER"};

    public SensitiveDataServiceProxy() {
        realService = new RealSensitiveDataService();
    }

    @Override
    public void accessData(String userRole) {
        if (isAuthorized(userRole)) {
            System.out.println("[SensitiveDataServiceProxy] Access granted for role: " + userRole);
            realService.accessData(userRole);
        } else {
            System.out.println("[SensitiveDataServiceProxy] Access denied for role: " + userRole);
            throw new SecurityException("Unauthorized access attempt by role: " + userRole);
        }
    }

    private boolean isAuthorized(String userRole) {
        for (String role : allowedRoles) {
            if (role.equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}