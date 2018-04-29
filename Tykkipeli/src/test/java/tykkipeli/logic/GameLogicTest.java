package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.physicobjects.BasicShell;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.physicobjects.LargeShell;

public class GameLogicTest {

    GameLogic gameLogic;
    GameStatus gameStatus;

    public GameLogicTest() {
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
        gameStatus = new GameStatus(testPlayerlist, testAmmolist1, testAmmolist2);

        gameLogic = new GameLogic(gameStatus);
    }

    @After
    public void tearDown() {
    }

    // keyPressed
    @Test
    public void upKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("UP");
        double cmp = Math.PI / 4 + 0.005;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void upKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("UP");
        double cmp = Math.PI / 4 + 0.005;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void downKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("DOWN");
        double cmp = Math.PI / 4 - 0.005;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void downKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("DOWN");
        double cmp = Math.PI / 4 - 0.005;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void rightKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("RIGHT");
        double cmp = 10.25;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void rightKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("RIGHT");
        double cmp = 9.75;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void leftKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("LEFT");
        double cmp = 9.75;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void leftKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("LEFT");
        double cmp = 10.25;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    // ComputerPlays
    @Test
    public void playerOneValuesAreOkAfterComputerPlaysOnce() {
        gameLogic.computerPlays(1);
        assertEquals((Math.PI / 4), gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle(), (Math.PI / 4));
        assertEquals(16.0, gameStatus.getPlayer(1).getPlayerCannon().getCannonPower(), 5.0);
    }

    @Test
    public void ComputerGivesTurnToHumanPlayer() {
        gameLogic.computerPlays(1);
        assertEquals(0, gameStatus.getTurn());
    }

    @Test
    public void ComputerSetsWaitStatusToOne() {
        gameLogic.computerPlays(1);
        assertEquals(1, gameStatus.getPhase());
    }
}
