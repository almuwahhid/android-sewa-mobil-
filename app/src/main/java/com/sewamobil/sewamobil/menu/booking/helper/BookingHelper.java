package com.sewamobil.sewamobil.menu.booking.helper;

import com.sewamobil.sewamobil.utils.dialogPicker.GeneralModel;

import java.util.ArrayList;
import java.util.List;

public class BookingHelper {
    public static List<GeneralModel> getListJaminan(){
        List<GeneralModel> getListJaminan = new ArrayList<>();
        GeneralModel generalModel = new GeneralModel("SIM", "SIM", "");
        getListJaminan.add(generalModel);
        generalModel = new GeneralModel("KTP", "KTP", "");
        getListJaminan.add(generalModel);
        generalModel = new GeneralModel("BPKB", "BPKB", "");
        getListJaminan.add(generalModel);
        return getListJaminan;
    }
}
