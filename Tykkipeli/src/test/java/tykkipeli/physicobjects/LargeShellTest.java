package tykkipeli.physicobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;

public class LargeShellTest {

    private LargeShell shell;

    public LargeShellTest() {
    }

    @Before
    public void setUp() {
        shell = new LargeShell();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void LargeShellDefaulConstructorTest() {
        double expMass = 2.0;
        double expDmg = 25.0;
        double expDrg = 1;
        assertEquals(expMass, shell.getMass(), 0.00001);
        assertEquals(expDmg, shell.getDamage(), 0.00001);
        assertEquals(expDrg, shell.getDragCoefficient(), 0.00001);
    }

    @Test
    public void LargeShellLocationConstructorTest() {
        this.shell = new LargeShell(new Vector(1, 1));
        assertEquals(1.0, shell.getLocation().getX(), 0.00001);
        assertEquals(1.0, shell.getLocation().getY(), 0.00001);
    }

    @Test
    public void LargeShellThirdConstructorTest() {
        this.shell = new LargeShell(new Vector(1, 1), new Vector(2, 2), new Vector(3, 3));
        assertEquals(2.0, shell.getVelocity().getX(), 0.00001);
        assertEquals(2.0, shell.getVelocity().getY(), 0.00001);
        assertEquals(3.0, shell.getAcceleration().getX(), 0.00001);
        assertEquals(3.0, shell.getAcceleration().getY(), 0.00001);
    }

}
