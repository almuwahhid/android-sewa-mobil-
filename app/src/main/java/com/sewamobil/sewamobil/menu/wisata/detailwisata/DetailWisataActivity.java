package com.sewamobil.sewamobil.menu.wisata.detailwisata;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.wisata.Model.WisataModel;
import com.sewamobil.sewamobil.utils.Functions;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;

import static com.sewamobil.sewamobil.utils.ProjectConstant.WEBVIEW_STYLE;

public class DetailWisataActivity extends ActivityGeneral {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_biaya)
    TextView tv_biaya;
    @BindView(R.id.tv_title)
    TextView tv_title;

    WisataModel wisataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(
                ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setTitle("Detail Wisata");

        if(getIntent().hasExtra("data")){
            wisataModel = (WisataModel) getIntent().getSerializableExtra("data");

            tv_title.setText(wisataModel.getNama_wisata());
            tv_biaya.setText("Biaya : "+Functions.rupiahFormat(Float.valueOf(wisataModel.getBiaya())));

            String html = WEBVIEW_STYLE;
            String mime = "text/html";
            String encoding = "utf-8";

            webview.loadData(String.format(html, wisataModel.getKeterangan()), mime, encoding);
        } else {
            finish();
        }
    }
}
