import controllers.UsersDAC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", "homuhomu");
            System.out.print("Enter username");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.print("Enter password:");
            String password = scanner.nextLine();
            UsersDAC dac = new UsersDAC(connection, false);
            if(Objects.equals(password, dac.get_password(username))){
                System.out.println("Welcome, " + username);
            } else{
                System.out.println("Wrong username or password!");
            }
            if(dac.checkAdmin(username)){
                System.out.println("You are an admin!");
                dac.setAdmin(true);
            }
            System.out.print("Search for a user:");
            String search = scanner.nextLine();
            //TODO: User search
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
