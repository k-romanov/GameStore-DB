package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import static controllers.DAC.isAdmin;

public class DLCDAC {
    public static ResultSet getDLCs(int dlcID, int start, int end) throws SQLException {
        return DAC.getSet(String.format("SELECT dlc_id, name FROM DLC WHERE game_id = %d ORDER BY dlc_id LIMIT %d,%d", dlcID, start, end));
    }
    public static ResultSet getDLCInfo(int dlcID) throws SQLException {
        return DAC.getSet(String.format("SELECT name, description FROM DLC WHERE dlc_id = %d", dlcID));
    }
    public static void removeDLC(int dlcID) throws SQLException {
        if(isAdmin){
            DAC.exec(String.format("""
                    DELETE FROM DLC
                    WHERE dlc_id=%d;
                    """, dlcID));
        }
    }
}
