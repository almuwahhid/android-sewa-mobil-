package com.sewamobil.sewamobil.menu.booking.detailbooking;

import android.os.Bundle;

import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;

import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;

public class DetailBookingActivity extends ActivityGeneral {
    BookingModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        model = (BookingModel) getIntent().getSerializableExtra("data");
    }
}
