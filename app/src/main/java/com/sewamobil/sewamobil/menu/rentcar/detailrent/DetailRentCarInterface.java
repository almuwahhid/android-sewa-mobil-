package com.sewamobil.sewamobil.menu.rentcar.detailrent;

import com.sewamobil.sewamobil.base.BaseViewInterface;

import java.util.HashMap;
import java.util.List;

public interface DetailRentCarInterface {
    public interface Presenter{
        void bookingKendaraan(HashMap<String, String> params);
        void requestListBookingActive(String id);
    }

    public interface View extends BaseViewInterface{
        void onSuccessBookingKendaraan(String id_booking);
        void onFailBookingKendaran(String message);
        void onRequestListBookingActive(List<String> dates);
    }
}
