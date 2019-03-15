package com.sewamobil.sewamobil.menu.booking.checkbooking;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;
import com.sewamobil.sewamobil.menu.booking.detailbooking.DetailBookingActivity;

import lib.almuwahhid.utils.DialogBuilder;
import lib.almuwahhid.utils.LibUi;

public class DialogCheckBooking extends DialogBuilder implements DialogCheckInterface.View{

    DialogCheckBookingPresenter presenter;

    ImageView img_back;
    EditText edt_kodebooking;
    RelativeLayout lay_checkbooking;

    public DialogCheckBooking(Context context, OnDialogCheckBooking onDialogCheckBooking) {
        super(context, R.layout.dialog_check_booking);
        presenter = new DialogCheckBookingPresenter(getContext(), this);
        this.onDialogCheckBooking = onDialogCheckBooking;

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                img_back = dialog.findViewById(R.id.img_back);
                edt_kodebooking = dialog.findViewById(R.id.edt_kodebooking);
                lay_checkbooking = dialog.findViewById(R.id.lay_checkbooking);
            }
        });

        setFullScreen(lay_checkbooking);
        setGravity(Gravity.BOTTOM);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        edt_kodebooking.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.checkBookingModel(edt_kodebooking.getText().toString());
                    return true;
                }
                return false;
            }
        });
        show();
    }

    @Override
    public void onSuccessCheck(BookingModel model) {
        onDialogCheckBooking.onSuccessCheck(model);
        dismiss();
    }

    @Override
    public void onFailedCheck() {
        LibUi.ToastShort(getContext(), "Kode booking tidak ditemukan");
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
        LibUi.ToastShort(getContext(), "Bermasalah dengan server");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        edt_kodebooking.setText("");
    }

    OnDialogCheckBooking onDialogCheckBooking;
    public interface OnDialogCheckBooking{
        void onSuccessCheck(BookingModel model);
    }
}
