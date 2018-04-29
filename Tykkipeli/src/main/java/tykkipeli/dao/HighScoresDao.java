package tykkipeli.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoresDao {

    public String databaseAddress;

    public HighScoresDao() {
        this.databaseAddress = "jdbc:sqlite:res/highscores.db";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void createNewDatabase() {
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            for (String s : createTables()) {
                st.executeUpdate(s);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> createTables() {
        List<String> list = new ArrayList<>();
        list.add("CREATE TABLE scoresEasy (id nvarchar(255), score integer);");
        list.add("CREATE TABLE scoresNormal (id nvarchar(255), score integer);");
        list.add("CREATE TABLE scoresHard (id nvarchar(255), score integer);");
        return list;
    }

    public String getTableName(int difficulty) {
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

    public void addScore(String playerName, int score, int difficulty) {
        String table = getTableName(difficulty);
        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO " + table + " (id, score) VALUES ('" + playerName + "', " + score + ");");
            st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<String> getTopThree(int difficulty) {
        List<String> list = new ArrayList<>();
        String table = getTableName(difficulty);
        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM " + table + " ORDER BY score DESC LIMIT 3;");
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
            System.out.println(ex);
        }
        return list;
    }

}
