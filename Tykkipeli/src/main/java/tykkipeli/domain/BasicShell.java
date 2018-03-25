package tykkipeli.domain;

public class BasicShell extends GraphicObject {
    
    public BasicShell() {
        
    }
    
    public BasicShell(double x, double y) {
        super(x, y);
    }
    
    public BasicShell(double x, double y, double mass, double velocity, double vDirection, double acceleration, double aDirection) {
        super(x, y, mass, velocity, vDirection, acceleration, aDirection);
    }
    
}
