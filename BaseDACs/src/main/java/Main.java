import controllers.DAC;
import controllers.ReviewDAC;
import controllers.UsersDAC;
import views.MainView;
import views.View;

import java.sql.Connection;
import java.sql.DriverManager;
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
                return;
            }
            View.setId(UsersDAC.getUserID(username));
            if(UsersDAC.checkAdmin(username)){
                System.out.println("You are an admin!");
                DAC.setAdmin(true);
                View.setAdmin(true);
            }
            MainView.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
