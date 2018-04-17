package tykkipeli.logic;

import tykkipeli.objects.GraphicObject;
import java.util.ArrayList;

public class ObjectPhysics {

    public ObjectPhysics() {
    }

    // Object behavior is calculated using Velocity Verlet algorithm.
    // For more info: https://en.wikipedia.org/wiki/Verlet_integration#Velocity_Verlet
    // Unit of length is "pixels/frame update" and unit of time is "time between two frames".
    public double[] nextLocation(GraphicObject object) {
        // Calculate object location during step i+1.
        double[] location = object.getLocation();
        double[] speed = object.getSpeed();
        double[] acc = object.getAcceleration();
        double deltaX = speed[0] + 0.5 * acc[0];
        double deltaY = speed[1] + 0.5 * acc[1];
        location[0] += deltaX;
        location[1] += deltaY;
        return location;
    }

    public double[] sumAcceleration(GraphicObject object, ArrayList<double[]> newAccelerations) {
        // Method assumes that all accelerations are listed in "newAccelerations".
        // If "newAccelerations" is not empty the method will overwrite all old values.
        double[] netAcceleration = object.getAcceleration();
        if (newAccelerations.isEmpty()) {           // Use old values.
            return netAcceleration;
        } else {                                    // Calculate new sum acceleration and discard old values.
            double deltaX = 0.0;
            double deltaY = 0.0;
            for (double[] a : newAccelerations) {
                deltaX += a[0];
                deltaY += a[1];
            }
            netAcceleration[0] = deltaX;
            netAcceleration[1] = deltaY;
        }
        return netAcceleration;
    }

    public double[] nextVelocity(GraphicObject object, double[] newAcceleration) {
        // Calculate object velocity for step i+1.
        double[] velocity = object.getSpeed();
        double[] oldAcceleration = object.getAcceleration();
        double deltaX = 0.5 * (oldAcceleration[0] + newAcceleration[0]);
        double deltaY = 0.5 * (oldAcceleration[1] + newAcceleration[1]);
        velocity[0] += deltaX;
        velocity[1] += deltaY;
        return velocity;
    }

    public double[] nextStepOnlyGravity(GraphicObject object) {
        double[] newLoc = nextLocation(object);
        double[] newVel = nextVelocity(object, object.getAcceleration());
        object.setLocation(newLoc);
        object.setSpeed(newVel[0], newVel[1]);
        return newLoc;
    }

//    public double[] calculateDrag() {
//        TODO
//    }
}
