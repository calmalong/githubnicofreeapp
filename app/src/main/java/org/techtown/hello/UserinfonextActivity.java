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


    Button btnAnalyze;
    EditText edtunxt46, edtunxt51;
    RadioGroup RGroup3;
    RadioButton R10;
    RadioButton R11;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfonext);

        btnAnalyze = (Button) findViewById(R.id.btnAnalyze);
        edtunxt46 = (EditText) findViewById(R.id.edtunxt46);
        edtunxt51 = (EditText) findViewById(R.id.edtunxt51);
        RGroup3 = (RadioGroup) findViewById(R.id.RGroup3);
        R10 = (RadioButton) findViewById(R.id.R10);
        R11 = (RadioButton) findViewById(R.id.R11);


        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stage = "";
                String inputText = edtunxt46.getText().toString();
                int numberOfAttempts = 0;
                if (!inputText.isEmpty()) {
                    numberOfAttempts = Integer.parseInt(inputText);
                }

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

                Toast.makeText(getApplicationContext(), stage, Toast.LENGTH_SHORT).show();

                // HomeEntity 생성 및 데이터 저장
                HomeEntity homeEntity = new HomeEntity();
                homeEntity.stage = stage;
                homeEntity.msgbox = edtunxt51.getText().toString();
                AppDatabase.getDBInstance(getApplicationContext()).homeDao().insert(homeEntity);

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
