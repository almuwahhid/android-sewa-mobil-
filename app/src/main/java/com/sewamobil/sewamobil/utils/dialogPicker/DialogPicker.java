package com.sewamobil.sewamobil.utils.dialogPicker;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sewamobil.sewamobil.R;

import java.util.List;

import lib.almuwahhid.utils.DialogBuilder;

public class DialogPicker extends DialogBuilder {
    ImageView img_close;
    TextView tv_title;
    RecyclerView rv;
    RelativeLayout lay_dialog;

    AdapterDialogPicker adapterDialogPicker;
    List<GeneralModel> datas;
    OnDialogPicker onDialogPicker;

    public DialogPicker(Context context, final List<GeneralModel> datas, final OnDialogPicker onDialogPicker) {
        super(context, R.layout.dialog_picker);
        this.datas = datas;
        this.onDialogPicker = onDialogPicker;

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                img_close = dialog.findViewById(R.id.img_close);
                tv_title = dialog.findViewById(R.id.tv_title);
                rv = dialog.findViewById(R.id.rv);
                lay_dialog = dialog.findViewById(R.id.lay_dialog);

                tv_title.setText("Pilih Jaminan");

                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);
                setAnimation(R.style.DialogBottomAnimation);

                adapterDialogPicker = new AdapterDialogPicker(getContext(), datas, new AdapterDialogPicker.OnAdapterDialogPicker() {
                    @Override
                    public void onClickDialogPicker(GeneralModel generalModel) {
                        onDialogPicker.onDialogPick(generalModel);
                        dismiss();
                    }
                });
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(adapterDialogPicker);

            }
        });

        show();
    }

    public interface OnDialogPicker{
        void onDialogPick(GeneralModel generalModel);
    }
}
