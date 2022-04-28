package com.sewamobil.sewamobil.menu.booking.detailbooking;

import android.content.Context;

import com.sewamobil.sewamobil.utils.BasePresenter;
import com.sewamobil.sewamobil.utils.Endpoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lib.almuwahhid.utils.LibConstant;
import lib.almuwahhid.utils.UiLibRequest;

public class DetailBookingPresenter extends BasePresenter implements DetailBookingView.Presenter {

    DetailBookingView.View view;
    public DetailBookingPresenter(Context context, DetailBookingView.View view) {
        super(context);
        this.view = view;
    }

    @Override
    public void requestKembaliKendaraan(final String id) {
        UiLibRequest.POST(Endpoints.stringSetPengembalian(), getContext(), new UiLibRequest.OnPostRequest() {
            @Override
            public void onPreExecuted() {
                view.onLoading();
            }

            @Override
            public void onSuccess(JSONObject response) {
                view.onHideLoading();
                try {
                    if(response.getInt("status")== LibConstant.CODE_SUCCESS){
                        view.onRequestKembaliKendaraan(true, response.getString("data"));
                    }else {
                        view.onRequestKembaliKendaraan(false, "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.onRequestKembaliKendaraan(false, "");
                }
            }

            @Override
            public void onFailure(String error) {
                view.onHideLoading();
                view.onFailed();
            }

            @Override
            public Map<String, String> requestParam() {
                Map<String, String> requestParam = new HashMap<>();
                requestParam.put("id_booking", id);
                return requestParam;
            }

            @Override
            public Map<String, String> requestHeaders() {
                Map<String, String> header_param = new HashMap<String, String>();
                return header_param;
            }
        });
    }
}
