package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class LargeShell extends Ammo {

    public LargeShell() {
        super(25, 0.82);
    }

    public LargeShell(Vector startLocation) {
        super(startLocation, 25, 0.82);
    }

    public LargeShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 5, 25, 0.82);
    }

}
