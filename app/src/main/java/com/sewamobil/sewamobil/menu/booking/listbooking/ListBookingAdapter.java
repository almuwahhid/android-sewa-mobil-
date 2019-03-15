package com.sewamobil.sewamobil.menu.booking.listbooking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.menu.booking.Model.BookingModel;

import java.util.List;

public class ListBookingAdapter extends RecyclerView.Adapter<ListBookingAdapter.Holder> {
    List<BookingModel> bookingModels;
    OnListBookingAdapter onListBookingAdapter;

    public ListBookingAdapter(List<BookingModel> bookingModels, OnListBookingAdapter onListBookingAdapter) {
        this.bookingModels = bookingModels;
        this.onListBookingAdapter = onListBookingAdapter;
    }

    @NonNull
    @Override
    public ListBookingAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_booking, parent, false);
        ListBookingAdapter.Holder rcv = new ListBookingAdapter.Holder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookingAdapter.Holder holder, int position) {
        final BookingModel bookingModel = bookingModels.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListBookingAdapter.onListClick(bookingModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    interface OnListBookingAdapter{
        void onListClick(BookingModel model);
    }
}
