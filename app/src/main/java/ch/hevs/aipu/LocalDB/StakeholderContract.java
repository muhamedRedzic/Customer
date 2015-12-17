package ch.hevs.aipu.LocalDB;

import android.provider.BaseColumns;

/**
 * Created by Matthieu on 07.12.2015.
 */
public class StakeholderContract {

    private StakeholderContract() {

    }

    public static abstract class StakeholdersEntry implements BaseColumns {
        //Table name
        public static final String TABLE_STAKEHOLDERS = "Stakeholders";

        //Patient Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "Name";
        public static final String KEY_TYPE = "Type";
        public static final String KEY_EMAIL = "Email";
        public static final String KEY_WEBSITE = "Website";


        // Database creation sql statement
        public static final String CREATE_TABLE_STAKEHOLDERS = "CREATE TABLE "
                + TABLE_STAKEHOLDERS
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT NOT NULL, "
                + KEY_TYPE + " TEXT NOT NULL, "
                + KEY_EMAIL + " TEXT NOT NULL, "
                + KEY_WEBSITE + " TEXT NOT NULL);";
    }
}
