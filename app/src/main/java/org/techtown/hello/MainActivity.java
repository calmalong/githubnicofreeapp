package org.techtown.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6, text7;
    RadioGroup rGroup1, rGroup2, rGroup3, rGroup4, rGroup5, rGroup6, rGroup7;
    RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16;
    Button btnSub;


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

        btnSub = (Button) findViewById(R.id.button1);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(mIntent);

            }
        });

    }
}