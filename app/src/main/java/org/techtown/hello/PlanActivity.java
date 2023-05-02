package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PlanActivity extends Activity {

    Button btnDate, btnRecord, btnMain;
    RadioButton rdoDate;
    DatePicker dPicker;
    TextView startdate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        rdoDate = (RadioButton) findViewById(R.id.rdoDate);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnRecord = (Button) findViewById(R.id.btnRecord);

        dPicker = (DatePicker) findViewById(R.id.dPicker);

        dPicker.setVisibility(View.INVISIBLE);
        btnDate.setVisibility(View.INVISIBLE);

        rdoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPicker.setVisibility(View.VISIBLE);
                btnDate.setVisibility(View.VISIBLE);
            }
        });


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdate.setText(dPicker.getYear() + "년" + dPicker.getMonth() + 1 + "월" + dPicker.getDayOfMonth() + "일");
                dPicker.setVisibility(View.INVISIBLE);
                btnDate.setVisibility(View.INVISIBLE);
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class); // 빌드 에러로 임의로 메인화면에 인텐트함 추후 수정
                startActivity(intent);

            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });
    }

}
