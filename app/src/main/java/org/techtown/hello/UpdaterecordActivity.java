package org.techtown.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdaterecordActivity extends AppCompatActivity {

    EditText upSituationEdit, upFeelingEdit;
    Button btnUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterecord);

        //초기화
        upSituationEdit = findViewById(R.id.up_situation);
        upFeelingEdit = findViewById(R.id.up_feeling);
        btnUpdate = findViewById(R.id.btnUpdate);

        String situation = getIntent().getStringExtra("userSituation");
        String feeling = getIntent().getStringExtra("userFeeling");

        upSituationEdit.setText(situation);
        upFeelingEdit.setText(feeling);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userSituation = upSituationEdit.getText().toString();
                String userFeeling = upFeelingEdit.getText().toString();

                Record record = new Record();
                record.userSituation = userSituation;
                record.userFeeling = userFeeling;

                AppDatabase db = AppDatabase.getDBInstance(UpdaterecordActivity.this);

                db.recordDao().recordUpdate(record);

                Intent intent = new Intent(UpdaterecordActivity.this, ViewrecordActivity.class);
                startActivity(intent);

                finish();


            }
        });
    }
}