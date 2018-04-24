package tykkipeli.physicobjects;

import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Vector;

public class BasicShell extends Ammo {
    
    private final double damage;

    public BasicShell() {
        this.damage = 10;
    }

    public BasicShell(Vector startLocation) {
        super(startLocation);
        this.damage = 10;
    }

    public BasicShell(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        super(startLocation, startVelocity, startAcceleration, mass);
        this.damage = 10;
    }

}
