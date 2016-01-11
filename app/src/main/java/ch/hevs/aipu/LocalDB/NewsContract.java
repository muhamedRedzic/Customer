package ch.hevs.aipu.LocalDB;

import android.provider.BaseColumns;

/**
 * Created by Matthieu on 07.12.2015.
 */
public class NewsContract {

    public NewsContract(){

    }

    public static abstract class NewsEntry implements BaseColumns {
        //Table name
        public static final String TABLE_NEWS = "NewsActivity";

        //Patient Column names
        public static final String KEY_ID = "id";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_TEXT = "Text";
        public static final String KEY_PUBLICATION = "Publication";



        // Database creation sql statement
        public static final String CREATE_TABLE_NEWS = "CREATE TABLE "
                + TABLE_NEWS
                + "("
                + KEY_ID + " BIGINT PRIMARY KEY , "
                + KEY_TITLE + " TEXT NOT NULL, "
                + KEY_TEXT + " TEXT NOT NULL, "
                + KEY_PUBLICATION + " TEXT NOT NULL);";
    }


}
