package tykkipeli.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameStatusTest {

    GameStatus status;

    public GameStatusTest() {
    }

    @Before
    public void setUp() {
        status = new GameStatus();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getTurnAtBeginning() {
        int ret = status.getTurn();
        assertEquals(0, ret);
    }

    @Test
    public void setTurnPlayerOne() {
        status.setTurn(1);
        int ret = status.getTurn();
        assertEquals(1, ret);
    }

    @Test
    public void getScoreAtBeginning() {
        int[] ret = status.getPlayerScores();
        int[] cmp = {0, 0};
        assertArrayEquals(cmp, ret);
    }

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

}
