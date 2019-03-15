package com.sewamobil.sewamobil.menu.wisata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.wisata.Model.WisataModel;

import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.Holder>  {

    Context context;
    List<WisataModel> modelList;
    OnWisataAdapter onWisataAdapter;

    public WisataAdapter(Context context, List<WisataModel> modelList, OnWisataAdapter onWisataAdapter) {
        this.context = context;
        this.modelList = modelList;
        this.onWisataAdapter = onWisataAdapter;
    }

    @NonNull
    @Override
    public WisataAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wisata, parent, false);
        WisataAdapter.Holder rcv = new WisataAdapter.Holder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull WisataAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnWisataAdapter{
        void onWisataClick(WisataModel model);
    }
}
