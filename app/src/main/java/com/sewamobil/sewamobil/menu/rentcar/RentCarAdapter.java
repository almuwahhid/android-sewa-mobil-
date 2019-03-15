package com.sewamobil.sewamobil.menu.rentcar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.rentcar.Model.RentCarModel;
import com.sewamobil.sewamobil.utils.Functions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentCarAdapter extends RecyclerView.Adapter<RentCarAdapter.Holder> {
    Context context;
    List<RentCarModel> models;
    OnRentCarAdapter onRentCarAdapter;

    public RentCarAdapter(Context context, List<RentCarModel> models, OnRentCarAdapter onRentCarAdapter) {
        this.context = context;
        this.onRentCarAdapter = onRentCarAdapter;
        this.models = models;
    }

    @NonNull
    @Override
    public RentCarAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView;
        layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_rent_car, viewGroup, false);
        RentCarAdapter.Holder rcv = new RentCarAdapter.Holder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull RentCarAdapter.Holder holder, int i) {
        final RentCarModel model = models.get(i);
        holder.tv_name.setText(model.getName());
        holder.tv_jenis.setText(model.getNama_jenis());
        holder.tv_price.setText(Functions.rupiahFormat(model.getPrice()));
        if(model.getStatus() == 0){
            holder.lay_book.setVisibility(View.GONE);
            holder.lay_empty.setVisibility(View.VISIBLE);
        } else {
            holder.lay_empty.setVisibility(View.GONE);
            holder.lay_book.setVisibility(View.VISIBLE);
        }
        holder.lay_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRentCarAdapter.onBookClicked(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_jenis)
        TextView tv_jenis;
        @BindView(R.id.lay_book)
        LinearLayout lay_book;
        @BindView(R.id.lay_empty)
        LinearLayout lay_empty;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface OnRentCarAdapter{
        void onBookClicked(RentCarModel model);
    }
}
