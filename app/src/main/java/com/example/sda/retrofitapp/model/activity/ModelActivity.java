package com.example.sda.retrofitapp.model.activity;

import com.google.gson.annotations.SerializedName;

public class ModelActivity {

    @SerializedName("PhoneNo")
    private String phoneNo;
    @SerializedName("Incoming")
    private boolean incoming;
    @SerializedName("ActivityType")
    private ActivityType activityType;
    @SerializedName("Note")
    private String note;
    @SerializedName("NoteTitle")
    private String noteTitle;
    @SerializedName("Status")
    private Status status;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isIncoming() {
        return incoming;
    }

    public void setIncoming(boolean incoming) {
        this.incoming = incoming;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @Override
    public String toString() {
        return "ModelActivity{" +
                "phoneNo='" + phoneNo + '\'' +
                ", incoming=" + incoming +
                ", activityType=" + activityType +
                ", note='" + note + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", status=" + status +
                '}';
    }
}
