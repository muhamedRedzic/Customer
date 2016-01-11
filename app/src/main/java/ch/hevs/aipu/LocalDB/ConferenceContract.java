package ch.hevs.aipu.LocalDB;

import android.provider.BaseColumns;

/**
 * Created by Matthieu on 17.12.2015.
 */
public class ConferenceContract {

    private ConferenceContract(){

    }

    public static abstract class ConferenceEntry implements BaseColumns {
        //Table name
        public static final String TABLE_CONFERENCE = "Conference";

        //Patient Column names
        public static final String KEY_ID = "id";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_ROOM = "Room";
        public static final String KEY_START = "StartTime";
        public static final String KEY_END = "EndTime";



        // Database creation sql statement
        public static final String CREATE_TABLE_CONFERENCE = "CREATE TABLE "
                + TABLE_CONFERENCE
                + "("
                + KEY_ID + " INTEGER NOT NULL, "
                + KEY_TITLE + " TEXT NOT NULL, "
                + KEY_ROOM + " TEXT NOT NULL, "
                + KEY_START + " TEXT NOT NULL, "
                + KEY_END + " TEXT NOT NULL);";
    }

}
