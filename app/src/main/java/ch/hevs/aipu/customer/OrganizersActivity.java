package ch.hevs.aipu.customer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.aipu.LocalDB.DBHelper;
import ch.hevs.aipu.admin.entity.stakeholderendpoint.model.Stakeholder;

public class OrganizersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelper dbHelper = new DBHelper(this);
        List<Stakeholder> stakeholderList = dbHelper.getListStakeholdersByType("organizer");
        dbHelper.close();

        //fill string list with news
        List<String> organizers = new ArrayList<String>(stakeholderList.size());
        for (Stakeholder s : stakeholderList)
        {
            organizers.add(s.getName() + "\n" + s.getEmail() + "\n" + s.getWebsite());
        }

        //set names to listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, organizers);
        ListView lv = (ListView)findViewById(R.id.listViewOrganizers);
        lv.setAdapter(arrayAdapter);
    }

}
