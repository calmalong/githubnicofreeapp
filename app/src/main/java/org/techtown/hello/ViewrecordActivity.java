package org.techtown.hello;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ViewrecordActivity extends AppCompatActivity {
    RecordAdapter adapter;
    List<Record> recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrecord);

        FloatingActionButton fab = findViewById(R.id.btnAddrecord);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewrecordActivity.this, DailyrecordActivity.class);
                activityResult.launch(intent);
            }
        });

        //RecyclerView 초기화 및 설정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //RecordAdapter 초기화
        adapter = new RecordAdapter(ViewrecordActivity.this);

        //RecyclerView Adapter 설정
        recyclerView.setAdapter(adapter);

        //조회
        loadRecordList();

        // 삭제 제스처
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getBindingAdapterPosition();
                switch(direction){
                    case ItemTouchHelper.LEFT:

                        //레코드 클래스 생성
                        Record record = new Record();
                        record.uid = recordList.get(position).uid;

                        // 아이템 삭제
                        adapter.deleteRecord(position);

                        // 아이템 삭제 화면 재정리
                        adapter.notifyItemRemoved(position);

                        // DB
                        AppDatabase db = AppDatabase.getDBInstance(getApplicationContext());

                        //삭제 쿼리
                        db.recordDao().deleteRecord(record);

                        break;
                }
            }

            // 제스처 그림 구현
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.RED)
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .addSwipeLeftLabel("삭제")
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }

   // 액티비티 백그라운드에 있는데 호출되면 실행
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        loadRecordList();
    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == RESULT_OK) {

                        loadRecordList();
                    }
                }
            }
    );

    private void loadRecordList() {
        AppDatabase db = AppDatabase.getDBInstance((this.getApplicationContext()));

        List<Record> recordList = db.recordDao().getAllRecord();
        adapter.setRecordList(recordList);
    }
}
