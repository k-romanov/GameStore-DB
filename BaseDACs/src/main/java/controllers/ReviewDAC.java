package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAC {
    public static ArrayList<String> getReviews(int gameId) throws SQLException {
        ResultSet result = DAC.getSet(String.format("SELECT text_review FROM Reviews WHERE game_id = %d", gameId));
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
}
