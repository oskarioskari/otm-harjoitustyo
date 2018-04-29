package tykkipeli.physicobjects;

import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Vector;

public class BasicShell extends Ammo {

    public BasicShell() {
        super(1, 10, 0.25);
    }

    public BasicShell(Vector startLocation) {
        super(startLocation, 1, 10, 0.25);
    }

    public BasicShell(Vector startLocation, Vector startVelocity, Vector startAcceleration) {
        super(startLocation, startVelocity, startAcceleration, 1, 10, 0.25);
    }

}
