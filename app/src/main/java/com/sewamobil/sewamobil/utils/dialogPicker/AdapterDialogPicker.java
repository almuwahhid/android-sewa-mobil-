package com.sewamobil.sewamobil.utils.dialogPicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sewamobil.sewamobil.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDialogPicker extends RecyclerView.Adapter<AdapterDialogPicker.Holder> {

    Context context;
    List<GeneralModel> list;
    OnAdapterDialogPicker onAdapterDialogPicker;

    public AdapterDialogPicker(Context context, List<GeneralModel> list, OnAdapterDialogPicker onAdapterDialogPicker) {
        this.context = context;
        this.list = list;
        this.onAdapterDialogPicker = onAdapterDialogPicker;
    }

    @NonNull
    @Override
    public AdapterDialogPicker.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_picker, parent, false);
        AdapterDialogPicker.Holder rcv = new AdapterDialogPicker.Holder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDialogPicker.Holder holder, final int position) {
        holder.tv_picker.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdapterDialogPicker.onClickDialogPicker(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_picker)
        TextView tv_picker;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnAdapterDialogPicker{
        void onClickDialogPicker(GeneralModel generalModel);
    }
}
