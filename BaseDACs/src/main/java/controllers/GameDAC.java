package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAC extends DAC {
    public static ResultSet getGameInfo(int gameID) throws SQLException {
        return DAC.getSet(String.format("SELECT game_title, username, description, price, on_sale, sale_amount FROM Games JOIN Users ON Users.user_id = Games.publisher_id  WHERE publisher_id = user_id AND game_id = %d", gameID));
    }
    public static ResultSet getGameList(int start, int end) throws SQLException {
        return DAC.getSet(String.format("SELECT game_id, game_title from Games ORDER BY game_id LIMIT %d,%d", start, end));
    }

    public static ResultSet getGameListByQuery(String name, int start, int end) throws SQLException {
        return DAC.getSet(String.format("SELECT game_id, game_title from Games where (LOCATE(%s, game_title) > 0) ORDER BY game_id LIMIT %d,%d", name, start, end));


    }
    public static void publishGame(String name, String description, int price) throws SQLException {
        DAC.exec(String.format("INSERT INTO gamestore.Games (game_title, publisher_id, on_sale, price, sale_amount, description) " +
                "VALUES(%s, %d, 0, %d, 0, %s)", name, loginID, price, description));
    }
    public static void setSaleStatus(boolean onSale, int priceReduction, int gameID){

    }
    public static void removeGame(int gameID) throws SQLException {
        if(isAdmin){
            DAC.exec(String.format("""
                    DELETE FROM Games
                    WHERE game_id=%d;
                    """, gameID));
        }
    }
}
