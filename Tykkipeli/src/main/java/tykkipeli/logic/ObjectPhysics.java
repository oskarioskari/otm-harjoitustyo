package tykkipeli.logic;

import tykkipeli.physicobjects.GraphicObject;
import java.util.ArrayList;
import tykkipeli.objects.Vector;

public class ObjectPhysics {

    public ObjectPhysics() {
    }

    // Object behavior is calculated using Velocity Verlet algorithm.
    // For more info: https://en.wikipedia.org/wiki/Verlet_integration#Velocity_Verlet
    // Unit of velocity is "pixels/frame update time" and unit of time is "time between two frames".
    public Vector nextLocation(GraphicObject object) {
        // Calculate object location during step i+1.
        Vector location = object.getLocation();
        Vector velocity = object.getVelocity();
        Vector acceleration = object.getAcceleration();
        double deltaX = velocity.getX() + 0.5 * acceleration.getX();
        double deltaY = velocity.getY() + 0.5 * acceleration.getY();
        location.addX(deltaX);
        location.addY(deltaY);
        return location;
    }

    public Vector sumAcceleration(GraphicObject object, ArrayList<Vector> newAccelerations) {
        // Method assumes that all accelerations are listed in "newAccelerations".
        // If "newAccelerations" is not empty the method will overwrite all old values.
        Vector netAcceleration = object.getAcceleration();
        if (newAccelerations.isEmpty()) {           // Use old values.
            return netAcceleration;
        } else {                                    // Calculate new sum acceleration and discard old values.
            double newX = 0.0;
            double newY = 0.0;
            for (Vector a : newAccelerations) {
                newX += a.getX();
                newY += a.getY();
            }
            netAcceleration.setX(newX);
            netAcceleration.setY(newY);
        }
        return netAcceleration;
    }

    public Vector nextVelocity(GraphicObject object, Vector newAcceleration) {
        // Calculate object velocity for step i+1.
        Vector velocity = object.getVelocity();
        Vector oldAcceleration = object.getAcceleration();
        double deltaX = 0.5 * (oldAcceleration.getX() + newAcceleration.getX());
        double deltaY = 0.5 * (oldAcceleration.getY() + newAcceleration.getY());
        velocity.addX(deltaX);
        velocity.addY(deltaY);
        return velocity;
    }

    public void nextStepOnlyGravity(GraphicObject object) {
        nextLocation(object);
        nextVelocity(object, object.getAcceleration());
    }

//    public double[] calculateDrag() {
//        TODO
//    }
}
