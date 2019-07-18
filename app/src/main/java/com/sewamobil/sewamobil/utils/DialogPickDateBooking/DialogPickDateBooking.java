package com.sewamobil.sewamobil.utils.DialogPickDateBooking;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.sewamobil.sewamobil.R;
import com.sewamobil.sewamobil.utils.EventDecorator;

import org.threeten.bp.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lib.almuwahhid.utils.DialogBuilder;
import lib.almuwahhid.utils.LibUi;

public class DialogPickDateBooking extends DialogBuilder {

    MaterialCalendarView calendarView;
    TextView tv_title;
    ImageView img_close;
    RelativeLayout lay_dialog;
    final String DATE_FORMAT = "yyyy-MM-dd";
    String firstDate = "";

    OnDialogPickDateBooking onDialogPickDateBooking;

    public DialogPickDateBooking(Context context, final String title, final PickerDate pickerDate, final OnDialogPickDateBooking onDialogPickDateBooking) {
        super(context, R.layout.dialog_pick_date_booking);
        this.onDialogPickDateBooking = onDialogPickDateBooking;

        initComponent(title, pickerDate);

        show();
    }

    public DialogPickDateBooking(Context context, final String title, String firstDate, final PickerDate pickerDate, final OnDialogPickDateBooking onDialogPickDateBooking) {
        super(context, R.layout.dialog_pick_date_booking);
        this.onDialogPickDateBooking = onDialogPickDateBooking;
        this.firstDate = firstDate;

        initComponent(title, pickerDate);

        show();
    }

    private void initComponent(final String title, final PickerDate pickerDate){
        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                tv_title = dialog.findViewById(R.id.tv_title);
                calendarView = dialog.findViewById(R.id.calendarView);
                img_close = dialog.findViewById(R.id.img_close);
                lay_dialog = dialog.findViewById(R.id.lay_dialog);

                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);
                setAnimation(R.style.DialogBottomAnimation);

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

                tv_title.setText(title);
                if(!firstDate.equals("")){
                    calendarView.state().edit().setMinimumDate(CalendarDay.from(LocalDate.parse(firstDate)));
                } else {
                    calendarView.state().edit().setMinimumDate(CalendarDay.from(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE)));
                }
                calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

                        String month = "";
                        String day = "";
                        if(String.valueOf(calendarDay.getMonth()).length() == 1){
                            month = "0"+calendarDay.getMonth();
                        } else {
                            month = ""+calendarDay.getMonth();
                        }
                        if(String.valueOf(calendarDay.getDay()).length() == 1){
                            day = "0"+calendarDay.getDay();
                        } else {
                            day = ""+calendarDay.getDay();
                        }
                        String date = calendarDay.getYear()+"-"+month+"-"+day;
                        String minimumDate = "";
                        if(!firstDate.equals("")){
                            minimumDate = firstDate;
//                            calendarView.state().edit().setMinimumDate(CalendarDay.from(LocalDate.parse(firstDate)));
                        } else {
                            minimumDate = Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+Calendar.getInstance().get(Calendar.DATE);
//                            calendarView.state().edit().setMinimumDate(CalendarDay.from(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE)));
                        }
//                        minimumDate = calendarView.getMinimumDate().getYear()+"-"+calendarView.getMinimumDate().getMonth()+"-"+calendarView.getMinimumDate().getDay();
                        if(isDateAfterMinimumDate(minimumDate, date)){
                            boolean okToLanjut = true;
                            for (DatePickerModel data :pickerDate.getList()){
                                Log.d("ondateselected", "onDateSelected: "+data.getDate()+" karo "+date+" ikii "+b);
                                if(data.getDate().equals(date)){
                                    okToLanjut = false;
                                }
                            }
                            if(okToLanjut){
                                onDialogPickDateBooking.onDialogPick(date);
                                dismiss();
                            } else {
                                LibUi.ToastShort(getContext(), "Tanggal sudah dibooking, silahkan pilih di tanggal lain");
                            }
                        } else {
                            LibUi.ToastShort(getContext(), "Silahkan tentukan pilihan diatas tanggal tersebut");
                        }
                    }
                });
                setEvent(pickerDate.getList());
            }
        });
    }

    boolean isDateAfterMinimumDate(String d1, String d2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("DialogPickDialogBooking", "isDateAfterMinimumDate#1: "+d1);
        Log.d("DialogPickDialogBooking", "isDateAfterMinimumDate#2: "+d2);
        try {
            Date dateBefore = sdf.parse(d1);
            Date dateAfter = sdf.parse(d2);
            if (dateAfter.compareTo(dateBefore) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    void setEvent(List<DatePickerModel> list) {
        List<CalendarDay> datesGeneral = new ArrayList<>();
        List<CalendarDay> datesFirst = new ArrayList<>();
        List<CalendarDay> datesEnd = new ArrayList<>();
        for (DatePickerModel localDate : list) {

            switch (localDate.getType()){
                case 1:
                    datesGeneral.add(CalendarDay.from(getLocalDate(localDate.getDate())));
                    break;
                case 2:
                    datesFirst.add(CalendarDay.from(getLocalDate(localDate.getDate())));
                    break;
                case 3:
                    datesEnd.add(CalendarDay.from(getLocalDate(localDate.getDate())));
                    break;
            }
        }

        setDecor(datesGeneral, R.drawable.picker_general);
        setDecor(datesFirst, R.drawable.picker_start);
        setDecor(datesEnd, R.drawable.picker_finish);
    }

    LocalDate getLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            Date input = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            return LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH));


        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    void setDecor(List<CalendarDay> calendarDayList, int drawable) {
        calendarView.addDecorators(new EventDecorator(getContext()
                , drawable
                , calendarDayList));
    }

    public interface OnDialogPickDateBooking{
        void onDialogPick(String date);
    }
}
