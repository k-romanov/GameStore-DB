package views;

import controllers.GameDAC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GameView extends View{
    public static void view(int gameID) throws SQLException {
        ResultSet game = GameDAC.getGameInfo(gameID);
        System.out.println(game.getString("game_title"));
        int choice = 0;

        while(choice != 6){
            System.out.println("""
                1. View description
                2. View reviews
                3. View tags
                4. View DLCs
                5. Edit
                6. Back
                """);
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
            switch(choice){
                case(1): System.out.println(game.getString("description"));
                case(2): ReviewsView.view(gameID);
                case(3): System.out.println(TagsDAC.getTags(gameID));
                case(4): DLCsView.view(gameID);
                case(5): System.out.println("TODO");
            }
        }
    }
}
