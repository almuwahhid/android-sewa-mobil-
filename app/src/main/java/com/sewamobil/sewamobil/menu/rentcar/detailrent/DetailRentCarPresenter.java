package com.sewamobil.sewamobil.menu.rentcar.detailrent;

import android.content.Context;

import com.sewamobil.sewamobil.utils.BasePresenter;
import com.sewamobil.sewamobil.utils.Endpoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.almuwahhid.utils.LibConstant;
import lib.almuwahhid.utils.UiLibRequest;

public class DetailRentCarPresenter extends BasePresenter implements DetailRentCarInterface.Presenter{
    Context context;
    DetailRentCarInterface.View view;

    public DetailRentCarPresenter(Context context, DetailRentCarInterface.View view) {
        super(context);
        this.context = context;
        this.view = view;
    }

    @Override
    public void bookingKendaraan(HashMap<String, String> params) {

    }

    @Override
    public void requestListBookingActive(final String id) {
        UiLibRequest.POST(Endpoints.stringGetBookingDate(), getContext(), new UiLibRequest.OnPostRequest() {
            @Override
            public void onPreExecuted() {
                view.onLoading();
            }

            @Override
            public void onSuccess(JSONObject response) {
                view.onHideLoading();
                try {
                    List<String> list = new ArrayList<>();
                    if(response.getString("result").equals("success")){
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            list.add(String.valueOf(data.get(i)));
                        }
                        view.onRequestListBookingActive(list);
                    }else {
                        view.onRequestListBookingActive(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.onRequestListBookingActive(new ArrayList<String>());
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
                requestParam.put("id", id);
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
