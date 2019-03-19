package com.sewamobil.sewamobil.menu.booking.detailbooking;

import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;
import com.sewamobil.sewamobil.menu.rentcar.Model.RentGeneralModel;
import com.sewamobil.sewamobil.utils.Functions;

import java.util.ArrayList;
import java.util.List;

import lib.almuwahhid.utils.LibUi;

public class DetailBookingHelper {
    public static List<RentGeneralModel> getDetail(BookingModel model){
        List<RentGeneralModel> getDetail = new ArrayList<>();

        RentGeneralModel rentGeneralModel = new RentGeneralModel("Kode Booking", model.getKode_booking(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Merk", model.getMerk(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Model", model.getNama_model(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Jaminan", model.getJaminan(), "text");
        getDetail.add(rentGeneralModel);

        String begin = model.getBegin_date().split(" ")[0];
        String due = model.getDue_date().split(" ")[0];

        rentGeneralModel = new RentGeneralModel("Tanggal Mulai", begin.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(begin.split("-")[1])-1)+ " "+begin.split("-")[0], "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Tanggal Kembali", due.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(due.split("-")[1])-1)+ " "+due.split("-")[0], "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Status", model.getMerk(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Biaya", Functions.rupiahFormat(Float.valueOf(model.getBiaya())), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Foto Konfirmasi", model.getConfirmation_photo(), "photo");
        getDetail.add(rentGeneralModel);

        return getDetail;
    }
}
