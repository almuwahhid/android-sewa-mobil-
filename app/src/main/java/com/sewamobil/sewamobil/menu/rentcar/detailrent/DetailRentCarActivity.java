package com.sewamobil.sewamobil.menu.rentcar.detailrent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.BookingActivity;
import com.sewamobil.sewamobil.menu.login.LoginActivity;
import com.sewamobil.sewamobil.menu.rentcar.Model.RentCarModel;
import com.sewamobil.sewamobil.menu.rentcar.RentCarHelper;
import com.sewamobil.sewamobil.utils.DialogPickDateBooking.DialogPickDateBooking;
import com.sewamobil.sewamobil.utils.DialogPickDateBooking.PickerDate;
import com.sewamobil.sewamobil.utils.Functions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;
import lib.almuwahhid.utils.LibUi;

public class DetailRentCarActivity extends ActivityGeneral implements DetailRentCarInterface.View {

    DetailRentCarAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lay_book)
    LinearLayout lay_book;
    @BindView(R.id.lay_empty)
    LinearLayout lay_empty;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.img_rent)
    ImageView img_rent;


    RentCarModel model;
    DetailRentCarPresenter presenter;
    PickerDate pickerDate = new PickerDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rent_car);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail Kendaraan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new DetailRentCarPresenter(this, this);

        if(getIntent().hasExtra("data")){
            model = (RentCarModel) getIntent().getSerializableExtra("data");

            rv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new DetailRentCarAdapter(RentCarHelper.detail(model));
            rv.setAdapter(adapter);

            lay_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    startActivity(new Intent(getContext(), BookingActivity.class).putExtra("data", model));
                    if(Functions.isUserLogin(getContext())){
                        presenter.requestListBookingActive(model.getId_kendaraan());
                    } else {
                        LibUi.ToastShort(getContext(), "Login diperlukan");
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }

                }
            });

            Picasso.with(getContext())
                    .load(model.getFoto_kendaraan())
                    .error(R.drawable.bg)
                    .placeholder(R.drawable.bg)
                    .into(img_rent, new Callback() {
                        @Override
                        public void onSuccess() {
                            pb.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            pb.setVisibility(View.GONE);
                        }
                    });

            Log.d("test", "onCreate: "+model.isAvailable());
            /*if(model.isAvailable()){
                lay_book.setVisibility(View.VISIBLE);
                lay_empty.setVisibility(View.GONE);

            } else {
                lay_book.setVisibility(View.GONE);
                lay_empty.setVisibility(View.VISIBLE);
            }*/
        } else {
            finish();
        }
    }

    @Override
    public void onSuccessBookingKendaraan(String id_booking) {

    }

    @Override
    public void onFailBookingKendaran(String message) {

    }

    @Override
    public void onRequestListBookingActive(List<String> dates) {
        pickerDate.addGeneralList(dates);
        new DialogPickDateBooking(getContext(), "Pilih tanggal sewa", pickerDate, new DialogPickDateBooking.OnDialogPickDateBooking() {
            @Override
            public void onDialogPick(String date) {
                pickerDate.addStartDate(date);
                new BookingActivity(getContext(), model, pickerDate).show();
            }
        });
    }

    @Override
    public void onLoading() {
        LibUi.showLoadingDialog(getContext(), R.drawable.logo_rent);
    }

    @Override
    public void onHideLoading() {
        LibUi.hideLoadingDialog(getContext());
    }

    @Override
    public void onFailed() {
        LibUi.ToastShort(getContext(), "Bermasalah dengan Server");
    }
}