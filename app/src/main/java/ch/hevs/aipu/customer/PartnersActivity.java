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

public class PartnersActivity extends AppCompatActivity {
    ListView partnerList;
    List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelper dbHelper = new DBHelper(this);
        stakeholders = dbHelper.getListStakeholdersByType("partner");
        dbHelper.close();

        //fill string list with news
        List<String> partners = new ArrayList<String>(stakeholders.size());
        for (Stakeholder s : stakeholders)
        {
            partners.add(s.getName() + "\n" + s.getEmail() + "\n" + s.getWebsite());
        }

        //set names to listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, partners);
        ListView lv = (ListView)findViewById(R.id.listViewPartners);
        lv.setAdapter(arrayAdapter);
    }

}
