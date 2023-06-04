package views;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView extends View{
    public static void show() throws SQLException {
        while (true) {
            System.out.println("""
                    1. Search
                    2. View Profile""");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());
            if(choice < 1 || choice > 2) {
                System.out.println("Wrong input! Try again!");
            } else {
                switch (choice){
                    case 1:
                        SearchView.show();
                        break;
                    case 2:
                        ProfileView.show();
                        break;
                }
            }
        }
    }
}
