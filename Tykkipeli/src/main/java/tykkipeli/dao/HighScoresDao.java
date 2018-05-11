package tykkipeli.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for using database.
 *
 * @author oskari
 */
public class HighScoresDao {

    public String databaseAddress;

    /**
     * Default constructor for class HighScoresDao. Uses database in directory
     * "res/highscores.db".
     */
    public HighScoresDao() {
        this("jdbc:sqlite:res/highscores.db");
    }

    /**
     * Constructor for class HighScoresDao. Used for using custom database
     * address.
     *
     * @param databaseAddress Address as string
     */
    public HighScoresDao(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Connects to database.
     *
     * @return Connection to database
     * @throws SQLException if connection failed.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Attempts to create new database. If database already exists, method
     * prints message and continues.
     */
    public void createNewDatabase() {
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            for (String s : createTables()) {
                st.executeUpdate(s);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            if (ex.getMessage().equals("table scoresEasy already exists")) {
                System.out.println("High score database already exists. "
                        + "Using existing database.");
            } else if (ex.getMessage().contains("path to 'res/highscores.db'")) {
                System.out.println("Could not locate path 'res/'. "
                        + "Make sure that folder 'res' exists before restarting.\n"
                        + "Closing application.");
                System.exit(1);
            }
        }
    }

    /**
     * Method returns CREATE TABLE commands used during creation of new
     * database.
     *
     * @return List of CREATE TABLE commands
     */
    private List<String> createTables() {
        List<String> list = new ArrayList<>();
        list.add("CREATE TABLE scoresEasy (id nvarchar(255), score integer);");
        list.add("CREATE TABLE scoresNormal (id nvarchar(255), score integer);");
        list.add("CREATE TABLE scoresHard (id nvarchar(255), score integer);");
        return list;
    }

    /**
     * Gets the name of database table for selected difficulty.
     *
     * @param difficulty Selected difficulty
     * @return Table name as string
     */
    private String getTableName(int difficulty) {
        String table;
        if (difficulty == 3) {
            table = "scoresHard";
        } else if (difficulty == 2) {
            table = "scoresNormal";
        } else {
            table = "scoresEasy";
        }
        return table;
    }

    /**
     * Method adds new line into database table of selected difficulty.
     *
     * @param playerName Name of the player who's score is to be added
     * @param score Score to be added
     * @param difficulty Selected difficulty (1=easy, 2=normal, 3=hard)
     */
    public void addScore(String playerName, int score, int difficulty) {
        String table = getTableName(difficulty);
        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO " + table + " (id, score) VALUES ('" + playerName + "', " + score + ");");
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error while saving score to database. Score might not been saved.");
        }
    }

    /**
     * Method return three best scores of selected difficulty.
     *
     * @param difficulty Selected difficulty
     * @return String list of best scores
     */
    public List<String> getTopThree(int difficulty) {
        List<String> list = new ArrayList<>();
        String table = getTableName(difficulty);
        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM "
                    + table + " ORDER BY score DESC LIMIT 3;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("id");
                int score = rs.getInt("score");
                list.add(name + ": " + score + " points");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error while reading scores from database.");
        }
        return list;
    }

}
