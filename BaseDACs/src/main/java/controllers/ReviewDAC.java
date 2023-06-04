package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAC {
    public static ResultSet getReviews(int gameID, int start, int end) throws SQLException {
        return DAC.getSet(String.format("SELECT review_id, text_review, upvote_countdown, downvote_count, funny_count " +
                        "FROM Reviews JOIN Games ON Games.game_id = Reviews.game_id WHERE user_id = %d LIMIT %d, %d", gameID, start, end));
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
