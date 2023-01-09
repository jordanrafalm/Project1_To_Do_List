package com.example.project1_to_do_list;

import java.util.Date;

public class Record {
    private int id;
    private Date date;
    private String text;
    private boolean idDeleted;


    public Record(int id, Date date, String text, boolean idDeleted) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.idDeleted = idDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(boolean idDeleted) {
        this.idDeleted = idDeleted;
    }
}
