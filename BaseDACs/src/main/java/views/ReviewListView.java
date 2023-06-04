package views;

import controllers.DLCDAC;
import controllers.ReviewDAC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static views.View.isAdmin;

public class ReviewListView {
    public static void view(int gameID) throws SQLException {
        int choice = 0;
        int lb = 0;
        int mul = 10;
        while(choice != 5){
            ResultSet reviews = ReviewDAC.getReviews(gameID, lb * mul, (lb + 1) * mul);
            reviews.beforeFirst();
            int count = 1;
            while(reviews.next()){
                System.out.println(lb + count + reviews.getString("text_review"));
                System.out.println("Up: " + reviews.getInt("upvote_countdown") +
                                   " Down: " + reviews.getInt("downvote_countdown") +
                                   " Funny: " + reviews.getInt("funny_countdown"));
                count += 1;
            }
            System.out.println("""
                    1. Next Page
                    2. Prev Page
                    3. View Review
                    4. Edit Review
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
                    int reviewChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    while ((reviewChoice < 0) || (reviewChoice >= mul)) {
                        System.out.println("Invalid DLC number!");
                        reviewChoice = Integer.parseInt(new Scanner(System.in).nextLine());
                    }
                    reviews.absolute(reviewChoice);
                    ReviewView.view(reviews.getInt("dlc_id"));
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
                        reviews.absolute(gameChoice);
                        DLCDAC.removeDLC(reviews.getInt("dlc_id"));
                    }
                }
            }
        }
    }
}
