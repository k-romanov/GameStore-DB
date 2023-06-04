package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static controllers.DAC.loginID;

public class ReviewDAC {
    public static ArrayList<String> getReviews(int gameId) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT text_review FROM Reviews WHERE game_id = %d", gameId));
        ArrayList<String> retval = new ArrayList<>();
        while (result.next()) {
            retval.add(result.getString(1));
        }
        return retval;
    }

    public static ArrayList<String> getReviewsOfUser(int userID) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT text_review FROM Reviews WHERE user_id = %d", userID));
        ArrayList<String> retval = new ArrayList<>();
        while (result.next()){
            retval.add(result.getString(1));
        }
        return retval;
    }

    public static void addReview(int gameID, int text, int score) throws SQLException {
        DAC.exec(String.format("INSERT INTO gamestore.Reviews (score, game_id, user_id, text_review, upvote_countdown, downvote_countdown, funny_countdown) " +
                "VALUES(%d, %d, %d, %s, 0, 0, 0)", score, gameID, loginID, text));
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
}
