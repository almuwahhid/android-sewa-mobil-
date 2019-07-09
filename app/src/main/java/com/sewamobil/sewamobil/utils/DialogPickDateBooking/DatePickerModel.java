package com.sewamobil.sewamobil.utils.DialogPickDateBooking;

public class DatePickerModel {
//    type = 1 : general picker
//    type = 2 : start picker
//    type = 3 : finish picker

    String date;
    int type;

    public DatePickerModel(String date, int type) {
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
