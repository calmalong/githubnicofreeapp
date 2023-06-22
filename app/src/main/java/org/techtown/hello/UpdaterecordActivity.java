package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdaterecordActivity extends AppCompatActivity {

    EditText upSituationEdit, upFeelingEdit;
    int uId;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterecord);

        //초기화
        upSituationEdit = findViewById(R.id.up_situation);
        upFeelingEdit = findViewById(R.id.up_feeling);
        btnUpdate = findViewById(R.id.btnUpdate);

        // 이전 화면에서 전달받은 값 가져오기
        String situation = getIntent().getStringExtra("userSituation");
        String feeling = getIntent().getStringExtra("userFeeling");
        uId = getIntent().getIntExtra("uId", 0);

        // 변수 화면에 보여주기
        upSituationEdit.setText(situation);
        upFeelingEdit.setText(feeling);

        // 수정 버튼 이벤트
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // edittext에서 사용자 입력 값 가져오기
                String userSituation = upSituationEdit.getText().toString();
                String userFeeling = upFeelingEdit.getText().toString();

                // 업데이트할 레코드 객체 생성 및 값 설정
                Record record = new Record();
                record.uid = uId;
                record.userSituation = userSituation;
                record.userFeeling = userFeeling;

                // 데이터베이스 인스턴스 가져오기
                AppDatabase db = AppDatabase.getDBInstance(UpdaterecordActivity.this);
                db.recordDao().updateRecord(record);

                // ViewrecordActivity로 이동하는 인텐트 생성 및 시작
                Intent intent = new Intent(UpdaterecordActivity.this, ViewrecordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}