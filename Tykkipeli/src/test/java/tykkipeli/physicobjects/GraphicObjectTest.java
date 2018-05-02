/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tykkipeli.physicobjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;

/**
 *
 * @author oskari
 */
public class GraphicObjectTest {

    private GraphicObject object;

    public GraphicObjectTest() {
    }

    @Before
    public void setUp() {
        object = new GraphicObject();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(0.0, object.getDragCoefficient(), 0.00001);
    }

    @Test
    public void testSecondConstructor() {
        object = new GraphicObject(1.0);
        assertEquals(1.0, object.getDragCoefficient(), 0.00001);
    }

    @Test
    public void testConstructorWithThreeVectorsAndOneDouble() {
        object = new GraphicObject(new Vector(1, 1), new Vector(2, 2), new Vector(3, 3), 10);
        assertEquals(10.0, object.getMass(), 0.00001);
    }

    @Test
    public void testSetAccelerationXY() {
        object.setAccelerationXY(11, 12);
        assertEquals(11.0, object.getAcceleration().getX(), 0.00001);
        assertEquals(12.0, object.getAcceleration().getY(), 0.00001);
    }

    @Test
    public void testSetMass() {
        object.setMass(12345);
        assertEquals(12345, object.getMass(), 0.00001);
    }

    @Test
    public void testSetLocation() {
        object.setLocation(new Vector(10, 11));
        assertEquals(10.0, object.getLocation().getX(), 0.00001);
        assertEquals(11.0, object.getLocation().getY(), 0.00001);
    }

    @Test
    public void testSetVelocity() {
        object.setVelocity(new Vector(10, 11));
        assertEquals(10.0, object.getVelocity().getX(), 0.00001);
        assertEquals(11.0, object.getVelocity().getY(), 0.00001);
    }
}
