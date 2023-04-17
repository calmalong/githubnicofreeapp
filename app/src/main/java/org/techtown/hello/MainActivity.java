package org.techtown.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6, text7, finalScore;
    RadioGroup rGroup1, rGroup2, rGroup3, rGroup4, rGroup5, rGroup6;
    RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16;
    Button btnResult;

    int score = 0;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;
    int score5 = 0;
    int score6 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.Text1);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.RGroup1);
        r1 = (RadioButton) findViewById(R.id.R1);
        r2 = (RadioButton) findViewById(R.id.R2);
        r3 = (RadioButton) findViewById(R.id.R3);
        r4 = (RadioButton) findViewById(R.id.R4);

        text3 = (TextView) findViewById(R.id.Text3);
        rGroup2 = (RadioGroup) findViewById(R.id.RGroup2);
        r5 = (RadioButton) findViewById(R.id.R5);
        r6 = (RadioButton) findViewById(R.id.R6);

        text4 = (TextView) findViewById(R.id.Text4);
        rGroup3 = (RadioGroup) findViewById(R.id.RGroup3);
        r7 = (RadioButton) findViewById(R.id.R7);
        r8 = (RadioButton) findViewById(R.id.R8);

        text5 = (TextView) findViewById(R.id.Text5);
        rGroup4 = (RadioGroup) findViewById(R.id.RGroup4);
        r9 = (RadioButton) findViewById(R.id.R9);
        r10 = (RadioButton) findViewById(R.id.R10);
        r11 = (RadioButton) findViewById(R.id.R11);
        r12 = (RadioButton) findViewById(R.id.R12);

        text6 = (TextView) findViewById(R.id.Text6);
        rGroup5 = (RadioGroup) findViewById(R.id.RGroup5);
        r13 = (RadioButton) findViewById(R.id.R13);
        r14 = (RadioButton) findViewById(R.id.R14);

        text7 = (TextView) findViewById(R.id.Text7);
        rGroup6 = (RadioGroup) findViewById(R.id.RGroup6);
        r15 = (RadioButton) findViewById(R.id.R15);
        r16 = (RadioButton) findViewById(R.id.R16);

        btnResult = (Button) findViewById(R.id.btnResult);
        finalScore = (TextView) findViewById(R.id.textFinalScore);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EvaluationvalueActivity.class);
                if (r1.isChecked()) {
                    score1 = 3;
                } else if (r2.isChecked()) {
                    score1 = 2;
                } else if (r3.isChecked()) {
                    score1 = 1;
                } else {
                    score1 = 0;
                }

                if (r5.isChecked()) {
                    score2 = 1;
                } else {
                    score2 = 0;
                }

                if (r7.isChecked()) {
                    score3 = 1;
                } else {
                    score3 = 0;
                }

                if (r9.isChecked()) {
                    score4 = 0;
                } else if (r10.isChecked()) {
                    score4 = 1;
                } else if (r11.isChecked()) {
                    score4 = 2;
                } else {
                    score4 = 3;
                }

                if (r13.isChecked()) {
                    score5 = 1;
                } else {
                    score5 = 0;
                }

                if (r15.isChecked()) {
                    score6 = 1;
                } else {
                    score6 = 0;
                }

                score = score1 + score2 + score3 + score4 + score5 + score6;


                String thescore = String.valueOf(score);
                Intent myIntent = new Intent(MainActivity.this, EvaluationvalueActivity.class);
                myIntent.putExtra("score", thescore);

                startActivity(intent);
                startActivity(myIntent);
            }
        });


    }
}