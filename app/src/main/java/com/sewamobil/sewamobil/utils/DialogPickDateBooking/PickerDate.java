package com.sewamobil.sewamobil.utils.DialogPickDateBooking;

import java.util.ArrayList;
import java.util.List;

public class PickerDate {
    List<DatePickerModel> list;

    public PickerDate() {
        list = new ArrayList<>();
    }

    public void addGeneralList(List<String> list){
        this.list.clear();
        for (String x : list){
            DatePickerModel data = new DatePickerModel(x, 1);
            this.list.add(data);
        }
    }

    public String getStartDate(){
        for (DatePickerModel datePickerModel : list){
            if(datePickerModel.getType() == 2){
                return datePickerModel.getDate();
            }
        }

        return "";
    }

    public void addStartDate(String date){
        boolean isNew = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).type == 2){
                list.get(i).setDate(date);
                isNew = false;
            }
        }
        if(isNew){
            list.add(new DatePickerModel(date, 2));
        }
    }

    public void addFinishDate(String date){
        boolean isNew = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).type == 3){
                list.get(i).setDate(date);
                isNew = false;
            }
        }
        if(isNew){
            list.add(new DatePickerModel(date, 3));
        }
    }

    public List<DatePickerModel> getList() {
        return list;
    }
}
