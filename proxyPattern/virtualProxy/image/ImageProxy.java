package proxyPattern.virtualProxy.image;

public class ImageProxy implements IImage{
    
    private RealImage realImage;
    private String filename;

    public ImageProxy(String file) {
        this.filename = file;
        this.realImage = null;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        
        realImage.display();
    }
}
