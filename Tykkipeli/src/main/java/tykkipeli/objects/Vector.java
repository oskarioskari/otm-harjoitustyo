package tykkipeli.objects;

/**
 * Class for Vector object. Vector can be used for location, velocity and
 * acceleration vectors.
 *
 * @author oskari
 */
public class Vector {

    private double x;
    private double y;

    /**
     * Constructor for Vector class. Vector components are set to x=0 and y=0.
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor for Vector class.
     *
     * @param x Set x component value
     * @param y Set y component value
     */
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
