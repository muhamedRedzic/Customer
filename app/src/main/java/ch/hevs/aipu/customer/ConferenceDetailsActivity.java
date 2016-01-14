package ch.hevs.aipu.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.LocalDB.DBHelper;
import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Conference;
import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key;
import ch.hevs.aipu.admin.entity.stakeholderendpoint.model.Stakeholder;
import ch.hevs.aipu.localClasses.AndroidConference;

public class ConferenceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelper dbHelper = new DBHelper(this);
        //retrieve selected conference
        Gson gson = new Gson();
        String target = getIntent().getStringExtra("SelectedConference");
        Log.i("GSON : ", target);
        AndroidConference selectedConference = gson.fromJson(target, AndroidConference.class); // Converts the JSON String to an Object

        //retrieve textviews
        TextView tvTitle = (TextView) findViewById(R.id.textViewConferenceDetailsTitle);
        TextView tvFromDate = (TextView) findViewById(R.id.textViewConferenceDetailsFromDate);
        TextView tvToDate = (TextView) findViewById(R.id.textViewConferenceDetailsToDate);
        TextView tvRoom = (TextView) findViewById(R.id.textViewConferenceDetailsRoom);
        TextView tvWebsite = (TextView) findViewById(R.id.textViewConferenceDetailsWebsite);
        ListView tvStakeholders = (ListView) findViewById(R.id.listViewConferenceDetailsStakeholders);

        //set textview content
        tvTitle.setText(selectedConference.getTitle());
        tvFromDate.setText(selectedConference.getFromDate());
        tvToDate.setText(selectedConference.getToDate());
        tvRoom.setText(selectedConference.getRoom());
        tvWebsite.setText(selectedConference.getWebsite());

        //retrieve stakeholders by id
        List<Long> keys = selectedConference.getKeys();
        List<Stakeholder> stakeholders = new ArrayList<>();


        if (keys != null){
            for (Long l:keys) {
                Log.i("Stakeholder Keys : ", String.valueOf(l));
            }
        }

    }
}
