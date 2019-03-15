package com.sewamobil.sewamobil.menu.booking;

import com.sewamobil.sewamobil.base.BaseViewInterface;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;

public interface BookingInterface {
    interface Presenter{
        void requestBooking(BookingModel model);
    }

    interface View extends BaseViewInterface {
        void onRequestBooking(BookingModel model);
    }
}
