package tykkipeli.dao;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oskari
 */
public class HighScoresDaoTest {

    private HighScoresDao hsDao;

    public HighScoresDaoTest() {
    }

    @Before
    public void setUp() {
        hsDao = new HighScoresDao("jdbc:sqlite:res/testdatabase.db");
    }

    @After
    public void tearDown() {
        Path path = FileSystems.getDefault().getPath("res", "testdatabase.db");
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.out.println("Error while deleting testdatabase:");
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testGetConnectionReturnsNotNull() throws Exception {
        Connection ret = hsDao.getConnection();
        assertNotNull(ret);
    }

    @Test
    public void testGetConnectionReturnsConnection() throws Exception {
        assertThat(hsDao.getConnection(), is(Connection.class));
    }

    @Test
    public void testAddScore() {
        hsDao.createNewDatabase();
        hsDao.addScore("a", 1, 1);
        int score = 0;
        try {
            Connection conn = hsDao.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM scoresEasy ORDER BY score DESC LIMIT 3;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                score = rs.getInt("score");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            fail("Error while reading scores from testdatabase. testAddScore()");
        }
        assertEquals(1, score);
    }

    @Test
    public void getTopThreeReturnsNotNull() {
        assertNotNull(hsDao.getTopThree(1));
    }

    @Test
    public void getTopThreeReturnsTopThree() {
        hsDao.createNewDatabase();
        hsDao.addScore("a", 10, 1);
        hsDao.addScore("b", 11, 1);
        hsDao.addScore("c", 12, 1);
        hsDao.addScore("d", 13, 1);
        hsDao.addScore("e", 14, 1);
        List<String> topThree = hsDao.getTopThree(1);
        assertTrue(topThree.get(0).equals("e: 14 points"));
        assertTrue(topThree.get(1).equals("d: 13 points"));
        assertTrue(topThree.get(2).equals("c: 12 points"));
    }

}
