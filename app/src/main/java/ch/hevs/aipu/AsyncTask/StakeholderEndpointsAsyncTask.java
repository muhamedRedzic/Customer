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
import ch.hevs.aipu.admin.entity.stakeholderendpoint.Stakeholderendpoint;
import ch.hevs.aipu.admin.entity.stakeholderendpoint.model.Stakeholder;

/**
 * Created by Matthieu on 11.01.2016.
 */
public class StakeholderEndpointsAsyncTask extends AsyncTask<Void,Void, List<Stakeholder>> {


    private static final String TAG = NewsEndpointsAsyncTask.class.getName();
    private static Stakeholderendpoint myApiService = null;
    private Context context;


    public StakeholderEndpointsAsyncTask(Context context){
        this.context = context;
    }



    @Override
    protected List<Stakeholder> doInBackground(Void... params) {
        if(myApiService == null){
            // Only do this once
            Stakeholderendpoint.Builder builder = new Stakeholderendpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://conference-6452.appspot.com/_ah/api/");

            myApiService = builder.build();
        }
        // end options for devappserver



        try{
            //getAll

            return myApiService.listStakeholder().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Stakeholder>();
        }
    }


    protected void onPostExecute(List<Stakeholder> stakeholders) {

       /* if(news != null) {
            for (News seller : news) {
                Log.i(TAG, "Name: " + seller.getTitle());
            }
        }
*/

        DBHelper dbHelper = new DBHelper(this.context);

        dbHelper.cleanStakeholderTable();

        // dbHelper.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NEWS);




        for (Stakeholder s:stakeholders) {

            //Log.i(TAG, "Name: " + n.getId());
            // if(dbHelper.checkIfNewsExist(n.getId())) {

            dbHelper.addStakeholder(s);

            //}
        }



    }
}
