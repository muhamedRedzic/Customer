package ch.hevs.aipu.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.admin.entity.newsendpoint.Newsendpoint;
import ch.hevs.aipu.admin.entity.newsendpoint.model.News;


/**
 * Created by Matthieu on 10.12.2015.
 */
public class NewsEndpointsAsyncTask extends AsyncTask<Void,Void, List<News>> {


    private static final String TAG = NewsEndpointsAsyncTask.class.getName();
    private static NewsEndpoint myApiService = null;
    private Context context;


    public NewsEndpointsAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        if(myApiService == null){
            // Only do this once
            Newsendpoint.Builder builder = new Newsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://conference-6452.appspot.com/_ah/apionfer/");

            myApiService = builder.build();
        }
            // end options for devappserver



    try{
        //getAll

        return myApiService.listNews().execute().getItems();
    } catch (IOException e){
        Log.e(TAG, e.toString());
        return new ArrayList<News>();
    }
    }

    @Override
    protected void onPostExecute(List<News> news) {
        //Toast.makeText(context, result.getTitle(), Toast.LENGTH_LONG).show();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (News n:news) {

            values.put(NewsContract.NewsEntry.KEY_TITLE, n.getTitle());
            values.put(NewsContract.NewsEntry.KEY_TEXT, n.getText());
            values.put(NewsContract.NewsEntry.KEY_PUBLICATION, n.getPublished().toString());

            db.insert(NewsContract.NewsEntry.TABLE_NEWS, null, values);

            db.close();
        }


    }
}




