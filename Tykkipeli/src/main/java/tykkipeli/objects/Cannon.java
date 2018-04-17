package tykkipeli.objects;

import tykkipeli.objects.GraphicObject;

public class Cannon extends GraphicObject {

    private double angle;
    private double power;

    public Cannon(double x, double y) {
        super(x, y);
        this.angle = 1.0 * Math.PI / 2;
        this.power = 2;
    }

    public Cannon(double x, double y, double cannonAngle, double shootingPower) {
        super(x, y);
        this.angle = cannonAngle;
        this.power = shootingPower;
    }

    public double getCannonAngle() {
        return this.angle;
    }

    public void setCannonAngle(double angle) {
        this.angle = angle;
    }

    public void increaseCannonAngle(double angle) {
        this.angle += angle;
    }

    public double getCannonPower() {
        return this.power;
    }

    public void setCannonPower(double power) {
        this.power = power;
    }

    public void increaseCannonPower(double power) {
        this.power += power;
    }

}
