package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class Ammo extends GraphicObject {
    
    private final double damage;
    
    public Ammo() {
        this.damage = 10;
    }
    
    public Ammo(Vector startLocation) {
        super(startLocation);
        this.damage = 10;
    }
    
    public Ammo(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        super(startLocation, startVelocity, startAcceleration, mass);
        this.damage = 10;
    }
    
    public double getDamage() {
        return this.damage;
    }
    
}
