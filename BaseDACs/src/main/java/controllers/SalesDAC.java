package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesDAC extends DAC{
    public static ResultSet getGamesBought(int userID) throws SQLException {
        return DAC.getSet(String.format("SELECT game_title, game_id FROM Sales JOIN Games ON Sales.game_id = Games.game_id WHERE buyer_id = %d", userID));
    }
    public static int getSaleCount(int gameID) throws SQLException {
        return DAC.getSet(String.format("SELECT COUNT(*) FROM Sales WHERE game_id = %d", gameID)).getInt(1);
    }

    public static void setSale(int buyerID, int userID, int gameID, int price) throws SQLException {
        DAC.exec(String.format("INSERT INTO gamestore.Sales (buyer_id, reciever_id, game_id, price) VALUES(%d, %d, %d, %d)", buyerID, userID, gameID, price));
    }
}
