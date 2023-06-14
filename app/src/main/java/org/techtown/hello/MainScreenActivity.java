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

        currentStage = findViewById(R.id.current_stage);
        currentStage2 = findViewById(R.id.current_stage2);
        messageBox = findViewById(R.id.message_box);
        appDatabase = AppDatabase.getDBInstance(this);

        RecyclerView recyclerView = findViewById(R.id.recent_smoking_log);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecordAdapter 초기화
        adapter = new RecordAdapter(MainScreenActivity.this);

        //RecyclerView Adapter 설정
        recyclerView.setAdapter(adapter);

        //조회
        loadRecentRecordList();

        String selectedDate = getIntent().getStringExtra("startdate");
        String stage = getIntent().getStringExtra("stage");

        if (selectedDate != null && !selectedDate.isEmpty()) {
            // 선택된 날짜를 가져와서 출력
            currentStage.setText(selectedDate);
        } else {
            // 데이터베이스에서 날짜 가져와서 출력
            getCurrentStageFromDatabase();
        }

        if (stage != null && !stage.isEmpty()) {
            currentStage2.setText("현재 " + stage + "중입니다!");
        } else {
            getCurrentStage2FromDatabase();
        }


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
                    case R.id.nav_chatbot_info:
                        // 챗봇 정보 제공 화면으로 이동
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
        updateMessageBox();
    }

    protected void onResume() {
        super.onResume();
        updateMessageBox();
    }

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

    private void getCurrentStageFromDatabase() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HomeEntity entity = appDatabase.homeDao().getCurrentStage();
                if (entity != null) {
                    String startDate = entity.startdate;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentStage.setText(startDate);
                        }
                    });
                }
            }
        });
    }

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

    private void loadRecentRecordList() {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<Record> recentRecordList = db.recordDao().getRecentRecords(3);
        adapter.setRecordList(recentRecordList); // 어뎁터에 최근 레코드 리스트 설정
    }
}
