package jdbc.dao.executor;

import jdbc.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    public Executor() {  }

    public void execUpdate(String update) throws SQLException {
        try (Connection connection = Main.getMysqlConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(update);
        }
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler)
            throws SQLException {
        try (
        Connection connection = Main.getMysqlConnection();
        Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            T resultObj =  handler.handle(result);
            result.close();
            return resultObj;
        }
    }
}
