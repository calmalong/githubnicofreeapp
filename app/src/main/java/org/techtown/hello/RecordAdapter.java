package org.techtown.hello;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    List<Record> recordList; // 레코트 데이터 보관 리스트
    Context context;

    public RecordAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        // 각 아이템의 레이아웃을 인플레이션해서 ViewHolder 생성
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int mposition = holder.getBindingAdapterPosition();
        // 상황과 기분 텍스트뷰에 값 설정
        holder.situationText.setText(recordList.get(position).userSituation);
        holder.feelingText.setText(recordList.get(position).userFeeling);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // updateRecordActivity를 시작하기 위한 인텐트 생성
                Intent intent = new Intent(context, UpdaterecordActivity.class);
                intent.putExtra("uId", recordList.get(mposition).uid);
                intent.putExtra("userSituation", recordList.get(mposition).userSituation);
                intent.putExtra("userFeeling", recordList.get(mposition).userFeeling);
                // 인텐트를 사용하여 액티비티 시작
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (recordList == null) {
            return 0;
        } else {
            return this.recordList.size();
        }
    }

    // 리스트 데이터 설정
    public void setRecordList(List<Record> recordList) {

        this.recordList = recordList;
        notifyDataSetChanged();
    }

    // 일지 삭제
    public void deleteRecord(int position) {
        this.recordList.remove(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView situationText, feelingText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            situationText = itemView.findViewById((R.id.user_situation));
            feelingText = itemView.findViewById(R.id.user_feeling);
        }
    }
}
