package org.techtown.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ImageButton sidebarButton = findViewById(R.id.sidebarbutton);
        sidebarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainScreenActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());

                // 팝업 메뉴 아이템 클릭 리스너 설정
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_1:
                                // 페이지 이동 예: 흡연 일지
                                break;
                            case R.id.menu_item_2:
                                // 페이지 이동 예: 금연 통계
                                break;
                            case R.id.menu_item_3:
                                // 페이지 이동 예: 자기 보상
                                break;
                            case R.id.menu_item_4:
                                // 페이지 이동 예: 금연 계획서 확인
                                break;
                            case R.id.menu_item_5:
                                // 페이지 이동 예: 챗봇 도움
                                break;
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}
