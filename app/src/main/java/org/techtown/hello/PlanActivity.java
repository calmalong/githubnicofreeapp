package org.techtown.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PlanActivity extends Activity {

    RadioButton rdoDate;
    DatePicker dPicker;
    TextView tvYear, tvMonth, tvDay;
    int selectYear, selectMonth, selectDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

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
    }

}
