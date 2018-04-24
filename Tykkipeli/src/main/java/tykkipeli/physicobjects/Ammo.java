package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class Ammo extends GraphicObject {

    private final double damage;
    private final double dragCoeff;

    public Ammo(double damage, double dragCoeff) {
        this.damage = damage;
        this.dragCoeff = dragCoeff;
    }

    public Ammo(Vector startLocation, double damage, double dragCoeff) {
        super(startLocation);
        this.damage = damage;
        this.dragCoeff = dragCoeff;
    }

    public Ammo(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass, double damage, double dragCoeff) {
        super(startLocation, startVelocity, startAcceleration, mass);
        this.damage = damage;
        this.dragCoeff = dragCoeff;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getDragCoefficient() {
        return this.dragCoeff;
    }

}
