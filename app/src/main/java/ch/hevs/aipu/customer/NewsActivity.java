package ch.hevs.aipu.customer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.hevs.aipu.admin.entity.newsendpoint.model.News;

public class NewsActivity extends AppCompatActivity {
    List<News> newsList= new ArrayList<News>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //fill list from DB
        for(int i=0;i<3;i++)
        {
            News n = new News();
            n.setTitle("Titel " + i);
            n.setText("Description " + i);
            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            n.setPublished(new DateTime(seconds));
            newsList.add(n);
        }
        //fill string list with news
        List<String> names = new ArrayList<String>(newsList.size());
        for (News n : newsList)
        {
            names.add(n.getTitle() + " " + "\n" + n.getText());
        }
        //set names to listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ListView lv = (ListView)findViewById(R.id.listViewNews);
        lv.setAdapter(arrayAdapter);
    }

}
