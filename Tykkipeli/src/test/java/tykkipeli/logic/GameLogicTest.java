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
    public void checkPlayerParametersDefaultSituation() {
        gameLogic.checkPlayerParameters();
        double expAngle = Math.PI / 4;
        double expPower = 20;
        assertEquals(expAngle, gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle(), 0.00001);
        assertEquals(expPower, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(), 0.00001);
    }

    @Test
    public void checkPlayerParametersAngleTooHighPowerTooLow() {
        gameStatus.getPlayer(0).getPlayerCannon().setCannonAngle(Math.PI / 2 + 1);
        gameStatus.getPlayer(0).getPlayerCannon().setCannonPower(-10);
        gameLogic.checkPlayerParameters();
        double expAngle = Math.PI / 2;
        double expPower = 0;
        assertEquals(expAngle, gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle(), 0.00001);
        assertEquals(expPower, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(), 0.00001);
    }

    @Test
    public void checkPlayerParametersAngleTooLowPowerTooHigh() {
        gameStatus.getPlayer(0).getPlayerCannon().setCannonAngle(-Math.PI / 4);
        gameStatus.getPlayer(0).getPlayerCannon().setCannonPower(10000);
        gameLogic.checkPlayerParameters();
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

}
