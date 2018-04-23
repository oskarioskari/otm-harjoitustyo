package tykkipeli.objects;

public class Cannon extends GraphicObject {

    private double angle;
    private double power;
    
    public Cannon(Vector startLocation) {
        super(startLocation);
        this.angle = Math.PI / 4;
        this.power = 10;
    }
    
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
