package ch.hevs.aipu.LocalDB;

import android.provider.BaseColumns;

/**
 * Created by Matthieu on 17.12.2015.
 */
public class JoinContract {

    private JoinContract(){

    }

    public static abstract class JoinEntry implements BaseColumns {
        //Table name
        public static final String TABLE_JOIN = "JoinTable";

        //Patient Column names
        public static final String KEY_ID = "id";
        public static final String KEY_IDCONFERENCE = "idConference";
        public static final String KEY_IDSTAKEHOLDER = "idStakeholder";


        // Database creation sql statement
        public static final String CREATE_TABLE_JOIN = "CREATE TABLE "
                + TABLE_JOIN
                + "("
                + KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_IDCONFERENCE + "  INTEGER,"
                + KEY_IDSTAKEHOLDER + "  INTEGER"
                + ");";
    }

}
