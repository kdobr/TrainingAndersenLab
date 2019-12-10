package jdbc.dao.executor;

import jdbc.Main;

import java.sql.*;

public class Executor {

    public Executor() {
    }

    public void execUpdate(String update) throws SQLException {
        try (Connection connection = Main.getMysqlConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(update);
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        ResultSet result = null;
        try (Connection connection = Main.getMysqlConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            result = stmt.getResultSet();
            return handler.handle(result);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public <T> T execQueryPrep(PreparedStatement stmt, ResultHandler<T> handler)
            throws SQLException {
        ResultSet result = null;
        try {
            stmt.execute();
            result = stmt.getResultSet();
            return handler.handle(result);
        } finally {
            if (result != null) {
                result.close();
            }
            stmt.close();
        }
    }
}

