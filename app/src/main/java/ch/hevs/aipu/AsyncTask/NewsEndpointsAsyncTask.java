package ch.hevs.aipu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.LocalDB.DBHelper;
import ch.hevs.aipu.admin.entity.newsendpoint.Newsendpoint;
import ch.hevs.aipu.admin.entity.newsendpoint.model.News;


/**
 * Created by Matthieu on 10.12.2015.
 */
public class NewsEndpointsAsyncTask extends AsyncTask<Void,Void, List<News>> {


    private static final String TAG = NewsEndpointsAsyncTask.class.getName();
    private static Newsendpoint myApiService = null;
    private Context context;


    public NewsEndpointsAsyncTask(Context context){
        this.context = context;
    }



    @Override
    protected List<News> doInBackground(Void... params) {
        if(myApiService == null){
            // Only do this once
            Newsendpoint.Builder builder = new Newsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://conference-6452.appspot.com/_ah/api/");

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

       /* if(news != null) {
            for (News seller : news) {
                Log.i(TAG, "Name: " + seller.getTitle());
            }
        }
*/

        DBHelper dbHelper = new DBHelper(this.context);



       // dbHelper.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NEWS);




       for (News n:news) {

           //Log.i(TAG, "Name: " + n.getId());
           if(dbHelper.checkIfNewsExist(n.getId())) {

               dbHelper.addNews(n.getId(), n.getTitle(), n.getText(), n.getPublished().toString());

           }
       }



    }
}




