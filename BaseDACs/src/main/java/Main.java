import controllers.DAC;
import controllers.ReviewDAC;
import controllers.UsersDAC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", "homuhomu");
            System.out.print("Enter username:");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.print("Enter password:");
            String password = scanner.nextLine();
            DAC.setConnection(connection);
            if(Objects.equals(password, UsersDAC.get_password(username))){
                System.out.println("Welcome, " + username);
            } else{
                System.out.println("Wrong username or password!");
            }
            if(UsersDAC.checkAdmin(username)){
                System.out.println("You are an admin!");
                DAC.setAdmin(true);
            }
            System.out.print("Search for a user id:");
            String search = scanner.nextLine();
            for(int i: UsersDAC.getUserIDs(search)){
                System.out.println(UsersDAC.getUserName(i));
            }
            System.out.println("Get game reviews");
            int id = Integer.parseInt(scanner.nextLine());
            for (String i: ReviewDAC.getReviews(id)){
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
