package tykkipeli.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author oskari
 */
public class GameAiTest {

    private GameAi ai;
    private GameStatus status;

    public GameAiTest() {
    }

    @Before
    public void setUp() {
        this.status = new GameStatus();
        this.ai = new GameAi(status);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDefaultDifficulty() {
        int cmp = 1;
        int ret = ai.getDifficulty();
        assertEquals(cmp, ret);
    }

    @Test
    public void setDifficultyToTwo() {
        ai.setDifficulty(2);
        int cmp = 2;
        int ret = ai.getDifficulty();
        assertEquals(cmp, ret);
    }

    @Test
    public void playingWithEasyChangesAim() {
        ai.setDifficulty(1);
        ai.play(1);
        double cmp = 20;
        double ret = status.getPlayer(1).getPlayerCannon().getCannonPower();
        assertThat(cmp, is(not(ret)));
    }

    @Test
    public void playingWithNormalChangesAim() {
        ai.setDifficulty(2);
        status.getPlayer(0).getPlayerCannon().setCannonPower(1);
        ai.play(1);
        double cmp = 20;
        double ret = status.getPlayer(1).getPlayerCannon().getCannonPower();
        assertThat(cmp, is(not(ret)));
    }

    @Test
    public void playingWithHardChangesAim() {
        ai.setDifficulty(3);
        ai.play(1);
        double cmp = 20;
        double ret = status.getPlayer(1).getPlayerCannon().getCannonPower();
        assertThat(cmp, is(not(ret)));
    }

    @Test
    public void playingWithNonexistingDifficulty() {
        ai.setDifficulty(999);
        ai.play(1);
        double cmp = 20;
        double ret = status.getPlayer(1).getPlayerCannon().getCannonPower();
        assertThat(cmp, is(not(ret)));
    }

}
