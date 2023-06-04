package views;

import controllers.GameDAC;
import controllers.ReviewDAC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfileView extends View{
    public static void show(){
        while (true){
            System.out.println("""
                    1. View reviews
                    2. View games
                    3. Back""");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());
            if(choice < 1 || choice > 3) {
                System.out.println("Wrong input! Try again!");
            } else{
                switch (choice){
                    case 1:
                        ArrayList<String> reviews;
                        try {
                            reviews = ReviewDAC.getReviewsOfUser(id);
                        } catch (SQLException e) {
                            System.out.println("Reviews not found!");
                            break;
                        }
                        int counter = 1;
                        for (String review: reviews){
                            System.out.println(counter + ". " + review);
                        }
                        break;
                    case 2:
                        try {
                            GameListView list = new GameListView();
                            list.addUser(id);
                            list.view();
                        }
                        catch(SQLException e){
                            System.out.println("Oops!");
                        }
                        break;
                    case 3:
                        return;
                }
            }
        }
    }
}
