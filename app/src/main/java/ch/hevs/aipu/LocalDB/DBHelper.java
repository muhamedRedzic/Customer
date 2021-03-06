package ch.hevs.aipu.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Conference;
import ch.hevs.aipu.admin.entity.newsendpoint.model.News;
import ch.hevs.aipu.admin.entity.stakeholderendpoint.model.Key;
import ch.hevs.aipu.admin.entity.stakeholderendpoint.model.Stakeholder;

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

    public void addConference(Conference conference) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key> stakeholders = conference.getStakeholders();


        ContentValues values = new ContentValues();
        values.put(ConferenceContract.ConferenceEntry.KEY_ID, conference.getId().getId());
        values.put(ConferenceContract.ConferenceEntry.KEY_TITLE, conference.getTitle());
        values.put(ConferenceContract.ConferenceEntry.KEY_ROOM, conference.getRoom());
        values.put(ConferenceContract.ConferenceEntry.KEY_START, conference.getStart().toString());
        values.put(ConferenceContract.ConferenceEntry.KEY_END, conference.getEnd().toString());
        values.put(ConferenceContract.ConferenceEntry.KEY_WEBSITE, conference.getWebsite());
        //create string of keys
        String listIntoString = "";
        //Log.i("string", conferences.get(0).getId().toString());
        if(stakeholders!=null) {
            for (ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key k : stakeholders) {

                listIntoString = listIntoString + k.getId().toString() + ",";
            }
            values.put(ConferenceContract.ConferenceEntry.KEY_STAKEHOLDERS, listIntoString);
        }


        db.insert(ConferenceContract.ConferenceEntry.TABLE_CONFERENCE, null, values);

        db.close();



    }

    public void addStakeholder(Stakeholder stakeholder) {

        List<Key> conferences = stakeholder.getConferences();
        //Log.i("string", listIntoString);

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(StakeholderContract.StakeholdersEntry.KEY_ID, stakeholder.getId().getId());
        values.put(StakeholderContract.StakeholdersEntry.KEY_NAME,stakeholder.getName() );
        values.put(StakeholderContract.StakeholdersEntry.KEY_TYPE,stakeholder.getType() );
        values.put(StakeholderContract.StakeholdersEntry.KEY_EMAIL,stakeholder.getEmail());
        values.put(StakeholderContract.StakeholdersEntry.KEY_WEBSITE,stakeholder.getWebsite() );


        String listIntoString = "";
        //Log.i("XXXXXXXXXXXXXXXXXXXXX", conferences.get(0).getId().toString());
        if(conferences!=null) {
            for (Key k : conferences) {

                listIntoString = listIntoString + k.getId().toString() + ",";
            }
            values.put(StakeholderContract.StakeholdersEntry.KEY_CONFERENCES, listIntoString);
        }

        db.insert(StakeholderContract.StakeholdersEntry.TABLE_STAKEHOLDERS, null, values);

        db.close();



    }

    public List<String> getNews() {

        List<String> news = new ArrayList<String>() ;

        String querySelectAll = "SELECT * FROM " + NewsContract.NewsEntry.KEY_TITLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){
            for(int i=0; i< c.getCount();i++){
                news.add(c.getString(c.getColumnIndex(NewsContract.NewsEntry.KEY_TITLE)));
                c.move(1);
            }

        }

        return news;

    }



    public  void cleanNewsTable(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DELETE FROM " + NewsContract.NewsEntry.TABLE_NEWS);
        db.close();


    }

    public  void cleanConferenceTable(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DELETE FROM " + ConferenceContract.ConferenceEntry.TABLE_CONFERENCE);
        db.close();


    }

    public  void cleanStakeholderTable(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DELETE FROM " + StakeholderContract.StakeholdersEntry.TABLE_STAKEHOLDERS);
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

    public List<Conference> getListConference(){

        List<Conference> conferences = new ArrayList<Conference>() ;

        String querySelectAll = "SELECT * FROM " + ConferenceContract.ConferenceEntry.TABLE_CONFERENCE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){
            for(int i=0; i< c.getCount();i++){
                Conference conf = new Conference();
                Long l = c.getLong(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_ID));
                ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key k = new ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key();
                k.setId(l);
                conf.setId(k);
                conf.setTitle(c.getString(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_TITLE)));
                conf.setRoom(c.getString(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_ROOM)));
                conf.setStart(new DateTime(c.getString(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_START))));
                conf.setEnd(new DateTime(c.getString(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_END))));

                if (!ConferenceContract.ConferenceEntry.KEY_STAKEHOLDERS.equals("Stakeholders")){
                    List<ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key> keys = new ArrayList<>();
                    String[] stringKeys = ConferenceContract.ConferenceEntry.KEY_STAKEHOLDERS.split(",");
                    for (String stakeholderKey:stringKeys){
                        ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key key = new ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key();
                        key.setId(Long.parseLong(stakeholderKey));
                        keys.add(key);
                    }
                    conf.setStakeholders(keys);
                }

                conferences.add(conf);
                c.move(1);
            }

        }
        c.close();

        return conferences;

    }



    public List<Stakeholder> getListStakeholdersByType(String type){

        List<Stakeholder> stakeholders = new ArrayList<Stakeholder>() ;

        String querySelectAll = "SELECT * FROM " + StakeholderContract.StakeholdersEntry.TABLE_STAKEHOLDERS + " WHERE " + StakeholderContract.StakeholdersEntry.KEY_TYPE + " = '" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);
        if (c.moveToFirst()) {
            Log.i("cursor", "hello");
            for(int i = 0; i < c.getCount(); i++) {
                Stakeholder stakeholder = new Stakeholder();
                long l = c.getLong(c.getColumnIndex(ConferenceContract.ConferenceEntry.KEY_ID));
                Key key = new Key();
                key.setId(l);

                stakeholder.setId(key);
                stakeholder.setName(c.getString(c.getColumnIndex(StakeholderContract.StakeholdersEntry.KEY_NAME)));
                stakeholder.setType(c.getString(c.getColumnIndex(StakeholderContract.StakeholdersEntry.KEY_TYPE)));
                stakeholder.setEmail(c.getString(c.getColumnIndex(StakeholderContract.StakeholdersEntry.KEY_EMAIL)));
                stakeholder.setWebsite(c.getString(c.getColumnIndex(StakeholderContract.StakeholdersEntry.KEY_WEBSITE)));

                if(type.equals("speaker")){
                    String keyString = c.getString(c.getColumnIndex(StakeholderContract.StakeholdersEntry.KEY_CONFERENCES));

                    List<Key> keyList = new ArrayList<Key>();

                    String[] keyArray = keyString.split(",");

                    for (String s:keyArray ) {

                        Key k = new Key();
                        k.setId(Long.parseLong(s));
                        keyList.add(k);
                        Log.i("string", s);

                    }
                    Log.i("string", "end");

                    stakeholder.setConferences(keyList);
                }

                stakeholders.add(stakeholder);
                c.move(1);
            }

        }
        c.close();
        return stakeholders;

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