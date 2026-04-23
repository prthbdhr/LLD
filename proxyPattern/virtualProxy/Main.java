package proxyPattern.virtualProxy;

import proxyPattern.virtualProxy.image.IImage;
import proxyPattern.virtualProxy.image.ImageProxy;

public class Main {
    
    public static void main(String[] args) {
        IImage image1 = new ImageProxy("photo1.jpg");
        IImage image2 = new ImageProxy("photo2.jpg");

        System.out.println("------------------------------------------------"); 
        System.out.println("------------------------------------------------"); 

        // Images will be loaded only when display is called
        image1.display(); // Loads and displays photo1.jpg
        image2.display(); // Loads and displays photo2.jpg

        // Subsequent calls to display will not load the images again
        image1.display(); // Displays photo1.jpg without loading

        System.out.println("------------------------------------------------"); 
        System.out.println("------------------------------------------------"); 
        
    }
}
