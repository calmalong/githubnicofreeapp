package org.techtown.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainScreen2Activity extends AppCompatActivity {

    Button btnLearnMore;
    Button btnGoToReadyStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);

        btnLearnMore = findViewById(R.id.btnLearnMore);
        btnGoToReadyStage = findViewById(R.id.btnGoToReadyStage);

        btnLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageScrollActivity.class);
                startActivity(intent);
            }
        });

        btnGoToReadyStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                startActivity(intent);
            }
        });
    }
}
