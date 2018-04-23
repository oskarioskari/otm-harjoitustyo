package tykkipeli.objects;

public class Vector {

    private double x;
    private double y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setComponents(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double deltaX) {
        this.x += deltaX;
    }

    public void addY(double deltaY) {
        this.y += deltaY;
    }

    public double[] getComponents() {
        return new double[]{this.x, this.y};
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
