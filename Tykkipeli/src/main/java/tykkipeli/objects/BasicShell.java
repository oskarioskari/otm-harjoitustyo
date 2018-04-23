package tykkipeli.objects;

public class BasicShell extends GraphicObject {

    public BasicShell() {

    }

    public BasicShell(Vector startLocation) {
        super(startLocation);
    }

    public BasicShell(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        super(startLocation, startVelocity, startAcceleration, mass);
    }

}
