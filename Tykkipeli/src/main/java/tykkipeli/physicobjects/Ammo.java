package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class Ammo extends GraphicObject {

    private final double damage;
    
    public Ammo(double mass, double damage, double dragCoeff) {
        super(mass, dragCoeff);
        this.damage = damage;
    }

    public Ammo(Vector startLocation, double mass, double damage, double dragCoeff) {
        super(startLocation, dragCoeff);
        this.damage = damage;
    }

    public Ammo(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass, double damage, double dragCoeff) {
        super(startLocation, startVelocity, startAcceleration, mass, dragCoeff);
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

}
