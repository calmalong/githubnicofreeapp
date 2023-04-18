package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EvaluationvalueActivity extends Activity {

    Button btnGather;
    TextView textFinalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_result);


        textFinalScore = (TextView) findViewById(R.id.textFinalScore);
        btnGather = (Button) findViewById(R.id.btnGather);


        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");


        textFinalScore.setText("당신의 니코틴의존도는 " + score +"점 입니다.");



        btnGather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserinfoActivity.class);
                startActivity(intent);

            }
        });

    }
}
