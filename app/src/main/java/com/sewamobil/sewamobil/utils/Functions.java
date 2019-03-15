package com.sewamobil.sewamobil.utils;

import android.content.Context;

import com.sewamobil.sewamobil.menu.biodata.Model.UserModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lib.almuwahhid.utils.LibUi;

public class Functions {
    public static String rupiahFormat(float rupiah){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(rupiah);
    }

    public static void saveUserPreferenece(Context context, UserModel model){
        LibUi.setSPString(context, ProjectConstant.SP_alamat, model.getAlamat());
        LibUi.setSPString(context, ProjectConstant.SP_jk, model.getJk());
        LibUi.setSPString(context, ProjectConstant.SP_name, model.getName());
        LibUi.setSPString(context, ProjectConstant.SP_telp, model.getTelp());
        LibUi.setSPString(context, ProjectConstant.SP_tgllahir, model.getTgl_lahir());
        LibUi.setSPString(context, ProjectConstant.SP_uid, model.getId());
        LibUi.setSPString(context, ProjectConstant.SP_username, model.getUsername());
    }

    public static void removeUserPreference(Context context){
        LibUi.removeSPString(context, ProjectConstant.SP_alamat);
        LibUi.removeSPString(context, ProjectConstant.SP_jk);
        LibUi.removeSPString(context, ProjectConstant.SP_name);
        LibUi.removeSPString(context, ProjectConstant.SP_telp);
        LibUi.removeSPString(context, ProjectConstant.SP_tgllahir);
        LibUi.removeSPString(context, ProjectConstant.SP_uid);
        LibUi.removeSPString(context, ProjectConstant.SP_username);
    }

    public static boolean isUserLogin(Context context){
        if(LibUi.getSPString(context, ProjectConstant.SP_uid).equals(""))
            return false;
         else
            return true;
    }
}
