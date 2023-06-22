package org.techtown.hello;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

        // RadioButton 클릭 이벤트 처리
        rdoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPicker.setVisibility(View.VISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
            }
        });


        // btnRecord 클릭 이벤트 처ㅣ
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택한 날짜 정보 가져오기기
               int year = dPicker.getYear();
                int month = dPicker.getMonth() + 1;
                int dayOfMonth = dPicker.getDayOfMonth();

                // 선택한 날짜를 텍스트뷰에 설정
                String selectedDate = year + "년 " + month + "월 " + dayOfMonth + "일 ";
                startdate.setText(selectedDate);
            }
        });

        // bynCopy 클릭 이벤트 처리
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트를 클립보드에 복사함
                String textToCopy = shareText.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(PlanActivity.this, "텍스트가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // btnHome 클릭 이벤트 처리
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택한 날짜를 DB에 저장하고 메인화면으로
                int year = dPicker.getYear();
                int month = dPicker.getMonth() + 1;
                int dayOfMonth = dPicker.getDayOfMonth();

                String selectedDate = year + "년 " + month + "월 " + dayOfMonth + "일 ";

                // DB에 엔터티 저장
                AppDatabase appDatabase = AppDatabase.getDBInstance(PlanActivity.this);
                HomeEntity entity = new HomeEntity();
                entity.startdate = selectedDate;
                insertEntity(entity, appDatabase);
            }
        });

    }

    // 엔터티를 DB에 삽입하는 메서드
    private void insertEntity(final HomeEntity entity, final AppDatabase appDatabase) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // 엔터티 삽입
                appDatabase.homeDao().insert(entity);

                // D-Day 계산
                String dDay = calculateDDay(entity.startdate);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                        intent.putExtra("dDay", dDay);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    // D-Day 계산 메서드
    private String calculateDDay(String startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault());
        try {
            Date startDateFormatted = sdf.parse(startDate);
            Date currentDate = new Date();

            // 현재 날짜와 선택한 날짜의 차이 계산
            long diffInMillies = Math.abs(currentDate.getTime() - startDateFormatted.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            // 현재 날짜에 따라 D-day 표시
            if (currentDate.after(startDateFormatted)) {
                return "D+" + diff;
            } else {
                return "D-" + diff;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}