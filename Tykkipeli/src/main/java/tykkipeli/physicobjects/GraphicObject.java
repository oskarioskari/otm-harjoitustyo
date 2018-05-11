package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

/**
 * Master class of all drawable objects. All classes extending GraphicObject can
 * also be used with ObjectPhysics.
 *
 * @author oskari
 */
public class GraphicObject {

    private Vector location;
    private Vector velocity;
    private Vector acceleration;
    private double mass;
    private final double dragCoeff;

    /**
     * Constructor for GraphicObject class. All values will be zero.
     */
    public GraphicObject() {
        this(0);
    }

    /**
     * Constructor for GraphicObject class. All values except for drag
     * coefficient will be set to zero.
     *
     * @param dragCoeff Drag coefficient.
     */
    public GraphicObject(double dragCoeff) {
        this(new Vector(), new Vector(), new Vector(), 0, dragCoeff);
    }

    /**
     * Constructor for GraphicObject class. Set mass and drag coefficient to
     * custom values. All other values are set to zero.
     *
     * @param mass Mass of object
     * @param dragCoeff Drag coefficient
     */
    public GraphicObject(double mass, double dragCoeff) {
        this(new Vector(), new Vector(), new Vector(), mass, dragCoeff);
    }

    /**
     * Constructor for GraphicObject class. Set custom starting location and
     * drag coefficient. All other values are set to zero.
     *
     * @param startLocation Vector of starting location
     * @param dragCoeff Drag coefficient
     */
    public GraphicObject(Vector startLocation, double dragCoeff) {
        this(startLocation, new Vector(), new Vector(), 0, dragCoeff);
    }

    /**
     * Constructor for GraphicObject class. Set custom starting location. All
     * other values are set to zero.
     *
     * @param startLocation Vector of starting location
     */
    public GraphicObject(Vector startLocation) {
        this(startLocation, 0);
    }

    /**
     * Constructor for GraphicObject class. Set all values at start.
     *
     * @param startLocation Vector startLocation
     * @param startVelocity Vector startVelocity
     * @param startAcceleration Vector startAcceleration
     * @param mass Mass of object
     * @param dragCoeff Drag Coefficient of object
     */
    public GraphicObject(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass, double dragCoeff) {
        this.location = startLocation;
        this.velocity = startVelocity;
        this.acceleration = startAcceleration;
        this.mass = mass;
        this.dragCoeff = dragCoeff;
    }

    /**
     * Constructor for GraphicObject class. Set all other values except for drag
     * coefficient.
     *
     * @param startLocation Vector startLocation
     * @param startVelocity Vector startVelocity
     * @param startAcceleration Vector startAcceleration
     * @param mass Mass of object
     */
    public GraphicObject(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass) {
        this(startLocation, startVelocity, startAcceleration, mass, 0);
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

    public double getDragCoefficient() {
        return this.dragCoeff;
    }
}
