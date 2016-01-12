package ch.hevs.aipu.customer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ch.hevs.aipu.adapters.ConferenceAdapter;
import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Conference;

public class ConferencesActivity extends AppCompatActivity {
    ListView conferenceList;
    ConferenceAdapter conferenceAdapter;
    ArrayList<Conference> conferences = new ArrayList<Conference>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //add test conferences
        conferences.add(new Conference().setTitle("Title 1"));
        conferences.add(new Conference().setTitle("Title 2"));
        conferences.add(new Conference().setTitle("Title 3"));

        //set item into adapter
        conferenceAdapter = new ConferenceAdapter(ConferencesActivity.this, R.layout.conferencesrow,conferences);
        conferenceList = (ListView) findViewById(R.id.listViewConferences);
        conferenceList.setItemsCanFocus(false);
        conferenceList.setAdapter(conferenceAdapter);

        //get on item click listener
        conferenceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Toast.makeText(ConferencesActivity.this,
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
