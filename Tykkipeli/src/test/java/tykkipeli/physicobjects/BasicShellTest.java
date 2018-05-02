package tykkipeli.physicobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;

public class BasicShellTest {

    private BasicShell shell;

    public BasicShellTest() {
    }

    @Before
    public void setUp() {
        shell = new BasicShell();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void BasicShellDefaulConstructorTest() {
        double expMass = 1.0;
        double expDmg = 10.0;
        double expDrg = 0.25;
        assertEquals(expMass, shell.getMass(), 0.00001);
        assertEquals(expDmg, shell.getDamage(), 0.00001);
        assertEquals(expDrg, shell.getDragCoefficient(), 0.00001);
    }

    @Test
    public void BasicShellLocationConstructorTest() {
        this.shell = new BasicShell(new Vector(1, 1));
        assertEquals(1.0, shell.getLocation().getX(), 0.00001);
        assertEquals(1.0, shell.getLocation().getY(), 0.00001);
    }

    @Test
    public void BasicShellThirdConstructorTest() {
        this.shell = new BasicShell(new Vector(1, 1), new Vector(2, 2), new Vector(3, 3));
        assertEquals(2.0, shell.getVelocity().getX(), 0.00001);
        assertEquals(2.0, shell.getVelocity().getY(), 0.00001);
        assertEquals(3.0, shell.getAcceleration().getX(), 0.00001);
        assertEquals(3.0, shell.getAcceleration().getY(), 0.00001);
    }

}
