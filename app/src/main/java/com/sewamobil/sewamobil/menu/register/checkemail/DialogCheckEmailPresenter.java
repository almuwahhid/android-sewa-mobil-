package com.sewamobil.sewamobil.menu.register.checkemail;

import android.content.Context;

public class DialogCheckEmailPresenter implements DialogCheckEmailInterface.Presenter{
    Context context;
    DialogCheckEmailInterface.View view;

    public DialogCheckEmailPresenter(Context context, DialogCheckEmailInterface.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void checkEmail(String email) {

    }
}
