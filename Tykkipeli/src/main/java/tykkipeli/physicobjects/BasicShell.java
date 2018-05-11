package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

/**
 * Class for default type of ammo. BasicShell has following values: mass = 1,
 * damage = 10, drag coefficient = 0.25.
 *
 * @author oskari
 */
public class BasicShell extends Ammo {

    /**
     * Constructor for class BasicShell.
     */
    public BasicShell() {
        super(1, 10, 0.25);
    }

    /**
     * Constructor for class BasicShell. Set custom starting location.
     *
     * @param startLocation Vector startLocation
     */
    public BasicShell(Vector startLocation) {
        super(startLocation, 1, 10, 0.25);
    }

    /**
     * Constructor for class BasicShell. Set custom starting location, velocity
     * and acceleration.
     *
     * @param startLocation Vector startLocation
     * @param startVelocity Vector startVelocity
     * @param startAcceleration Vector startAcceleration
     */
    public BasicShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 1, 10, 0.25);
    }

}
