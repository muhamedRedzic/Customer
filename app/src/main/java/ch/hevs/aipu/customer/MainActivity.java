package ch.hevs.aipu.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ch.hevs.aipu.AsyncTask.ConferenceEndpointsAsyncTask;
import ch.hevs.aipu.AsyncTask.NewsEndpointsAsyncTask;
import ch.hevs.aipu.AsyncTask.StakeholderEndpointsAsyncTask;
import ch.hevs.aipu.LocalDB.DBHelper;
import ch.hevs.aipu.admin.entity.newsendpoint.model.News;

public class MainActivity extends AppCompatActivity {

    private Menu menu;
    private List<News> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new NewsEndpointsAsyncTask(this).execute();
        new ConferenceEndpointsAsyncTask(this).execute();
        new StakeholderEndpointsAsyncTask(this).execute();

        DBHelper db = new DBHelper(this);
//        List<Stakeholder> stake = db.getListStakeholdersByType("speaker");
//        Stakeholder st = stake.get(1);
//
//        List<Key> keyList = st.getConferences();
//        for (Key k:keyList) {
//            Log.i("result", k.getId().toString());
//        }
//        List<Conference> conf = db.getListConference();
//        for (Conference c:conf ) {
//            Log.i("conf",c.getTitle());
//
//        }
//        Conference c = conf.get(0);
//        List<Key> key = c.getStakeholders();
//        for (Key k:key) {
//            Log.i("result", k.getId().toString());
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void about(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        menu.clear();
        onCreateOptionsMenu(menu);
    }

    public void share(MenuItem item) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AIPU 2016");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "https://aipu2016.wordpress.com/");
        startActivity(Intent.createChooser(intent, "Share " + getResources().getText(R.string.app_name) + " on..."));
    }

    public void news(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void maps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void conferences(View view) {
        Intent intent = new Intent(this, ConferencesActivity.class);
        startActivity(intent);
    }

    public void plan(View view) {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    public void feedback(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void partners(View view) {
        Intent intent = new Intent(this, PartnersActivity.class);
        startActivity(intent);
    }

    public void organizers(View view) {
        Intent intent = new Intent(this, OrganizersActivity.class);
        startActivity(intent);
    }

    public void calendar(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android.cursor.item/event");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        startActivity(intent);
    }

    public void share(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AIPU 2016");
        intent.putExtra(android.content.Intent.EXTRA_TEXT,"https://aipu2016.wordpress.com/");
        startActivity(Intent.createChooser(intent, "Share " + getResources().getText(R.string.app_name) + " on..."));
    }
}
