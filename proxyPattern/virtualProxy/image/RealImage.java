package proxyPattern.virtualProxy.image;

public class RealImage implements IImage {
    
    private String filename;

    public RealImage(String file) {
        this.filename = file;
        System.out.println("[RealImage] Loading image from disk: " + filename);
    }

    @Override
    public void display() {
         System.out.println("[RealImage] Displaying " + filename);
    }
    
}
