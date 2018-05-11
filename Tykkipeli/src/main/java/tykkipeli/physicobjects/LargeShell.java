package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

/**
 * Class for larger ammo type. LargeShell has following values: mass = 2, damage
 * = 25, drag coefficient = 1.
 *
 * @author oskari
 */
public class LargeShell extends Ammo {

    /**
     * Constructor for class LargeShell. Extends class Ammo.
     */
    public LargeShell() {
        super(2, 25, 1);
    }

    /**
     * Constructor for class LargeShell. Set custom starting location.
     *
     * @param startLocation Vector startLocation
     */
    public LargeShell(Vector startLocation) {
        super(startLocation, 2, 25, 1);
    }

    /**
     * Constructor for class LargeShell. Set custom starting location, velocity
     * and acceleration.
     *
     * @param startLocation Vector startLocation
     * @param startVelocity Vector startVelocity
     * @param startAcceleration Vector startAcceleration
     */
    public LargeShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 2, 25, 1);
    }

}
