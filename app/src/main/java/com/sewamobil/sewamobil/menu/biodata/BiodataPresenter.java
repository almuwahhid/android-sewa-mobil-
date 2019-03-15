package com.sewamobil.sewamobil.menu.biodata;

import android.content.Context;

import com.sewamobil.sewamobil.menu.biodata.Model.UserModel;

public class BiodataPresenter implements BiodataInterface.Presenter{

    Context context;
    BiodataInterface.View view;

    public BiodataPresenter(Context context, BiodataInterface.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void changeProfil(UserModel model) {

    }
}
