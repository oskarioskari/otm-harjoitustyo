package tykkipeli.physicobjects;

import tykkipeli.objects.Vector;

/**
 * Class for player's cannons. Extends class GraphicObject.
 *
 * @author oskari
 */
public class Cannon extends GraphicObject {

    private double angle;
    private double power;

    /**
     * Constructor for class Cannon.
     *
     * @param startLocation Vector startLocation
     */
    public Cannon(Vector startLocation) {
        super(startLocation);
        this.angle = Math.PI / 4;
        this.power = 10;
    }

    /**
     * Constructor for class Cannon.
     *
     * @param startLocation Vector startLocation
     * @param startAngle Aiming angle at start
     * @param startPower Aiming power at start
     */
    public Cannon(Vector startLocation, double startAngle, double startPower) {
        super(startLocation);
        this.angle = startAngle;
        this.power = startPower;
    }

    public double getCannonAngle() {
        return this.angle;
    }

    public void setCannonAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Increases barrel angle with given amount. Givin negative amount will
     * lower the angle.
     *
     * @param angle Amount to increase angle
     */
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
