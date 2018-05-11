package tykkipeli.logic;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tykkipeli.objects.Vector;
import tykkipeli.physicobjects.Ammo;

public class GameLogicTest {

    GameLogic gameLogic;
    GameStatus gameStatus;

    public GameLogicTest() {
    }

    @AfterClass
    public static void tearDownClass() {
        Path path = FileSystems.getDefault().getPath("res", "testdatabase.db");
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Before
    public void setUp() {
        gameStatus = new GameStatus();
        gameStatus.getPlayer(0).getPlayerCannon().setLocationXY(1, 1);
        gameStatus.getPlayer(1).getPlayerCannon().setLocationXY(2, 2);
        List<Ammo> p0List = gameStatus.getAmmolist(0);
        p0List.get(0).setLocationXY(1, 1);
        p0List.get(1).setLocationXY(1, 2);
        List<Ammo> p1List = gameStatus.getAmmolist(1);
        p1List.get(0).setLocationXY(2, 1);
        p1List.get(1).setLocationXY(2, 2);

        gameLogic = new GameLogic(gameStatus);
    }

    @After
    public void tearDown() {
    }

    // keyPressed
    @Test
    public void upKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("UP");
        double cmp = Math.PI / 4 + 0.0087;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void upKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("UP");
        double cmp = Math.PI / 4 + 0.0087;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void downKeyPressedPlayerZeroChangesAngle() {
        gameLogic.keyPressed("DOWN");
        double cmp = Math.PI / 4 - 0.0087;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void downKeyPressedPlayerOneChangesAngle() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("DOWN");
        double cmp = Math.PI / 4 - 0.0087;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle();
        assertEquals(cmp, ret, 0.0001);
    }

    @Test
    public void rightKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("RIGHT");
        double cmp = 20.50;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void rightKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("RIGHT");
        double cmp = 19.50;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void leftKeyPressedPlayerZeroChangesPower() {
        gameLogic.keyPressed("LEFT");
        double cmp = 19.50;
        double ret = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void leftKeyPressedPlayerOneChangesPower() {
        gameStatus.setTurn(1);
        gameLogic.keyPressed("LEFT");
        double cmp = 20.50;
        double ret = gameStatus.getPlayer(1).getPlayerCannon().getCannonPower();
        assertEquals(cmp, ret, 0.001);
    }

    @Test
    public void enterKeyPressed() {
        gameStatus.setTurn(0);
        gameLogic.keyPressed("ENTER");
        assertEquals(1, gameStatus.getTurn());
    }

    @Test
    public void digitOnePressed() {
        gameLogic.keyPressed("DIGIT1");
        assertEquals(0, gameStatus.getSelectedWeaponNumber(0));
    }

    @Test
    public void digitTwoPressed() {
        gameLogic.keyPressed("DIGIT2");
        assertEquals(1, gameStatus.getSelectedWeaponNumber(0));
    }

    // ComputerPlays
    @Test
    public void playerOneValuesAreOkAfterComputerPlaysOnce() {
        gameLogic.computerPlays(1);
        assertEquals((Math.PI / 4), gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle(), (Math.PI / 4));
        assertEquals(25.0, gameStatus.getPlayer(1).getPlayerCannon().getCannonPower(), 8);
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

    // CheckPlayerParameters
    @Test
    public void checkGameParametersDefaultSituation() {
        gameLogic.checkGameParameters();
        double expAngle = Math.PI / 4;
        double expPower = 20;
        assertEquals(expAngle, gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle(), 0.00001);
        assertEquals(expPower, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(), 0.00001);
    }

    @Test
    public void checkGameParametersAngleTooHighPowerTooLow() {
        gameStatus.getPlayer(0).getPlayerCannon().setCannonAngle(Math.PI / 2 + 1);
        gameStatus.getPlayer(0).getPlayerCannon().setCannonPower(-10);
        gameLogic.checkGameParameters();
        double expAngle = Math.PI / 2;
        double expPower = 0;
        assertEquals(expAngle, gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle(), 0.00001);
        assertEquals(expPower, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(), 0.00001);
    }

    @Test
    public void checkGameParametersAngleTooLowPowerTooHigh() {
        gameStatus.getPlayer(0).getPlayerCannon().setCannonAngle(-Math.PI / 4);
        gameStatus.getPlayer(0).getPlayerCannon().setCannonPower(10000);
        gameLogic.checkGameParameters();
        double expAngle = 0.00007;
        double expPower = 50;
        assertEquals(expAngle, gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle(), 0.0000001);
        assertEquals(expPower, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(), 0.0000001);
    }

    // GetGameAi
    @Test
    public void getGameAiReturnsNotNull() {
        assertNotNull(gameLogic.getGameAi());
    }

    // keyCodeENTER
    @Test
    public void keyCodeEnterWhenTurnIsZeroAndPlayerOneIsHuman() {
        gameLogic.keycodeEnter(0);
        assertEquals(1, gameStatus.getTurn());
        assertEquals(0, gameStatus.getPhase());
    }

    public void keyCodeEnterWhenTurnIsZeroAndPlayerOneIsComputer() {
        gameStatus.getPlayer(1).setPlayerHumanStatus(false);
        gameLogic.keycodeEnter(0);
        assertFalse(gameStatus.getPlayer(1).getPlayerHumanStatus());
        assertEquals(0, gameStatus.getTurn());
        assertEquals(1, gameStatus.getPhase());
    }

    public void keyCodeEnterWhenTurnIsOne() {
        gameLogic.keycodeEnter(1);
        assertEquals(0, gameStatus.getTurn());
        assertEquals(1, gameStatus.getPhase());
    }

    // moveAmmo
    @Test
    public void testMoveAmmo() {
        gameStatus.getAmmolist(0).add(new Ammo(1, 1, 0.0));
        gameStatus.getAmmolist(0).get(0).setVelocityXY(1, 0);
        gameStatus.getAmmolist(0).get(0).setAccelerationXY(0, 0);
        gameStatus.getAmmolist(0).get(0).setLocationXY(0, 0);
        gameLogic.moveAmmo();
        assertEquals(1.0, gameStatus.getAmmolist(0).get(0).getLocation().getX(), 0.00001);
    }

    // getHighScoresDao
    @Test
    public void getHighScoresDaoReturnsNotNull() {
        assertNotNull(gameLogic.getHighScoresDao());
    }

    // getTopThree
    @Test
    public void getTopThreeReturnsNotNull() {
        gameLogic.setDatabaseAddress("jdbc:sqlite:res/testdatabase.db");
        List<String> res = gameLogic.getTopThree(1);
        assertNotNull(res);
    }

    // saveNewHighScore
    @Test
    public void testSaveNewHighScore() {
        gameLogic.setDatabaseAddress("jdbc:sqlite:res/testdatabase.db");
        gameLogic.saveNewHighscore("testPlayer", 999, 2);
        List<String> res = gameLogic.getTopThree(2);
        assertNotNull(res);
    }

    // checkAmmoCollision
    @Test
    public void noCollisionInSky() {
        assertFalse(gameLogic.checkAmmoCollision(300, 200));
    }

    @Test
    public void collisionWhenInGround() {
        assertTrue(gameLogic.checkAmmoCollision(0, 405));
    }

    @Test
    public void collisionWhenInHill() {
        assertTrue(gameLogic.checkAmmoCollision(400, 355));
    }

    @Test
    public void noCollisionAfterPastHitZone() {
        assertFalse(gameLogic.checkAmmoCollision(400, 380));
    }

    @Test
    public void collisionWhenTooFarLeft() {
        assertTrue(gameLogic.checkAmmoCollision(-200, 0));
    }

    @Test
    public void collisionWhenTooFarRight() {
        assertTrue(gameLogic.checkAmmoCollision(1000, 0));
    }

    @Test
    public void collisionWhenTooFarUnderground() {
        assertTrue(gameLogic.checkAmmoCollision(0, 600));
    }

    // checkIfHit
    @Test
    public void PlayerZeroCannonCanBeHit() {
        Vector location = gameStatus.getPlayer(0).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(0).setHealth(10);
        gameLogic.checkIfHit(1, 0, location, gameStatus);
        int ret = gameStatus.getPlayer(0).getHealth();
        assertEquals(0, ret);
    }

    @Test
    public void PlayerOneCannonCanBeHit() {
        Vector location = gameStatus.getPlayer(1).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(1).setHealth(10);
        gameLogic.checkIfHit(1, 1, location, gameStatus);
        int ret = gameStatus.getPlayer(1).getHealth();
        assertEquals(0, ret);
    }

    @Test
    public void PlayerZeroCannonCannotBeHitAfterWaitOver() {
        Vector location = gameStatus.getPlayer(0).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(0).setHealth(10);
        gameStatus.setWaitOver(1, true);
        gameLogic.checkIfHit(1, 0, location, gameStatus);
        int ret = gameStatus.getPlayer(0).getHealth();
        assertEquals(10, ret);
    }

    @Test
    public void PlayerOneCannonCannotBeHitAfterWaitOver() {
        Vector location = gameStatus.getPlayer(1).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(1).setHealth(10);
        gameStatus.setWaitOver(0, true);
        gameLogic.checkIfHit(1, 1, location, gameStatus);
        int ret = gameStatus.getPlayer(1).getHealth();
        assertEquals(10, ret);
    }

    @Test
    public void PlayerZeroHealthDoesntChangeWhenNotHit() {
        Vector location = gameStatus.getPlayer(0).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(0).setHealth(10);
        gameLogic.checkIfHit(72, 0, location, gameStatus);
        int ret = gameStatus.getPlayer(0).getHealth();
        assertEquals(10, ret);
    }

    @Test
    public void PlayerOneHealthDoesntChangeWhenNotHit() {
        Vector location = gameStatus.getPlayer(1).getPlayerCannon().getLocation();
        location.setComponents(1, 1);
        gameStatus.getPlayer(1).setHealth(10);
        gameLogic.checkIfHit(69, 1, location, gameStatus);
        int ret = gameStatus.getPlayer(1).getHealth();
        assertEquals(10, ret);
    }

    // calculateFinalScore
    @Test
    public void finalScoreIsRight() {
        gameStatus.setScore(0, 10);
        gameStatus.getPlayer(0).setHealth(50);
        int ret = gameLogic.calculateFinalScore();
        assertEquals(15, ret);
    }

    // resetAim
    @Test
    public void valuesRightAfterAimReset() {
        gameStatus.getPlayer(0).getPlayerCannon().setCannonAngle(Math.PI / 5);
        gameStatus.getPlayer(0).getPlayerCannon().setCannonPower(40);
        gameLogic.resetAim();
        double angle = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle();
        double power = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower();
        assertEquals(Math.PI / 4, angle, 0.000001);
        assertEquals(20, power, 0.000001);
    }

    // checkIfWaitOver
    @Test
    public void gameContinuesWhenWaitOverForBothPlayers() {
        gameStatus.setPhase(1);
        gameStatus.setWaitOver(0, true);
        gameStatus.setWaitOver(1, true);
        gameLogic.checkIfWaitOver();
        assertEquals(0, gameStatus.getPhase());
    }

    @Test
    public void waitingContinuesIfPlayerZeroIsStillWaiting() {
        gameStatus.setPhase(1);
        gameStatus.setWaitOver(0, false);
        gameStatus.setWaitOver(1, true);
        gameLogic.checkIfWaitOver();
        assertEquals(1, gameStatus.getPhase());
    }

    @Test
    public void waitingContinuesIfPlayerOneIsStillWaiting() {
        gameStatus.setPhase(1);
        gameStatus.setWaitOver(0, true);
        gameStatus.setWaitOver(1, false);
        gameLogic.checkIfWaitOver();
        assertEquals(1, gameStatus.getPhase());
    }
}
