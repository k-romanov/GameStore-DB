package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAC extends DAC {

    public static String get_password(String username) throws SQLException {
        ResultSet result = getSet(String.format("SELECT password FROM Users WHERE username = '%s'", username));
        result.next();
        return result.getString(1);
    }
    public static boolean checkAdmin(String username) throws SQLException {
        ResultSet result = getSet(String.format("SELECT admin FROM Users WHERE username = '%s'", username));
        result.next();
        return result.getBoolean(1);
    }

    public static ArrayList<Integer> getUserIDs(String username) throws SQLException { //TODO:Add counter
        ResultSet result = getSet(String.format("SELECT user_id from Users where (LOCATE('%s', username) > 0) ORDER BY user_id LIMIT 0,5", username));
        ArrayList<Integer> retval = new ArrayList<>();
        while (result.next()){
            int id = result.getInt(1);
            retval.add(id);
        }
        return retval;
    }

    public static int getUserID(String username) throws SQLException {
        ResultSet result = getSet(String.format("SELECT user_id from Users WHERE username = '%s'" ,username));
        result.next();
        return result.getInt(1);
    }
    public static void addUser(String username, String password, String email) throws SQLException {
        if(isAdmin){
            DAC.exec(String.format("INSERT INTO gamestore.Users (username, balance, password, email, admin) VALUES('%s', 0, '%s', '%s', 0)", username, password, email));
        }
    }
    public static void removeUser(int id) throws SQLException {
        if(isAdmin){
            DAC.exec(String.format("""
                    DELETE FROM Users
                    WHERE user_id=%d;
                    """, id));
        }
    }

    public static String getFullUserInfo(int id) throws SQLException {
        ResultSet result = getSet(String.format("SELECT username, balance, email FROM Users WHERE user_id = %d", id));
        result.next();
        return String.format("Username:%s, Balance:%s, Email:%s", result.getString(1), result.getString(2), result.getString(3));
    }

    public static String getUserName(int id) throws SQLException {
        ResultSet result = getSet(String.format("SELECT username FROM Users WHERE user_id = %d", id));
        result.next();
        return String.format("Username:%s", result.getString(1));
    }

    public static int getBalance(int id) throws SQLException {
        ResultSet result = getSet(String.format("SELECT balance FROM Users WHERE user_id = %d", id));
        result.next();
        return result.getInt(1);
    }
    public static void setBalance(int id, int newBalance) throws SQLException {
        DAC.exec(String.format("""
                UPDATE Users
                SET balance=%d
                WHERE user_id=%d;
                """,newBalance, id));
    }
}
