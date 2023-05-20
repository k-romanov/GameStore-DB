package controllers;

import lombok.Setter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAC {
    @Setter
    private static Connection connection;
    @Setter
    protected static boolean isAdmin = false;
    public static ResultSet getSet(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    public static void exec(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }
}
