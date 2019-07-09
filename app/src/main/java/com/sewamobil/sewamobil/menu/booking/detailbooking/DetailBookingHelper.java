package com.sewamobil.sewamobil.menu.booking.detailbooking;

import com.sewamobil.sewamobil.BuildConfig;
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

        rentGeneralModel = new RentGeneralModel("Merk", model.getMerk_kendaraan(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Jenis Kendaraan", model.getTipe_kendaraan(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Model", model.getNama_model(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Plat Nomor", model.getPlat_nomor(), "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Jaminan", model.getJaminan(), "text");
        getDetail.add(rentGeneralModel);

        String begin = model.getTanggal_mulai().split(" ")[0];
        String begintime = model.getTanggal_mulai().split(" ")[1];
        String due = model.getTanggal_berakhir().split(" ")[0];
        String duetime = model.getTanggal_berakhir().split(" ")[1];
        String submit = model.getKirimkan_tanggal().split(" ")[0];
        String submittime = model.getKirimkan_tanggal().split(" ")[1];


        rentGeneralModel = new RentGeneralModel("Waktu Booking", submit.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(submit.split("-")[1])-1)+ " "+submit.split("-")[0]+" "+submittime, "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Tanggal Mulai", begin.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(begin.split("-")[1])-1)+ " "+begin.split("-")[0]+" "+begintime, "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Tanggal Kembali", due.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(due.split("-")[1])-1)+ " "+due.split("-")[0]+" "+duetime, "text");
        getDetail.add(rentGeneralModel);


        String konfirm = "";
        if(model.getKonfirmasi().equals("Y")){
            if(!model.getDelete().equals("")){
                konfirm = "Dibatalkan";
            } else {
                konfirm = "Sudah dikonfirmasi";
            }
        } else if(model.getKonfirmasi().equals("N") && !model.getDelete().equals("")){
            konfirm = "Ditolak";
        } else {
            konfirm = "Belum dikonfirmasi oleh Admin";
        }
        rentGeneralModel = new RentGeneralModel("Status", konfirm, "text");
        getDetail.add(rentGeneralModel);

        rentGeneralModel = new RentGeneralModel("Biaya", Functions.rupiahFormat(Float.valueOf(model.getBiaya())), "text");
        getDetail.add(rentGeneralModel);

        if(model.getWaktu_pengembalian().equals("0000-00-00 00:00:00")){
            rentGeneralModel = new RentGeneralModel("Waktu Pengembalian", "Pengembalian kendaraan belum dikonfirmasi", "text");
            getDetail.add(rentGeneralModel);
        } else {
            String waktupengembalian = model.getWaktu_pengembalian().split(" ")[0];
            String waktupengembaliantime = model.getWaktu_pengembalian().split(" ")[1];
            rentGeneralModel = new RentGeneralModel("Waktu Pengembalian", waktupengembalian.split("-")[2]+" "+ LibUi.monthName(Integer.valueOf(waktupengembalian.split("-")[1])-1)+ " "+waktupengembalian.split("-")[0]+" "+waktupengembaliantime, "text");
            getDetail.add(rentGeneralModel);
        }

        String p_kon = "";
        if(!model.getKonfirmasi_foto().equals("")){
            p_kon = BuildConfig.base_url+"confirm/"+model.getKonfirmasi_foto();
        }

        rentGeneralModel = new RentGeneralModel("Foto Konfirmasi", p_kon, "photo");
        getDetail.add(rentGeneralModel);

        return getDetail;
    }
}
