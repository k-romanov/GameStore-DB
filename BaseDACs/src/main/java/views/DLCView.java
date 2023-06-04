package views;

import controllers.DLCDAC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DLCView {
    public static void view(int dlcID) throws SQLException {
        ResultSet dlc = DLCDAC.getDLCInfo(dlcID);
        System.out.println(dlc.getString("name"));
        System.out.println(dlc.getString("description"));
        System.out.println("Press a key to go back");
        Integer.parseInt(new Scanner(System.in).nextLine());
    }
}
