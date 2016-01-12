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
import ch.hevs.aipu.admin.entity.conferenceendpoint.Conferenceendpoint;
import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Conference;


/**
 * Created by Matthieu on 10.12.2015.
 */
public class ConferenceEndpointsAsyncTask extends AsyncTask<Void,Void, List<Conference>> {


    private static final String TAG = NewsEndpointsAsyncTask.class.getName();
    private static Conferenceendpoint myApiService = null;
    private Context context;


    public ConferenceEndpointsAsyncTask(Context context){
        this.context = context;
    }



    @Override
    protected List<Conference> doInBackground(Void... params) {
        if(myApiService == null){
            // Only do this once
            Conferenceendpoint.Builder builder = new Conferenceendpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://conference-6452.appspot.com/_ah/api/");

            myApiService = builder.build();
        }
        // end options for devappserver



        try{
            //getAll

            return myApiService.listConference().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Conference>();
        }
    }

    @Override
    protected void onPostExecute(List<Conference> conf) {

       /* if(news != null) {
            for (News seller : news) {
                Log.i(TAG, "Name: " + seller.getTitle());
            }
        }
*/

        DBHelper dbHelper = new DBHelper(this.context);

        dbHelper.cleanConferenceTable();

        // dbHelper.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NEWS);




        for (Conference c:conf) {

            //Log.i(TAG, "Name: " + n.getId());
            // if(dbHelper.checkIfNewsExist(n.getId())) {

            dbHelper.addConference(c);

            //}
        }



    }
}




