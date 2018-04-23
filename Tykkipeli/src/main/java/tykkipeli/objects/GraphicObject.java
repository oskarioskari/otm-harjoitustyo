package tykkipeli.objects;

public class GraphicObject {

    private Vector location;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    public GraphicObject() {
        this.location = new Vector();
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.mass = 0;
    }

    public GraphicObject(Vector startLocation) {
        this.location = startLocation;
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.mass = 0;
    }

    public GraphicObject(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        this.location = startLocation;
        this.velocity = startVelocity;
        this.acceleration = startAcceleration;
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }

    public void setMass(double newMass) {
        this.mass = newMass;
    }

    public Vector getLocation() {
        return this.location;
    }

    public void setLocationXY(double x, double y) {
        this.location.setComponents(x, y);
    }

    public void setLocation(Vector newLocation) {
        this.location = newLocation;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocityXY(double x, double y) {
        this.velocity.setComponents(x, y);
    }

    public void setVelocity(Vector newVelocity) {
        this.velocity = newVelocity;
    }

    public Vector getAcceleration() {
        return this.acceleration;
    }

    public void setAccelerationXY(double x, double y) {
        this.acceleration.setComponents(x, y);
    }

    public void setAcceleration(Vector newAcceleration) {
        this.acceleration = newAcceleration;
    }

}
