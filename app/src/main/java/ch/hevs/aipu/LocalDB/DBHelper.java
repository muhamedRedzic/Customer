package ch.hevs.aipu.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.admin.entity.newsendpoint.model.News;

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


    public void addNews(Long id, String title, String text, String date) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(NewsContract.NewsEntry.KEY_ID, id);
        values.put(NewsContract.NewsEntry.KEY_TITLE, title);
        values.put(NewsContract.NewsEntry.KEY_TEXT, text);
        values.put(NewsContract.NewsEntry.KEY_PUBLICATION, date);

        db.insert(NewsContract.NewsEntry.TABLE_NEWS, null, values);

        db.close();



    }

    public List<String> getNews() {

        List<String> manufacturers = new ArrayList<String>() ;

        String querySelectAll = "SELECT * FROM " + NewsContract.NewsEntry.KEY_TITLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){
            for(int i=0; i< c.getCount();i++){
                manufacturers.add(c.getString(c.getColumnIndex(NewsContract.NewsEntry.KEY_TITLE)));
                c.move(1);
            }

        }

        return manufacturers;

    }

    public  void dropTableNews(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NEWS);
        db.close();


    }

    public List<News> getListNews(){

        List<News> news = new ArrayList<News>() ;

        String querySelectAll = "SELECT * FROM " + NewsContract.NewsEntry.TABLE_NEWS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){
            for(int i=0; i< c.getCount();i++){
                News n = new News();
                n.setId(c.getLong(c.getColumnIndex(NewsContract.NewsEntry.KEY_ID)));
                n.setTitle(c.getString(c.getColumnIndex(NewsContract.NewsEntry.KEY_TITLE)));
                n.setText(c.getString(c.getColumnIndex(NewsContract.NewsEntry.KEY_TEXT)));
                n.setPublished(new DateTime(c.getString(c.getColumnIndex(NewsContract.NewsEntry.KEY_PUBLICATION))));
                news.add(n);
                c.move(1);
            }

        }

        return news;

    }

    public boolean checkIfNewsExist (long id){

        List<News> news = getListNews();

        for (News n : news) {

           // Log.i("check", "Name: " + n.getId());
            if(id==n.getId().longValue())
                return false;

        }
        return true;
    }





}