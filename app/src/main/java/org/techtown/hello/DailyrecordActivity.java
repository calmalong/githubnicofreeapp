package org.techtown.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DailyrecordActivity extends AppCompatActivity {

    EditText today, situation, feeling;
    Button insertBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyrecord);

        today = findViewById(R.id.today);
        situation = findViewById(R.id.situation);
        feeling = findViewById(R.id.feeling);
        insertBtn = findViewById(R.id.btnSave);



        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Situation = situation.getText().toString();
                String Feeling = feeling.getText().toString();

            }
        });
    }

    private void insertRecord (String situation, String feeling) {

        Record record = new Record();
        record.userSituation = situation;
        record.userFeeling = feeling;

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        db.recordDao().insertRecord(record);

        setResult(Activity.RESULT_OK);

        finish();

    }
}