package ch.hevs.aipu.LocalDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matthieu on 07.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AIPU.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsContract.NewsEntry.CREATE_TABLE_NEWS);
        db.execSQL(StakeholderContract.StakeholdersEntry.CREATE_TABLE_STAKEHOLDERS);
        db.execSQL(ConferenceContract.ConferenceEntry.CREATE_TABLE_CONFERENCE);
        db.execSQL(JoinContract.JoinEntry.CREATE_TABLE_JOIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}