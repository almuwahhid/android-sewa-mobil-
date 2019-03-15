package com.sewamobil.sewamobil.menu.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;
import com.sewamobil.sewamobil.menu.booking.detailbooking.DetailBookingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;
import lib.almuwahhid.utils.LibUi;

public class BookingActivity extends ActivityGeneral implements BookingInterface.View{

    BookingPresenter presenter;
    @BindView(R.id.btn_booking)
    LinearLayout btn_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        presenter = new BookingPresenter(getContext(), this);
        setFormsToValidate();

        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LibUi.isFormValid(getContext(), getWindow().getDecorView(), forms)){
                    presenter.requestBooking(getBookingModel());
                }

            }
        });
    }



    private BookingModel getBookingModel(){
        BookingModel getBookingModel = new BookingModel();
        return getBookingModel;
    }

    @Override
    public void onRequestBooking(BookingModel model) {
        startActivity(new Intent(getContext(), DetailBookingActivity.class).putExtra("data", model));
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
        LibUi.ToastShort(getContext(), "Server bermasalah");
    }

    List<Integer> forms = new ArrayList<>();
    private void setFormsToValidate(){

    }
}
