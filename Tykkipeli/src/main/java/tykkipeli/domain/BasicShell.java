package tykkipeli.domain;

public class BasicShell extends GraphicObject {
    
    public BasicShell() {
        
    }
    
    public BasicShell(double x, double y) {
        super(x, y);
    }
    
    public BasicShell(double x, double y, double mass, double velocityX, double velocityY, double accelerationX, double accelerationY) {
        super(x, y, mass, velocityX, velocityY, accelerationX, accelerationY);
    }
    
}
