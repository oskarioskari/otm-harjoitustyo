package tykkipeli.logic;

import tykkipeli.objects.Player;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.BasicShell;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.physicobjects.LargeShell;

public class GameStatusTest {

    GameStatus status;

    public GameStatusTest() {
    }

    @Before
    public void setUp() {
        List<Player> testPlayerlist = new ArrayList<>();
        testPlayerlist.add(new Player(0, new Cannon(new Vector(1, 1))));
        testPlayerlist.add(new Player(1, new Cannon(new Vector(2, 2))));
        List<Ammo> testAmmolist1 = new ArrayList<>();
        testAmmolist1.add(new BasicShell(new Vector(1, 1)));
        testAmmolist1.add(new LargeShell(new Vector(1, 2)));
        List<Ammo> testAmmolist2 = new ArrayList<>();
        testAmmolist2.add(new BasicShell(new Vector(2, 1)));
        testAmmolist2.add(new LargeShell(new Vector(2, 2)));
        status = new GameStatus(testPlayerlist, testAmmolist1, testAmmolist2);
    }

    @After
    public void tearDown() {
    }

    // setTurn
    @Test
    public void setTurnPlayerOne() {
        status.setTurn(1);
        int ret = status.getTurn();
        assertEquals(1, ret);
    }

    // getTurn
    @Test
    public void getTurnAtBeginning() {
        int ret = status.getTurn();
        assertEquals(0, ret);
    }

    // setWait
    @Test
    public void setWaitStatusToOne() {
        status.setPhase(1);
        assertEquals(1, status.getPhase());
    }

    // getWait
    @Test
    public void getWaitStatusAtStart() {
        assertEquals(0, status.getPhase());
    }

    // setWaitOver
    @Test
    public void setWaitOverPlayerOneValueOne() {
        status.setWaitOver(0, 1);
        assertEquals(1, status.getWaitOver(0));
        assertEquals(0, status.getWaitOver(1));
    }

    // getWaitOver
    @Test
    public void getWaitOverStatusAtStart() {
        assertEquals(0, status.getWaitOver(0));
        assertEquals(0, status.getWaitOver(1));
    }

    // addPoint
    @Test
    public void addOnePointToPlayer0() {
        status.addPoint(0);
        int[] ret = status.getPlayerScores();
        int[] cmp = {1, 0};
        assertArrayEquals(cmp, ret);
    }

    @Test
    public void addOnePointToNonexistingPlayer() {
        status.addPoint(-1);
        int[] ret = status.getPlayerScores();
        int[] cmp = {0, 0};
        assertArrayEquals(cmp, ret);
    }

    // getPlayerScores
    @Test
    public void getScoreAtBeginning() {
        int[] ret = status.getPlayerScores();
        int[] cmp = {0, 0};
        assertArrayEquals(cmp, ret);
    }

    // getPlayerScore
    @Test
    public void NonExistingPlayerReturnsZeroPoints() {
        assertEquals(0, status.getPlayerScore(2));
    }

    // setSelectedWeapon
    @Test
    public void bothPlayersSelectSecondWeaponOnList() {
        status.setSelectedWeapon(0, 1);
        status.setSelectedWeapon(1, 1);
        int[] ret = {status.getSelectedWeaponNumber(0), status.getSelectedWeaponNumber(1)};
        int[] cmp = {1, 1};
        assertArrayEquals(cmp, ret);
    }

    // getSelectedWeaponNumber
    @Test
    public void getDefaultWeaponSelection() {
        int[] ret = {status.getSelectedWeaponNumber(0), status.getSelectedWeaponNumber(1)};
        int[] cmp = {0, 0};
        assertArrayEquals(cmp, ret);
    }

    // getPlayerWeapon
    @Test
    public void getPlayerWeaponReturnsRightObject() {
        Vector cmp = new Vector(1, 1);
        Vector ret = status.getPlayerWeapon(0).getLocation();
        assertEquals(cmp.getX(), ret.getX(), 0.01);
        assertEquals(cmp.getY(), ret.getY(), 0.01);
    }

    // setGravity
    @Test
    public void setGravityToZeroTen() {
        status.setGravity(new Vector(0, 10));
        Vector cmp = new Vector(0, 10);
        assertEquals(cmp.getX(), status.getGravity().getX(), 0.01);
        assertEquals(cmp.getY(), status.getGravity().getY(), 0.01);
    }

    // getGravity
    @Test
    public void getDefaultGravity() {
        Vector cmp = new Vector(0, 0.5);
        Vector ret = status.getGravity();
        assertEquals(cmp.getX(), ret.getX(), 0.01);
        assertEquals(cmp.getY(), ret.getY(), 0.01);
    }

    // getPlayerList
    @Test
    public void getPlayerListReturnsSomethingNotNull() {
        assertNotNull(status.getPlayerList());
    }

    @Test
    public void getPlayerListReturnsList() {
        assertThat(status.getPlayerList(), isA(List.class));
    }

    // getPlayer
    @Test
    public void getPlayerReturnsSomethingNotNull() {
        assertNotNull(status.getPlayer(0));
    }

    @Test
    public void getPlayerReturnsPlayer() {
        assertThat(status.getPlayer(0), isA(Player.class));
    }

    // getAmmolist
    @Test
    public void getAmmolistReturnsSomethingNotNull() {
        assertNotNull(status.getAmmolist());
    }

    @Test
    public void getAmmolistReturnsList() {
        assertThat(status.getAmmolist(), isA(List.class));
    }

}
