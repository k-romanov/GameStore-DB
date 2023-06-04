package views;

import controllers.ReviewDAC;

import java.sql.SQLException;
import java.util.Scanner;

public class ReviewsView extends View{
    public static void show() {
        while (true){
            System.out.println("""
                    1. Upvote review
                    2. Downvote review
                    3. Set review as funny
                    4. Edit review
                    5. Delete review
                    6. Back""");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());
            if(choice < 1 || choice > 6) {
                System.out.println("Wrong input! Try again!");
            } else {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter review_id:");
                        int reviewID = Integer.parseInt(new Scanner(System.in).nextLine());
                        try {
                            ReviewDAC.setUpvoteCounter(reviewID);
                        } catch (SQLException e) {
                            System.out.println("This review does not exist!");
                            break;
                        }
                        System.out.println("Upvote counter has been increaced by one!");
                    }
                    case 2 -> {
                        System.out.print("Enter review_id:");
                        int reviewID = Integer.parseInt(new Scanner(System.in).nextLine());
                        try {
                            ReviewDAC.setDownvoteCounter(reviewID);
                        } catch (SQLException e) {
                            System.out.println("This review does not exist!");
                            break;
                        }
                        System.out.println("Downvote counter has been increaced by one!");
                    }
                    case 3 -> {
                        System.out.print("Enter review_id:");
                        int reviewID = Integer.parseInt(new Scanner(System.in).nextLine());
                        try {
                            ReviewDAC.setFunnyCounter(reviewID);
                        } catch (SQLException e) {
                            System.out.println("This review does not exist!");
                            break;
                        }
                        System.out.println("Funny counter has been increaced by one!");
                    }
                    case 4 -> {
                        System.out.println("Enter review_id:");
                        int reviewID = Integer.parseInt(new Scanner(System.in).nextLine());
                        try{
                            int userID = ReviewDAC.getUserIDByReview(reviewID);
                            if(userID == id || isAdmin){
                                System.out.print("Enter new review text: ");
                                String reviewText = new Scanner(System.in).nextLine();
                                ReviewDAC.updateReview(reviewID, reviewText);
                            }
                            else {
                                System.out.println("You are not allowed to edit this review!");
                            }
                        } catch (SQLException e){
                            System.out.println("Review not found!");
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter review_id:");
                        int reviewID = Integer.parseInt(new Scanner(System.in).nextLine());
                        try{
                            int userID = ReviewDAC.getUserIDByReview(reviewID);
                            if(userID == id || isAdmin){
                                ReviewDAC.removeReview(reviewID);
                            }
                            else {
                                System.out.println("You are not allowed to remove this review!");
                            }
                        } catch (SQLException e){
                            System.out.println("Review not found!");
                        }
                    }
                    case 6 ->{
                        return;
                    }
                }
            }
        }
    }
}
