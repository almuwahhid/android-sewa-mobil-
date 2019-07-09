package com.sewamobil.sewamobil.menu.booking.detailbooking;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;
import com.sewamobil.sewamobil.menu.booking.detailbooking.DialogUpload.DialogUploadBukti;
import com.sewamobil.sewamobil.menu.booking.listbooking.ListBookingActivity;
import com.sewamobil.sewamobil.menu.rentcar.Model.RentGeneralModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.almuwahhid.Activity.ActivityGeneral;
import lib.almuwahhid.utils.AlertDialogBuilder;
import lib.almuwahhid.utils.LibUi;
import lib.almuwahhid.utils.PermissionChecker;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class DetailBookingActivity extends ActivityGeneral implements DetailBookingView.View{
    BookingModel model;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lay_upload)
    LinearLayout lay_upload;
    @BindView(R.id.lay_kembali)
    LinearLayout lay_kembali;

    DetailBookingAdapter adapter;

    Bitmap bitmapImage;
    String base64image = "";

    DetailBookingPresenter presenter;

    private static final String[] RequiredPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    protected PermissionChecker permissionChecker = new PermissionChecker();
    List<RentGeneralModel> getDetail = new ArrayList<>();

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

        presenter = new DetailBookingPresenter(getContext(), this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        for (RentGeneralModel r: DetailBookingHelper.getDetail(model)){
            getDetail.add(r);
        }
        adapter = new DetailBookingAdapter(getContext(), getDetail);
        rv.setAdapter(adapter);

        lay_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionChecker.verifyPermissions(DetailBookingActivity.this, RequiredPermissions, new PermissionChecker.VerifyPermissionsCallback() {

                    @Override
                    public void onPermissionAllGranted() {
                        EasyImage.openGallery(DetailBookingActivity.this, 0);
                    }

                    @Override
                    public void onPermissionDeny(String[] permissions) {
                        Toast.makeText(getContext(), "Please grant required permissions.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        lay_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialogBuilder(getContext(), "Apakah Anda yakin sudah mengembalikan kendaraan yang disewa?", "Ya, konfirmasi sekarang", "Belum", new AlertDialogBuilder.OnAlertDialog() {
                    @Override
                    public void onPositiveButton(DialogInterface dialog) {
                        dialog.dismiss();
                        presenter.requestKembaliKendaraan(model.getId_booking());
                    }

                    @Override
                    public void onNegativeButton(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        if(model.getKonfirmasi().equals("N") && !model.getDelete().equals("")){
            lay_upload.setVisibility(View.GONE);
        }
        if(model.getKonfirmasi().equals("Y")){
            lay_upload.setVisibility(View.GONE);
            if(model.getWaktu_pengembalian().equals("0000-00-00 00:00:00")){
                lay_kembali.setVisibility(View.VISIBLE);
            } else {
                lay_kembali.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d("asdEdit", "onActivityResult: masuk sini");
            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //Some error handlingn
                    e.printStackTrace();
                }

                @Override
                public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
                    Log.d("asdEdit", "onImagesPicked: "+imageFiles);

                    try {
                        Uri imageUri = Uri.fromFile(imageFiles.get(0));
                        bitmapImage = MediaStore.Images.Media.getBitmap(DetailBookingActivity.this.getContentResolver(), imageUri);
                        if (bitmapImage != null) {
                            base64image = LibUi.convertToBase64(bitmapImage);
                        }

                        new DialogUploadBukti(getContext(), bitmapImage, base64image, model.getId_booking(), new DialogUploadBukti.OnDialogUploadBukti() {
                            @Override
                            public void onAfterUpload() {
                                startActivity(new Intent(getContext(), ListBookingActivity.class));
                                finish();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    //Cancel handling, you might wanna remove taken photo if it was canceled
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getContext());
                        if (photoFile != null) photoFile.delete();
                    }
                }
            });
        }
    }


    @Override
    public void onRequestKembaliKendaraan(boolean isSuccess, String data) {
        if(isSuccess){
            lay_kembali.setVisibility(View.GONE);
            model.setWaktu_pengembalian(data);
            getDetail.clear();
            for (RentGeneralModel r: DetailBookingHelper.getDetail(model)){
                getDetail.add(r);
            }
            adapter.notifyDataSetChanged();
        } else {
            LibUi.ToastShort(getContext(), "Gagal mengkonfirmasi pengembalian");
        }
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
