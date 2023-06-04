package views;

import controllers.DLCDAC;
import controllers.GameDAC;
import controllers.ReviewDAC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DLCListView extends View{
    public static void view(int gameID) throws SQLException {
        int choice = 0;
        int lb = 0;
        int mul = 10;
        while(choice != 5){
            ResultSet dlcs = DLCDAC.getDLCs(gameID, lb * mul, (lb + 1) * mul);
            dlcs.beforeFirst();
            int count = 1;
            while(dlcs.next()){
                System.out.println(lb + count + dlcs.getString("name"));
                count += 1;
            }
            System.out.println("""
                    1. Next Page
                    2. Prev Page
                    3. View DLC
                    4. Edit DLC
                    5. Back""");
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
            switch (choice) {
                case (1) -> lb += 1;
                case (2) -> {
                    if (lb >= 1) {
                        lb -= 1;
                    }
                }
                case (3) -> {
                    int gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    while ((gameChoice < 0) || (gameChoice >= mul)) {
                        System.out.println("Invalid DLC number!");
                        gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    }
                    dlcs.absolute(gameChoice);
                    DLCView.view(dlcs.getInt("dlc_id"));
                }
                case (4) -> {
                    if (!isAdmin) {
                        System.out.println("Not an admin!");
                    } else {
                        int gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                        while ((gameChoice < 0) || (gameChoice >= mul)) {
                            System.out.println("Invalid DLC number!");
                            gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                        }
                        dlcs.absolute(gameChoice);
                        DLCDAC.removeDLC(dlcs.getInt("dlc_id"));
                    }
                }
            }
        }
    }
}
