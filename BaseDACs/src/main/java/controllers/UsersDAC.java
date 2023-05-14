package controllers;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
public class UsersDAC {
    private Connection connection;

    @Setter
    private boolean isAdmin = false;

    public String get_password(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String query = String.format("SELECT password FROM Users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(query);
        result.next();
        return result.getString(1);
    }
    public boolean checkAdmin(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String query = String.format("SELECT admin FROM Users WHERE username = '%s'", username);
        ResultSet result = statement.executeQuery(query);
        result.next();
        return result.getBoolean(1);
    }
}
