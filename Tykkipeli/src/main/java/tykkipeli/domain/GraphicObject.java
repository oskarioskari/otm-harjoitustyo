package tykkipeli.domain;

public class GraphicObject {

    private double mass, speed, speedDirection, acceleration, accDirection;
    private double x, y;
    
    public GraphicObject() {
        
    }

    public GraphicObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.mass = 0.0;
        this.speed = 0.0;
        this.speedDirection = 0.0;
        this.acceleration = 0.0;
        this.accDirection = 0.0;
    }

    public GraphicObject(double x, double y, double mass, double velocity, double vDirection, double acceleration, double aDirection) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.speed = velocity;
        this.speedDirection = vDirection;
        this.acceleration = acceleration;
        this.accDirection = aDirection;
    }

    public double getMass() {
        return this.mass;
    }

    public void setMass(double newMass) {
        this.mass = newMass;
    }

    public double[] getLocation() {
        double[] location = {this.x, this.y};
        return location;
    }

    public void setLocation(double[] newLocation) {
        this.x = newLocation[0];
        this.y = newLocation[1];
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public double getDirection() {
        return this.speedDirection;
    }

    public void setSpeedDirection(double newDirection) {
        this.speedDirection = newDirection;
    }

    public double[] getAcceleration() {
        double[] ret = {this.acceleration, this.accDirection};
        return ret;
    }

    public void setAcceleration(double newAcceleration) {
        this.acceleration = newAcceleration;
    }

    public void setAccelerationDirection(double newDirection) {
        this.accDirection = newDirection;
    }

}
