package com.example.sda.retrofitapp.model;

import com.google.gson.annotations.SerializedName;

public class CallActivity {

    @SerializedName("PhoneNo")
    private String phoneNo;
    @SerializedName("Incoming")
    private boolean incoming;
    @SerializedName("ActivityType")
    private int activityType;
    @SerializedName("Note")
    private String note;
    @SerializedName("NoteTitle")
    private String noteTitle;
    @SerializedName("Status")
    private int status;

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

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CallActivity{" +
                "phoneNo='" + phoneNo + '\'' +
                ", incoming=" + incoming +
                ", activityType=" + activityType +
                ", note='" + note + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", status=" + status +
                '}';
    }
}
