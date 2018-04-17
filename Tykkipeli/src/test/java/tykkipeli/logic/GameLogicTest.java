package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.BasicShell;
import tykkipeli.objects.Cannon;
import tykkipeli.objects.GraphicObject;
import tykkipeli.objects.Player;

public class GameLogicTest {
    
    GameLogic gameLogic;
    GameStatus gameStatus;
    
    public GameLogicTest() {
    }
    
    @Before
    public void setUp() {
        gameLogic = new GameLogic();
        
        List<Player> testPlayerlist = new ArrayList<>();
        testPlayerlist.add(new Player(0, new Cannon(1, 1), true));
        testPlayerlist.add(new Player(1, new Cannon(1, 1), false));
        List<GraphicObject> testAmmolist1 = new ArrayList<>();
        testAmmolist1.add(new BasicShell(1, 1));
        testAmmolist1.add(new BasicShell(1, 2));
        List<GraphicObject> testAmmolist2 = new ArrayList<>();
        testAmmolist2.add(new BasicShell(2, 1));
        testAmmolist2.add(new BasicShell(2, 2));
        double[] testGravity = {0, 1};
        gameStatus = new GameStatus(testPlayerlist, testAmmolist1, testAmmolist2, testGravity);
    }
    
    @After
    public void tearDown() {
    }

    // keyPressed
    @Test
    public void upKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("UP", this.gameStatus);
        double cmp = Math.PI / 4 + 0.005;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }
    
    @Test
    public void upKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("UP", this.gameStatus);
        double cmp = Math.PI / 4 + 0.005;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }
    
    @Test
    public void downKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("DOWN", this.gameStatus);
        double cmp = Math.PI / 4 - 0.005;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }
    
    @Test
    public void downKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("DOWN", this.gameStatus);
        double cmp = Math.PI / 4 - 0.005;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }
    
    @Test
    public void rightKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("RIGHT", this.gameStatus);
        double cmp = 10.1;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }
    
    @Test
    public void rightKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("RIGHT", gameStatus);
        double cmp = 9.9;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }
    
    @Test
    public void leftKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("LEFT", gameStatus);
        double cmp = 9.9;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }
    
    @Test
    public void leftKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("LEFT", gameStatus);
        double cmp = 10.1;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    // easyComputerPlays
    @Test
    public void playerOneValuesAreOkAfterEasyComputerPlaysOnce() {
        gameLogic.easyComputerPlays(1, gameStatus);
        assertEquals((Math.PI / 4), gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle(), (Math.PI / 4));
        assertEquals(16.0, gameStatus.getPlayer(1).getPlayerCannon().getCannonPower(), 5.0);
    }
    
    @Test
    public void easyComputerGivesTurnToHumanPlayer() {
        gameLogic.easyComputerPlays(1, gameStatus);
        assertEquals(0, gameStatus.getTurn());
    }
    
    @Test
    public void easyComputerSetsWaitStatusToOne() {
        gameLogic.easyComputerPlays(1, gameStatus);
        assertEquals(1, gameStatus.getWait());
    }

    /**
     * Test of easyComputerPlays method, of class GameLogic.
     */
//    @Test
//    public void testEasyComputerPlays() {
//        System.out.println("easyComputerPlays");
//        int aiPlayer = 0;
//        GameStatus gameStatus = null;
//        GameLogic instance = new GameLogic();
//        instance.easyComputerPlays(aiPlayer, gameStatus);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fireCannon method, of class GameLogic.
//     */
//    @Test
//    public void testFireCannon() {
//        System.out.println("fireCannon");
//        int player = 0;
//        List<Player> playerList = null;
//        GraphicObject weapon = null;
//        double[] gravity = null;
//        GameLogic instance = new GameLogic();
//        instance.fireCannon(player, playerList, weapon, gravity);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of moveAmmo method, of class GameLogic.
//     */
//    @Test
//    public void testMoveAmmo() {
//        System.out.println("moveAmmo");
//        GameStatus gameStatus = null;
//        GameLogic instance = new GameLogic();
//        instance.moveAmmo(gameStatus);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkPlayerParameters method, of class GameLogic.
//     */
//    @Test
//    public void testCheckPlayerParameters() {
//        System.out.println("checkPlayerParameters");
//        GameStatus gameStatus = null;
//        GameLogic instance = new GameLogic();
//        instance.checkPlayerParameters(gameStatus);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
