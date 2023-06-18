package org.techtown.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainScreen2Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Button btnLearnMore;
    Button btnGoToReadyStage;
    private AppDatabase appDatabase;

    RecordAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);

        btnLearnMore = findViewById(R.id.btnLearnMore);
        btnGoToReadyStage = findViewById(R.id.btnGoToReadyStage);

        RecyclerView recyclerView = findViewById(R.id.recent_smoking_log);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecordAdapter 초기화
        adapter = new RecordAdapter(MainScreen2Activity.this);

        //RecyclerView Adapter 설정
        recyclerView.setAdapter(adapter);

        //조회
        loadRecentRecordList();
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

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_smoking_evaluation:
                        // 기본 흡연 평가 화면으로 이동
                        Intent EvalIntent = new Intent(MainScreen2Activity.this, UserinfoActivity.class);
                        startActivity(EvalIntent);
                        break;
                    case R.id.nav_smoking_diary:
                        // 흡연 일지 화면으로 이동
                        Intent ViewIntent = new Intent(MainScreen2Activity.this, ViewrecordActivity.class);
                        startActivity(ViewIntent);
                        break;
                    case R.id.nav_smoking_plan:
                        // 금연 계획서 확인 화면으로 이동
                        Intent planIntent = new Intent(MainScreen2Activity.this, PlanActivity.class);
                        startActivity(planIntent);
                        break;
                    case R.id.nav_chatbot_consult:
                        // 챗봇 상담 화면으로 이동
                        Intent chatbotIntent = new Intent(MainScreen2Activity.this, ChatbotActivity.class);
                        startActivity(chatbotIntent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }

        });
        ImageButton sidebarButton = findViewById(R.id.btnSidebar);
        sidebarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

    }
    private void loadRecentRecordList() {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<Record> recentRecordList = db.recordDao().getRecentRecords(3);
        adapter.setRecordList(recentRecordList); // 어뎁터에 최근 레코드 리스트 설정
    }
}
