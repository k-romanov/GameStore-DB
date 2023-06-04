package views;

import controllers.UsersDAC;

import java.sql.SQLException;
import java.util.Scanner;

public class SearchView extends View {
    public static void show() throws SQLException {
        while (true){
            System.out.println("""
                1. Find by name
                2. Browse games
                3. Find username
                4. Back""");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());
            if(choice < 1 || choice > 4) {
                System.out.println("Wrong input! Try again!");
            }
            else{
                switch (choice){
                    case 1:
                        System.out.println("Enter the game name:");
                        String gamename = new Scanner(System.in).nextLine();
                        //TODO: GameDAC
                        break;
                    case 2:
                        // GamesView.show();
                        break;
                    case 3:
                        System.out.println("Enter the username");
                        String username = new Scanner(System.in).nextLine();
                        int userID = 0;
                        try {
                            userID = UsersDAC.getUserID(username);
                        } catch (SQLException e){
                            System.out.println("User not found!");
                            break;
                        }

                        System.out.println(UsersDAC.getFullUserInfo(userID));
                        break;
                    case 4:
                        return;
                }
            }
        }
    }
}
