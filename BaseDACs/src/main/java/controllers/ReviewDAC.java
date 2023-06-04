package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAC {
    public static ArrayList<String> getReviews(int gameId) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT review_id, text_review FROM Reviews WHERE game_id = %d", gameId));
        ArrayList<String> retval = new ArrayList<>();
        while (result.next()){
            retval.add(result.getInt(1) + ". " + result.getString(2));
        }
        return retval;
    }

    public static void updateReview(int reviewID, String review) throws SQLException {
        DAC.exec(String.format("""
                UPDATE Reviews
                SET text_review= %s
                WHERE review_id=%d""", review, reviewID));
    }
    public static int getUserIDByReview(int reviewID) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT user_id FROM Reviews WHERE review_id = %d", reviewID));
        result.next();
        return result.getInt(1);
    }
    public static ArrayList<String> getReviewsOfUser(int userID) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT text_review FROM Reviews WHERE user_id = %d", userID));
        ArrayList<String> retval = new ArrayList<>();
        while (result.next()){
            retval.add(result.getString(1));
        }
        return retval;
    }
    public static void setUpvoteCounter(int id) throws SQLException {
        DAC.exec(String.format("""
                UPDATE Reviews
                SET upvote_countdown= upvote_countdown + 1
                WHERE review_id=%d""", id));
    }

    public static void setDownvoteCounter(int id) throws SQLException {
        DAC.exec(String.format("""
                UPDATE Reviews
                SET downvote_countdown= downvote_countdown + 1
                WHERE review_id=%d""", id));
    }

    public static void setFunnyCounter(int id) throws SQLException {
        DAC.exec(String.format("""
                UPDATE Reviews
                SET funny_countdown= funny_countdown + 1
                WHERE review_id=%d""", id));
    }

    public static void removeReview(int reviewID) throws SQLException {
        DAC.exec(String.format("""
                    DELETE FROM Reviews
                    WHERE review_id=%d;
                    """, reviewID));
    }
}
