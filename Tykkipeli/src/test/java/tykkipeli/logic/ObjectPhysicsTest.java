package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.physicobjects.BasicShell;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.LargeShell;

public class ObjectPhysicsTest {

    private ObjectPhysics phys;
    private Ammo testAmmo;
    private GameStatus status;

    public ObjectPhysicsTest() {
    }

    @Before
    public void setUp() {
        phys = new ObjectPhysics();
        testAmmo = new Ammo(new Vector(1, 1), new Vector(1, 1), new Vector(1, 1), 1, 1, 1);
        status = new GameStatus();
        status.getPlayer(0).getPlayerCannon().setLocationXY(1, 1);
        status.getPlayer(1).getPlayerCannon().setLocationXY(2, 2);
        List<Ammo> p0List = status.getAmmolist(0);
        p0List.get(0).setLocationXY(1, 1);
        p0List.get(1).setLocationXY(1, 2);
        List<Ammo> p1List = status.getAmmolist(1);
        p1List.get(0).setLocationXY(2, 1);
        p1List.get(1).setLocationXY(2, 2);
        status.setGravity(new Vector(0, 1));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nextLocationForDefaultTestAmmo() {
        Vector ret = phys.nextLocation(testAmmo);
        assertEquals(2.5, ret.getX(), 0.0001);
        assertEquals(2.5, ret.getY(), 0.0001);
    }

    @Test
    public void sumAccelerationEmptyList() {
        Vector ret = phys.sumAcceleration(testAmmo, new ArrayList<>());
        assertEquals(1.0, ret.getX(), 0.0001);
        assertEquals(1.0, ret.getY(), 0.0001);
    }

    @Test
    public void sumAccelerationNullList() {
        Vector ret = phys.sumAcceleration(testAmmo, null);
        assertEquals(1.0, ret.getX(), 0.0001);
        assertEquals(1.0, ret.getY(), 0.0001);
    }

    @Test
    public void sumAccelerationNewList() {
        ArrayList<Vector> accList = new ArrayList<>();
        accList.add(new Vector(0, 1));
        accList.add(new Vector(0, 2.5));
        Vector ret = phys.sumAcceleration(testAmmo, accList);
        assertEquals(0.0, ret.getX(), 0.0001);
        assertEquals(3.5, ret.getY(), 0.0001);
    }

    @Test
    public void nextVelocityTest() {
        status.setWind(0);
        Vector ret = phys.nextVelocity(testAmmo, new Vector(1, 1), status);
        assertEquals(2.0, ret.getX(), 0.0001);
        assertEquals(2.0, ret.getY(), 0.0001);
    }

    @Test
    public void calculateDragOnStart() {
        Vector ret = phys.calculateDrag(testAmmo);
        double cmp = -6.5 * Math.pow(10, -3);
        assertEquals(cmp, ret.getX(), 0.000001);
        assertEquals(cmp, ret.getY(), 0.000001);
    }

    @Test
    public void nextStepTest() {
        phys.nextStep(testAmmo, status);
        double cmpAccX = -6.5 * Math.pow(10, -3);
        double cmpAccY = 1.0 - 6.5 * Math.pow(10, -3);
        assertEquals(2.5, testAmmo.getLocation().getX(), 0.00001);
        assertEquals(2.5, testAmmo.getLocation().getY(), 0.00001);
        assertEquals(cmpAccX, testAmmo.getAcceleration().getX(), 0.00001);
        assertEquals(cmpAccY, testAmmo.getAcceleration().getY(), 0.00001);
    }

}
