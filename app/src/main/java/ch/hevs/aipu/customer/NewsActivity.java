package ch.hevs.aipu.customer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.LocalDB.DBHelper;
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
        DBHelper dbHelper = new DBHelper(this);
        newsList = dbHelper.getListNews();
        dbHelper.close();

        //fill string list with news
        List<String> names = new ArrayList<String>(newsList.size());
        String date;
        String time;
        for (News n : newsList)
        {
            date = n.getPublished().toString().substring(0,9);
            time = n.getPublished().toString().substring(11,16);
            names.add(n.getTitle() + " " + date + " " + time + "\n" + n.getText());
        }
        //set names to listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ListView lv = (ListView)findViewById(R.id.listViewNews);
        lv.setAdapter(arrayAdapter);
    }

}
