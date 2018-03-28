package tykkipeli.domain;

public class GraphicObject {

    private double mass, speedX, speedY, accelerationX, accelerationY;
    private double x, y;
    
    public GraphicObject() {
        
    }

    public GraphicObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.mass = 0.0;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.accelerationX = 0.0;
        this.accelerationY = 0.0;
    }

    public GraphicObject(double x, double y, double mass, double velocityX, double velocityY, double accelerationX, double accelerationY) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.speedX = velocityX;
        this.speedY = velocityY;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
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

    public double[] getSpeed() {
        double[] ret = {this.speedX, this.speedY};
        return ret;
    }

    public void setSpeed(double newXValue, double newYValue) {
        this.speedX = newXValue;
        this.speedY = newYValue;
    }

    public double[] getAcceleration() {
        double[] ret = {this.accelerationX, this.accelerationY};
        return ret;
    }

    public void setAcceleration(double newXValue, double newYValue) {
        this.accelerationX = newXValue;
        this.accelerationY = newYValue;
    }

}
