package tykkipeli.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.physicobjects.Cannon;

public class PlayerTest {

    private Player player;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player = new Player(0, new Cannon(new Vector(1, 1)));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPlayerNum method, of class Player.
     */
    @Test
    public void testGetPlayerNum() {
        int cmp = 0;
        assertEquals(cmp, player.getPlayerNum());
    }

    /**
     * Test of getPlayerCannon method, of class Player.
     */
    @Test
    public void testGetPlayerCannonReturnsNotNull() {
        assertNotNull(player.getPlayerCannon());
    }

    @Test
    public void testGetPlayerCannonReturnsRightCannon() {
        Vector cmp = new Vector(1, 1);
        assertEquals(cmp.getX(), player.getPlayerCannon().getLocation().getX(), 0.0001);
        assertEquals(cmp.getY(), player.getPlayerCannon().getLocation().getY(), 0.0001);
    }

    /**
     * Test of setPlayerHumanStatus method, of class Player.
     */
    @Test
    public void testSetPlayerHumanStatus() {
        player.setPlayerHumanStatus(false);
        assertEquals(false, player.getPlayerHumanStatus());
    }

    /**
     * Test of getPlayerHumanStatus method, of class Player.
     */
    @Test
    public void testGetPlayerHumanStatusDefaultValue() {
        assertEquals(true, player.getPlayerHumanStatus());
    }

    /**
     * Test of getHealth method, of class Player.
     */
    @Test
    public void testGetHealthOnStart() {
        assertEquals(100.0, player.getHealth(), 0.00001);
    }

    /**
     * Test of setHealth method, of class Player.
     */
    @Test
    public void testSetHealth() {
        player.setHealth(30);
        assertEquals(30.0, player.getHealth(), 0.00001);
    }

    /**
     * Test of addHealth method, of class Player.
     */
    @Test
    public void testAddHealth() {
        player.addHealth(100);
        assertEquals(200, player.getHealth(), 0.00001);
    }

    /**
     * Test of getX method, of class Player.
     */
    @Test
    public void testGetX() {
        assertEquals(1.0, player.getX(), 0.00001);
    }

    /**
     * Test of getY method, of class Player.
     */
    @Test
    public void testGetY() {
        assertEquals(1.0, player.getY(), 0.00001);
    }

}
