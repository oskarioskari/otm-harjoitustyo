package tykkipeli.logic;

import tykkipeli.physicobjects.GraphicObject;
import java.util.ArrayList;
import java.util.List;
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

    public Vector sumAcceleration(GraphicObject object, List<Vector> newAccelerations) {
        // Method assumes that all accelerations are listed in "newAccelerations".
        // If "newAccelerations" is not empty the method will overwrite all old values.
        Vector netAcceleration = new Vector();
        if (newAccelerations.isEmpty()) {           // Use old values.
            netAcceleration = object.getAcceleration();
            return netAcceleration;
        } else {                                    // Calculate new sum acceleration and discard old values.
            double newX = 0.0;
            double newY = 0.0;
            for (Vector v : newAccelerations) {
                newX += v.getX();
                newY += v.getY();
            }
            netAcceleration.setX(newX);
            netAcceleration.setY(newY);
        }
        return netAcceleration;
    }

    public Vector nextVelocity(GraphicObject object, Vector newAcceleration, GameStatus status) {
        // Calculate object velocity for step i+1.
        Vector velocity = object.getVelocity();
        Vector oldAcceleration = object.getAcceleration();
        double deltaX = 0.5 * (oldAcceleration.getX() + newAcceleration.getX());
        double deltaY = 0.5 * (oldAcceleration.getY() + newAcceleration.getY());
        deltaX += status.getWind();
        velocity.addX(deltaX);
        velocity.addY(deltaY);
        return velocity;
    }

    public void nextStepOnlyGravity(GraphicObject object, GameStatus status) {
        // Iteration in two steps:
        // 1) Calculate next location of object
        nextLocation(object);
        // (Constant acceleration, so no need to calculate new)
        // 2) Calculate the velocity of object
        nextVelocity(object, object.getAcceleration(), status);
    }

    // TODO: Something is very wrong with this
    // TODO: Fix this.
    public void nextStep(GraphicObject object, GameStatus status) {
        // Empty list for all accelerations in system
        List<Vector> accelerations = new ArrayList<>();
        // Add accelerations caused by gravity and drag:
        accelerations.add(calculateDrag(object));
        accelerations.add(status.getGravity());
        // Iteration in three steps:
        // 1) Calculate next location of object
        nextLocation(object);
        // 2) Calculate total acceleration of object
        Vector totalAcceleration = sumAcceleration(object, accelerations);
        object.setAcceleration(totalAcceleration);
        // 3) Calculate the velocity of object
        nextVelocity(object, totalAcceleration, status);
    }

    public Vector calculateDrag(GraphicObject ammo) {
        double x = -(ammo.getVelocity().getX() / Math.abs(ammo.getVelocity().getX())) * (0.5 * ammo.getDragCoefficient() * 1.3 * (0.1 * 0.1) * Math.pow(ammo.getVelocity().getX(), 2)) / ammo.getMass();
        double y = -(ammo.getVelocity().getY() / Math.abs(ammo.getVelocity().getY())) * (0.5 * ammo.getDragCoefficient() * 1.3 * (0.1 * 0.1) * Math.pow(ammo.getVelocity().getY(), 2)) / ammo.getMass();
        return new Vector(x, y);
    }
}
