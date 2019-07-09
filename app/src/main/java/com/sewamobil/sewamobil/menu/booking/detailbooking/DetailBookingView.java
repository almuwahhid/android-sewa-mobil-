package com.sewamobil.sewamobil.menu.booking.detailbooking;

import com.sewamobil.sewamobil.base.BaseViewInterface;

public interface DetailBookingView {
    interface Presenter{
        void requestKembaliKendaraan(String id);
    }

    interface View extends BaseViewInterface {
        void onRequestKembaliKendaraan(boolean isSuccess, String data);
    }
}
