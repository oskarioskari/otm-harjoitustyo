package tykkipeli.physicobjects;

import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Vector;

public class BasicShell extends Ammo {

    public BasicShell() {
        super(10, 0.47);
    }

    public BasicShell(Vector startLocation) {
        super(startLocation, 10, 0.47);
    }

    public BasicShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 1, 10, 0.47);
    }

}
