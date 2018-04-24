package tykkipeli.physicobjects;

import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Vector;

public class LargeShell extends Ammo {
    
    private final double damage;
    
    public LargeShell() {
        this.damage = 25;
    }
    
    public LargeShell(Vector startLocation) {
        super(startLocation);
        this.damage = 25;
    }

    public LargeShell(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        super(startLocation, startVelocity, startAcceleration, mass);
        this.damage = 25;
    }
    
}
