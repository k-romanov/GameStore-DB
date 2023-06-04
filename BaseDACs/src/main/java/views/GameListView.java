package views;

import controllers.GameDAC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GameListView extends View{
    private String _queryString = null;
    public void addQuery(String query){
        _queryString = query;
    }
    public void view() throws SQLException {
        int choice = 0;
        int lb = 0;
        int mul = 10;
        while(choice != 5){
            ResultSet games;
            if(_queryString != null) {
                games = GameDAC.getGameListByQuery(_queryString, lb * mul, (lb + 1) * mul);
            }
            else {
                games = GameDAC.getGameList(lb * mul, (lb + 1) * mul);
            }
            games.beforeFirst();
            int count = 1;
            while(games.next()){
                System.out.println(String.format("%d: %s", lb + count, games.getString("game_title")));
                count += 1;
            }
            System.out.println("""
                    1. Next Page
                    2. Prev Page
                    3. View Game
                    4. Edit Game
                    5. Back""");
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
            switch(choice) {
                case(1): lb += 1;
                case(2):{if (lb >= 1) {lb -= 1;}}
                case(3):{
                    int gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    while((gameChoice < 0) || (gameChoice >= mul)){
                        System.out.println("Invalid game number!");
                        gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    }
                    games.absolute(gameChoice);
                    GameView.view(games.getInt("game_id"));
                }
                case(4):{
                    if(!isAdmin){
                        System.out.println("Not an admin!");
                    }
                    else{
                        int gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                        while((gameChoice < 0) || (gameChoice >= mul)){
                            System.out.println("Invalid game number!");
                            gameChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                        }
                        games.absolute(gameChoice);
                        GameDAC.removeGame(games.getString("game_id"));
                    }
                }
            }
        }
    }
}
