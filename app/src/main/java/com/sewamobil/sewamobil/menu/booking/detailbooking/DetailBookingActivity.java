package com.sewamobil.sewamobil.menu.booking.detailbooking;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;

public class DetailBookingActivity extends ActivityGeneral {
    BookingModel model;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(
                ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setTitle("Detail Booking");
        model = (BookingModel) getIntent().getSerializableExtra("data");
    }
}
