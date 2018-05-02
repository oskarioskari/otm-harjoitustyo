package tykkipeli.physicobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;

public class CannonTest {

    private Cannon cannon;

    public CannonTest() {
    }

    @Before
    public void setUp() {
        cannon = new Cannon(new Vector(1, 1));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of second constructor of class Cannon.
     */
    @Test
    public void testSecondCannonConstructor() {
        cannon = new Cannon(new Vector(1, 1), Math.PI / 2, 100);
        assertEquals(Math.PI / 2, cannon.getCannonAngle(), 0.00001);
        assertEquals(100, cannon.getCannonPower(), 0.00001);
    }

    /**
     * Test of getCannonAngle method, of class Cannon.
     */
    @Test
    public void testGetCannonAngle() {
        double exp = Math.PI / 4;
        double res = cannon.getCannonAngle();
        assertEquals(exp, res, 0.00001);
    }

    /**
     * Test of setCannonAngle method, of class Cannon.
     */
    @Test
    public void testSetCannonAngle() {
        cannon.setCannonAngle(Math.PI / 5);
        double exp = Math.PI / 5;
        double res = cannon.getCannonAngle();
        assertEquals(exp, res, 0.00001);
    }

    /**
     * Test of increaseCannonAngle method, of class Cannon.
     */
    @Test
    public void testIncreaseCannonAngle() {
        cannon.increaseCannonAngle(0.1);
        double exp = Math.PI / 4 + 0.1;
        double res = cannon.getCannonAngle();
        assertEquals(exp, res, 0.00001);
    }

    /**
     * Test of getCannonPower method, of class Cannon.
     */
    @Test
    public void testGetCannonPower() {
        assertEquals(10.0, cannon.getCannonPower(), 0.00001);
    }

    /**
     * Test of setCannonPower method, of class Cannon.
     */
    @Test
    public void testSetCannonPower() {
        cannon.setCannonPower(23.4);
        assertEquals(23.4, cannon.getCannonPower(), 0.00001);
    }

    /**
     * Test of increaseCannonPower method, of class Cannon.
     */
    @Test
    public void testIncreaseCannonPower() {
        cannon.increaseCannonPower(15.321);
        assertEquals(25.321, cannon.getCannonPower(), 0.00001);
    }

}
