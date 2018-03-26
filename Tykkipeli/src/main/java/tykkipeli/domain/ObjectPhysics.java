package tykkipeli.domain;

import java.util.ArrayList;

public class ObjectPhysics {

    public ObjectPhysics() {
    }

    public double[] nextLocation(GraphicObject object) {
        double[] location = object.getLocation();
        double speed = object.getSpeed();
        double direction = object.getDirection();
        double[] acc = object.getAcceleration();
        double deltaX = speed * Math.cos(direction) + 0.5 * acc[0] * Math.cos(acc[1]);
        double deltaY = speed * Math.sin(direction) + 0.5 * acc[0] * Math.sin(acc[1]);
        location[0] += deltaX;
        location[1] += deltaY;
        return location;
    }

    public double[] sumAcceleration(GraphicObject object, ArrayList<double[]> forces) {
        double[] netAcceleration = object.getAcceleration();
        if (forces.isEmpty()) {
            return netAcceleration;
        } else {
            double deltaX = 0.0;
            double deltaY = 0.0;
            for (double[] f : forces) {
                deltaX += f[0] * Math.cos(f[1]);
                deltaY += f[0] * Math.sin(f[1]);
            }
            netAcceleration[0] = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
            netAcceleration[1] = Math.atan(deltaX / deltaY);
        }
        return netAcceleration;
    }

    public double[] nextVelocity(GraphicObject object, double[] newAcceleration) {
        double[] velocity = {object.getSpeed(), object.getDirection()};
        double[] oldAcceleration = object.getAcceleration();
        double deltaX = 0.5 * (oldAcceleration[0] * Math.cos(oldAcceleration[1]) + newAcceleration[0] * Math.cos(newAcceleration[1]));
        double deltaY = 0.5 * (oldAcceleration[0] * Math.sin(oldAcceleration[1]) + newAcceleration[0] * Math.sin(newAcceleration[1]));
        velocity[1] = Math.atan((deltaY + velocity[0] * Math.sin(velocity[1])) / (deltaX + velocity[0] * Math.cos(velocity[1])));
        velocity[0] += Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return velocity;
    }

    public double[] nextStepOnlyGravity(GraphicObject object) {
        double[] newLoc = nextLocation(object);
        double[] newVel = nextVelocity(object, object.getAcceleration());
        object.setLocation(newLoc);
        object.setSpeed(newVel[0]);
        object.setSpeedDirection(newVel[1]);
        return newLoc;
    }

//    public double[] calculateDrag() {
//        TODO
//    }
}
