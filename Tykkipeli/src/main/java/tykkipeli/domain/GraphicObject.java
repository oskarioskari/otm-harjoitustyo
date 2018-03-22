package tykkipeli.domain;

public class GraphicObject {

    double mass, speed, direction;
    int x, y;

    public GraphicObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.mass = 0.0;
        this.speed = 0.0;
        this.direction = 0.0;
    }

    public GraphicObject(int x, int y, double mass, double velocity, double direction) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.speed = velocity;
        this.direction = direction;
    }

    public double getMass() {
        return this.mass;
    }

    public void setMass(double newMass) {
        this.mass = newMass;
    }

    public int[] getLocation() {
        int[] location = {this.x, this.y};
        return location;
    }

    public void setLocation(int[] newLocation) {
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
        return this.direction;
    }

    public void setDirection(double newDirection) {
        this.direction = newDirection;
    }

}
