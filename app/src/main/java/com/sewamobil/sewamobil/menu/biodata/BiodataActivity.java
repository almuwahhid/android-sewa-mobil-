package com.sewamobil.sewamobil.menu.biodata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.biodata.Model.UserModel;

import lib.almuwahhid.Activity.ActivityGeneral;

public class BiodataActivity extends ActivityGeneral implements BiodataInterface.View{

    BiodataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        presenter = new BiodataPresenter(getContext(), this);
    }

    @Override
    public void onChangeProfil(UserModel model) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onFailed() {

    }
}
