package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

public class GraphicObject {

    private Vector location;
    private Vector velocity;
    private Vector acceleration;
    private double mass;
    private final double dragCoeff;

    public GraphicObject() {
        this(0);
    }

    public GraphicObject(double dragCoeff) {
        this(new Vector(), new Vector(), new Vector(), 0, dragCoeff);
    }
    
    public GraphicObject(double mass, double dragCoeff) {
        this(new Vector(), new Vector(), new Vector(), mass, dragCoeff);
    }

    public GraphicObject(Vector startLocation, double dragCoeff) {
        this(startLocation, new Vector(), new Vector(), 0, dragCoeff);
    }

    public GraphicObject(Vector startLocation) {
        this(startLocation, 0);
    }

    public GraphicObject(Vector startLocation, Vector startVelocity, Vector startAcceleration, double mass, double dragCoeff) {
        this.location = startLocation;
        this.velocity = startVelocity;
        this.acceleration = startAcceleration;
        this.mass = mass;
        this.dragCoeff = dragCoeff;
    }

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
