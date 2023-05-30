package org.techtown.hello;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PlanActivity extends Activity {

    Button btnRecord, btnHome, btnCopy;
    RadioButton rdoDate;
    DatePicker dPicker;
    TextView startdate, shareText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        rdoDate = (RadioButton) findViewById(R.id.rdoDate);
        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnHome = (Button) findViewById(R.id.btnHome);
        dPicker = (DatePicker) findViewById(R.id.dPicker);
        startdate = findViewById(R.id.startdate);
        shareText = findViewById(R.id.shareText);
        btnCopy = findViewById(R.id.btnCopy);


        dPicker.setVisibility(View.INVISIBLE);
        btnRecord.setVisibility(View.INVISIBLE);

        rdoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPicker.setVisibility(View.VISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
            }
        });


        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = dPicker.getYear();
                int month = dPicker.getMonth() + 1;
                int dayOfMonth = dPicker.getDayOfMonth();

                String selectedDate = year + "년 " + month + "월 " + dayOfMonth + "일 ";
                startdate.setText(selectedDate);
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = shareText.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(PlanActivity.this, "텍스트가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class); // 빌드 에러로 임의로 메인화면에 인텐트함 추후 수정
                startActivity(intent);
                finish();
            }
        });

    }

}
