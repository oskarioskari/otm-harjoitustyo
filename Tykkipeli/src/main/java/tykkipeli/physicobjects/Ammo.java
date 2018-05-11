package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

/**
 * Master class for all different kinds of ammo. Extends GraphicObject.
 *
 * @author oskari
 */
public class Ammo extends GraphicObject {

    private final double damage;

    /**
     * Constructor for class Ammo.
     *
     * @param mass Mass of ammo
     * @param damage Damage caused by ammo
     * @param dragCoeff Drag coefficient of ammo
     */
    public Ammo(double mass, double damage, double dragCoeff) {
        super(mass, dragCoeff);
        this.damage = damage;
    }

    /**
     * Constructor for class Ammo.
     *
     * @param startLocation Vector startLocation
     * @param mass Mass of ammo
     * @param damage Damage caused by ammo
     * @param dragCoeff Drag coefficient of ammo
     */
    public Ammo(Vector startLocation, double mass, double damage, double dragCoeff) {
        super(startLocation, dragCoeff);
        this.damage = damage;
    }

    /**
     * Constructor for class Ammo.
     *
     * @param startLocation Vector startLocation
     * @param startVelocity Vector startVelocity
     * @param startAcceleration Vector startAcceleration
     * @param mass Mass of ammo
     * @param damage Damage caused by ammo
     * @param dragCoeff Drag coefficient of ammo
     */
    public Ammo(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass, double damage, double dragCoeff) {
        super(startLocation, startVelocity, startAcceleration, mass, dragCoeff);
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

}
