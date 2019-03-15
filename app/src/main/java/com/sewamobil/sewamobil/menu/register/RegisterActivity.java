package com.sewamobil.sewamobil.menu.register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;
import lib.almuwahhid.utils.LibUi;

public class RegisterActivity extends ActivityGeneral implements RegisterInterface.View{

    RegisterPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_user_email)
    EditText edt_user_email;

    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(
                ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setTitle("Register");

        if(getIntent().hasExtra("data")){
            email = getIntent().getStringExtra("data");
        }
        edt_user_email.setText(email);


        presenter = new RegisterPresenter(getContext(), this);
    }

    @Override
    public void onSuccessRegister() {
        startActivity(new Intent(getContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onFailedRegister(String message) {
        LibUi.ToastShort(getContext(), message);
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
}
