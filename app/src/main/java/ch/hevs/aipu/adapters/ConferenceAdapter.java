package ch.hevs.aipu.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Conference;
import ch.hevs.aipu.customer.R;

public class ConferenceAdapter extends ArrayAdapter<Conference> {
    Context context;
    int layoutResourceId;
    ArrayList<Conference> data = new ArrayList<Conference>();

    public ConferenceAdapter(Context context, int layoutResourceId, ArrayList<Conference> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ConferenceHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ConferenceHolder();
            holder.textTitle = (TextView) row.findViewById(R.id.textViewConferenceTitle);
            holder.btnAddCalendar = (Button) row.findViewById(R.id.buttonConferenceAddCalendar);
            row.setTag(holder);
        } else {
            holder = (ConferenceHolder) row.getTag();
        }
        final Conference conference = data.get(position);
        holder.textTitle.setText(conference.getTitle());
        final ConferenceHolder finalHolder = holder;
        holder.btnAddCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar start = Calendar.getInstance();
                start.setTime(new Date(conference.getStart().getValue()));
                Calendar end = Calendar.getInstance();
                end.setTime(new Date(conference.getEnd().getValue()));
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                intent.putExtra(CalendarContract.Events.TITLE, conference.getTitle());
                intent.putExtra("beginTime", start.getTimeInMillis());
                intent.putExtra("endTime", end.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Conference : " + conference.getTitle() + "\n" + "Room : " + conference.getRoom());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "UNIL");
                context.startActivity(intent);
            }
        });
        return row;

    }

    static class ConferenceHolder {
        TextView textTitle;
        Button btnAddCalendar;
    }

}
