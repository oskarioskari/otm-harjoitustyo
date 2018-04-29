package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class LargeShell extends Ammo {

    public LargeShell() {
        super(2, 25, 1);
    }

    public LargeShell(Vector startLocation) {
        super(startLocation, 2, 25, 1);
    }

    public LargeShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 2, 25, 1);
    }

}
