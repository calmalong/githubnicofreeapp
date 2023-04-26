package org.techtown.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PlanActivity extends Activity {

    Button btnDate;
    RadioButton rdoDate;
    DatePicker dPicker;
    TextView tvYear, tvMonth, tvDay;
    int selectYear, selectMonth, selectDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        btnDate = (Button) findViewById(R.id.btnDate);

        rdoDate = (RadioButton) findViewById(R.id.rdoDate);

        dPicker =  (DatePicker) findViewById(R.id.dPicker);

        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);

        dPicker.setVisibility(View.INVISIBLE);

        dPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPicker.setVisibility(View.VISIBLE);
            }
        });

        dPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectYear = year;
                selectMonth = monthOfYear+1;
                selectDate = dayOfMonth;
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPicker.setVisibility(View.INVISIBLE);
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDate));}
        });
    }

}
