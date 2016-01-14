package ch.hevs.aipu.localClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ch.hevs.aipu.admin.entity.conferenceendpoint.model.Key;

/**
 * Created by Muhamed on 14.01.2016.
 */
public class AndroidConference {
    @SerializedName("title")
    private String title;
    @SerializedName("fromDate")
    private String fromDate;
    @SerializedName("toDate")
    private String toDate;
    @SerializedName("room")
    private String room;
    @SerializedName("website")
    private String website;
    @SerializedName("keys")
    private List<Long> keys;

    public AndroidConference (String title, String fromDate, String toDate, String room, String website, List<Long> keys){
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.room = room;
        this.website = website;
        this.keys = keys;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Long> getKeys() {
        return keys;
    }

    public void setKeys(List<Long> keys) {
        this.keys = keys;
    }
}
