package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class UserinfonextActivity extends Activity {


    Button btnRecord, btnAnalyze;
    EditText edtunxt46, edtunxt51;
    RadioGroup RGroup3;
    RadioButton R10;
    RadioButton R11;

    String stage = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfonext);

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnAnalyze = (Button) findViewById(R.id.btnAnalyze);
        edtunxt46 = (EditText) findViewById(R.id.edtunxt46);
        edtunxt51 = (EditText) findViewById(R.id.edtunxt51);
        RGroup3 = (RadioGroup) findViewById(R.id.RGroup3);
        R10 = (RadioButton) findViewById(R.id.R10);
        R11 = (RadioButton) findViewById(R.id.R11);


        // 저장하기 버튼 클릭시 동작
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = edtunxt46.getText().toString();
                int numberOfAttempts = 0;
                if (!inputText.isEmpty()) {
                    numberOfAttempts = Integer.parseInt(inputText);
                }

                // 시도 횟수에 따라 해당하는 단계 (stage)를 설정합니다.
                if (numberOfAttempts == 0) {
                    stage = "숙고";
                } else {
                    if (RGroup3.getCheckedRadioButtonId() == -1) {
                        stage = "준비";
                    } else if (R10.isChecked()) {
                        stage = "실행";
                    } else if (R11.isChecked()) {
                        stage = "유지";
                    }
                }

                String messageBoxText = edtunxt51.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("messageBoxText", messageBoxText);
                setResult(RESULT_OK, intent);

                Toast.makeText(getApplicationContext(), "성공적으로 저장되었습니다!", Toast.LENGTH_SHORT).show();

                // HomeEntity 생성 및 데이터 저장
                HomeEntity homeEntity = new HomeEntity();
                homeEntity.stage = stage;
                homeEntity.msgbox = messageBoxText;
                AppDatabase.getDBInstance(getApplicationContext()).homeDao().insert(homeEntity);
            }
        });

        // 분석하기 버튼 클릭시 동작
        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if (stage.equals("숙고")) {
                    intent = new Intent(getApplicationContext(), MainScreen2Activity.class);
                } else if (stage.equals("준비")) {
                    intent = new Intent(getApplicationContext(), PlanActivity.class);
                } else if (stage.equals("실행")) {
                    intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                }
                startActivity(intent);

            }
        });
    }
}
