package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsDAC {
    public static String getTags(int gameID) throws SQLException {
        ResultSet tags = DAC.getSet(String.format("SELECT tag_name FROM Tags JOIN GameTags ON Tags.tag_id = GameTags.tag_id WHERE game_id = %d", gameID));
        StringBuilder ret = new StringBuilder();
        tags.beforeFirst();
        while(tags.next()) {
            ret.append(tags.getString("tag_name"));
        }
        return ret.toString();
    }
}
