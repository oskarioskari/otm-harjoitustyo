package tykkipeli.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VectorTest {

    private Vector vector;

    public VectorTest() {
    }

    @Before
    public void setUp() {
        vector = new Vector(1, 2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setComponents method, of class Vector.
     */
    @Test
    public void testSetComponents() {
        double x = 0.0;
        double y = 0.0;
        vector.setComponents(x, y);
        assertEquals(0.0, vector.getX(), 0.00001);
        assertEquals(0.0, vector.getY(), 0.00001);
    }

    /**
     * Test of setX method, of class Vector.
     */
    @Test
    public void testSetX() {
        double x = 0.0;
        vector.setX(x);
        assertEquals(0.0, vector.getX(), 0.00001);
    }

    /**
     * Test of setY method, of class Vector.
     */
    @Test
    public void testSetY() {
        double y = 0.0;
        vector.setY(y);
        assertEquals(0.0, vector.getY(), 0.00001);
    }

    /**
     * Test of addX method, of class Vector.
     */
    @Test
    public void testAddX() {
        double deltaX = 2.0;
        vector.addX(deltaX);
        assertEquals(3.0, vector.getX(), 0.00001);
    }

    /**
     * Test of addY method, of class Vector.
     */
    @Test
    public void testAddY() {
        double deltaY = 4.0;
        vector.addY(deltaY);
        assertEquals(6.0, vector.getY(), 0.00001);
    }

    /**
     * Test of getComponents method, of class Vector.
     */
    @Test
    public void testGetComponents() {
        double[] exp = {1, 2};
        double[] res = vector.getComponents();
        assertEquals(exp[0], res[0], 0.00001);
        assertEquals(exp[1], res[1], 0.00001);
    }

    /**
     * Test of getX method, of class Vector.
     */
    @Test
    public void testGetX() {
        double expResult = 1.0;
        double result = vector.getX();
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of getY method, of class Vector.
     */
    @Test
    public void testGetY() {
        double expResult = 2.0;
        double result = vector.getY();
        assertEquals(expResult, result, 0.00001);
    }

}
