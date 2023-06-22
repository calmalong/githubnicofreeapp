package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UserinfoActivity extends Activity {

    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, finalScore;
    RadioGroup rGroup1, rGroup2, rGroup3, rGroup4, rGroup5, rGroup6, rGroup7, rGroup8, rGroup9, rGroup10;
    RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25,
            r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50;

    Button btnNextpage;

    int score = 0;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;
    int score5 = 0;
    int score6 = 0;
    int score7 = 0;

    int score8 = 0;

    int score9 = 0;

    int score10 = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);


        text1 = (TextView) findViewById(R.id.text1);

        text2 = (TextView) findViewById(R.id.text2);
        rGroup1 = (RadioGroup) findViewById(R.id.RGroup1);
        r1 = (RadioButton) findViewById(R.id.R1);
        r2 = (RadioButton) findViewById(R.id.R2);
        r3 = (RadioButton) findViewById(R.id.R3);
        r4 = (RadioButton) findViewById(R.id.R4);
        r5 = (RadioButton) findViewById(R.id.R5);

        text3 = (TextView) findViewById(R.id.text3);
        rGroup2 = (RadioGroup) findViewById(R.id.RGroup2);
        r6 = (RadioButton) findViewById(R.id.R6);
        r7 = (RadioButton) findViewById(R.id.R7);
        r8 = (RadioButton) findViewById(R.id.R8);
        r9 = (RadioButton) findViewById(R.id.R9);
        r10 = (RadioButton) findViewById(R.id.R10);

        text4 = (TextView) findViewById(R.id.text4);
        rGroup3 = (RadioGroup) findViewById(R.id.RGroup3);
        r11 = (RadioButton) findViewById(R.id.R11);
        r12 = (RadioButton) findViewById(R.id.R12);
        r13 = (RadioButton) findViewById(R.id.R13);
        r14 = (RadioButton) findViewById(R.id.R14);
        r15 = (RadioButton) findViewById(R.id.R15);

        text5 = (TextView) findViewById(R.id.text5);
        rGroup4 = (RadioGroup) findViewById(R.id.RGroup4);
        r16 = (RadioButton) findViewById(R.id.R16);
        r17 = (RadioButton) findViewById(R.id.R17);
        r18 = (RadioButton) findViewById(R.id.R18);
        r19 = (RadioButton) findViewById(R.id.R19);
        r20 = (RadioButton) findViewById(R.id.R20);

        text6 = (TextView) findViewById(R.id.text6);
        rGroup5 = (RadioGroup) findViewById(R.id.RGroup5);
        r21 = (RadioButton) findViewById(R.id.R21);
        r22 = (RadioButton) findViewById(R.id.R22);
        r23 = (RadioButton) findViewById(R.id.R23);
        r24 = (RadioButton) findViewById(R.id.R24);
        r25 = (RadioButton) findViewById(R.id.R25);

        text7 = (TextView) findViewById(R.id.text7);
        rGroup6 = (RadioGroup) findViewById(R.id.RGroup6);
        r26 = (RadioButton) findViewById(R.id.R26);
        r27 = (RadioButton) findViewById(R.id.R27);
        r28 = (RadioButton) findViewById(R.id.R28);
        r29 = (RadioButton) findViewById(R.id.R29);
        r30 = (RadioButton) findViewById(R.id.R30);

        text8 = (TextView) findViewById(R.id.text8);
        rGroup7 = (RadioGroup) findViewById(R.id.RGroup7);
        r31 = (RadioButton) findViewById(R.id.R31);
        r32 = (RadioButton) findViewById(R.id.R32);
        r33 = (RadioButton) findViewById(R.id.R33);
        r34 = (RadioButton) findViewById(R.id.R34);
        r35 = (RadioButton) findViewById(R.id.R35);

        text9 = (TextView) findViewById(R.id.text9);
        rGroup8 = (RadioGroup) findViewById(R.id.RGroup8);
        r36 = (RadioButton) findViewById(R.id.R36);
        r37 = (RadioButton) findViewById(R.id.R37);
        r38 = (RadioButton) findViewById(R.id.R38);
        r39 = (RadioButton) findViewById(R.id.R39);
        r40 = (RadioButton) findViewById(R.id.R40);

        text10 = (TextView) findViewById(R.id.text10);
        rGroup9 = (RadioGroup) findViewById(R.id.RGroup9);
        r41 = (RadioButton) findViewById(R.id.R41);
        r42 = (RadioButton) findViewById(R.id.R42);
        r43 = (RadioButton) findViewById(R.id.R43);
        r44 = (RadioButton) findViewById(R.id.R44);
        r45 = (RadioButton) findViewById(R.id.R45);

        text11 = (TextView) findViewById(R.id.text11);
        rGroup10 = (RadioGroup) findViewById(R.id.RGroup10);
        r46 = (RadioButton) findViewById(R.id.R46);
        r47 = (RadioButton) findViewById(R.id.R47);
        r48 = (RadioButton) findViewById(R.id.R48);
        r49 = (RadioButton) findViewById(R.id.R49);
        r50 = (RadioButton) findViewById(R.id.R50);


        btnNextpage = (Button) findViewById(R.id.btnNextpage);

        // 다음 페이지로 버튼을 클릭했을 때 동작함
        btnNextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UserinfonextActivity.class);

                // 각 버튼 상태에 따라 해당하는 점수를 설정
                if (r1.isChecked()) {
                    score1 = 1;
                } else if (r2.isChecked()){
                    score1 = 2;
                } else if (r3.isChecked()) {
                    score1 = 3;
                } else if (r4.isChecked()) {
                    score1 = 4;
                } else if (r5.isChecked()) {
                    score1 = 5;
                }

                if (r6.isChecked()) {
                    score2 = 1;
                } else if (r7.isChecked()){
                    score2 = 2;
                } else if (r8.isChecked()) {
                    score2 = 3;
                } else if (r9.isChecked()) {
                    score2 = 4;
                } else if (r10.isChecked()) {
                    score2 = 5;
                }

                if (r11.isChecked()) {
                    score3 = 1;
                } else if (r12.isChecked()){
                    score3 = 2;
                } else if (r13.isChecked()) {
                    score3 = 3;
                } else if (r14.isChecked()) {
                    score3 = 4;
                } else if (r15.isChecked()){
                    score3 = 5;
                }

                if (r16.isChecked()) {
                    score4 = 1;
                } else if (r17.isChecked()){
                    score4 = 2;
                } else if (r18.isChecked()) {
                    score4 = 3;
                } else if (r19.isChecked()) {
                    score4 = 4;
                } else if (r20.isChecked()) {
                    score4 = 5;
                }

                if (r21.isChecked()) {
                    score5 = 1;
                } else if (r22.isChecked()){
                    score5 = 2;
                } else if (r23.isChecked()) {
                    score5 = 3;
                } else if (r24.isChecked()) {
                    score5 = 4;
                } else if (r25.isChecked()) {
                    score5 = 5;
                }

                if (r26.isChecked()) {
                    score6 = 1;
                } else if (r27.isChecked()){
                    score6 = 2;
                } else if (r28.isChecked()) {
                    score6 = 3;
                } else if (r29.isChecked()) {
                    score6 = 4;
                } else if (r30.isChecked()){
                    score6 = 5;
                }

                if (r31.isChecked()) {
                    score7 = 5;
                } else if (r32.isChecked()){
                    score7 = 4;
                } else if (r33.isChecked()) {
                    score7 = 3;
                } else if (r34.isChecked()) {
                    score7 = 2;
                } else if (r35.isChecked()) {
                    score7 = 1;
                }

                if (r36.isChecked()) {
                    score8 = 5;
                } else if (r37.isChecked()){
                    score8 = 4;
                } else if (r38.isChecked()) {
                    score8 = 3;
                } else if (r39.isChecked()) {
                    score8 = 2;
                } else if (r40.isChecked()) {
                    score8 = 1;
                }

                if (r41.isChecked()) {
                    score9 = 1;
                } else if (r42.isChecked()){
                    score9 = 2;
                } else if (r43.isChecked()) {
                    score9 = 3;
                } else if (r44.isChecked()) {
                    score9 = 4;
                } else if (r45.isChecked()){
                    score9 = 5;
                }

                if (r46.isChecked()) {
                    score10 = 5;
                } else if (r47.isChecked()){
                    score10 = 4;
                } else if (r48.isChecked()) {
                    score10 = 3;
                } else if (r49.isChecked()) {
                    score10 = 2;
                } else if (r50.isChecked()){
                    score10 = 1;
                }


                // 전체 점수 계산
                score = score1 + score2 + score3 + score4 + score5 + score6 + score7 + score8 + score9 + score10;

                Toast.makeText(getApplicationContext(), "당신의 점수는: " + score + "/50", Toast.LENGTH_LONG).show();


                // 점수를 UserinfonextActivity 전달하기 위해 인텐트에 추가
                String thescore = String.valueOf(score);
                Intent myIntent = new Intent(UserinfoActivity.this, UserinfonextActivity.class);
                myIntent.putExtra("score", thescore);

                startActivity(intent);
                startActivity(myIntent);

            }

        });
    }
}
