package org.techtown.hello;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private TextView currentStage, currentStage2, messageBox;
    private AppDatabase appDatabase;

    RecordAdapter adapter;
    private static final int REQUEST_CODE_USER_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        currentStage2 = findViewById(R.id.current_stage2);
        messageBox = findViewById(R.id.message_box);
        appDatabase = AppDatabase.getDBInstance(this);

        // RecyclerView 설정
        RecyclerView recyclerView = findViewById(R.id.recent_smoking_log);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecordAdapter 초기화
        adapter = new RecordAdapter(MainScreenActivity.this);

        //RecyclerView Adapter 설정
        recyclerView.setAdapter(adapter);

        // 최근 기록 조회
        loadRecentRecordList();

        // 인텐트에서 전달받은 데이터 출력
        String selectedDate = getIntent().getStringExtra("startdate");
        String stage = getIntent().getStringExtra("stage");

        if (selectedDate != null && !selectedDate.isEmpty()) {
            // 선택된 날짜를 가져와서 출력
            currentStage.setText(selectedDate);
        }

        if (stage != null && !stage.isEmpty()) {
            currentStage2.setText("현재 " + stage + "중입니다!");
        } else {
            getCurrentStage2FromDatabase();
        }

        // Navigation Drawer 설정
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // ActionBarDrawerToggle 설정
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView의 메뉴 아이템 클릭 시, 해당 화면으로 이동
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_smoking_evaluation:
                        // 기본 흡연 평가 화면으로 이동
                        Intent EvalIntent = new Intent(MainScreenActivity.this, UserinfoActivity.class);
                        startActivity(EvalIntent);
                        break;
                    case R.id.nav_smoking_diary:
                        // 흡연 일지 화면으로 이동
                        Intent ViewIntent = new Intent(MainScreenActivity.this, ViewrecordActivity.class);
                        startActivity(ViewIntent);
                        break;
                    case R.id.nav_smoking_plan:
                        // 금연 계획서 확인 화면으로 이동
                        Intent planIntent = new Intent(MainScreenActivity.this, PlanActivity.class);
                        startActivity(planIntent);
                        break;
                    case R.id.nav_chatbot_consult:
                        // 챗봇 상담 화면으로 이동
                        Intent chatbotIntent = new Intent(MainScreenActivity.this, ChatbotActivity.class);
                        startActivity(chatbotIntent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }

        });
        // 사이드바 버튼 클릭 시, Navigation Drawer 열기
        ImageButton sidebarButton = findViewById(R.id.btnSidebar);
        sidebarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        // 메시지 박스 업데이트
        updateMessageBox();
    }

    protected void onResume() {
        super.onResume();
        updateMessageBox();
    }

    // 메시지 박스 업데이트 기능
    private void updateMessageBox() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HomeEntity entity = appDatabase.homeDao().getCurrentStage();
                if (entity != null) {
                    final String message = entity.msgbox;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            messageBox.setText(message);
                        }
                    });
                }
            }
        });
    }


    // 데이터베이스에서 현재 단계 가져오기
    private void getCurrentStage2FromDatabase() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HomeEntity entity = appDatabase.homeDao().getCurrentStage();
                if (entity != null) {
                    String stage = entity.stage;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentStage2.setText("현재 " + stage + "중입니다!");
                        }
                    });
                }
            }
        });
    }

    // 결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_USER_INFO && resultCode == RESULT_OK) {
            String messageBoxText = data.getStringExtra("messageBoxText");
            if (messageBoxText != null) {
                messageBox.setText(messageBoxText);
            }
        }
    }

    // 최근 기록 조회
    private void loadRecentRecordList() {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<Record> recentRecordList = db.recordDao().getRecentRecords(3);
        adapter.setRecordList(recentRecordList); // 어뎁터에 최근 레코드 리스트 설정
    }
}
