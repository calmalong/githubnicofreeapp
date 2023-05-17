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

    List<Record> recordList;

    Context context;

    public RecordAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int mPosition = holder.getAdapterPosition();

        holder.situationText.setText(recordList.get(position).userSituation);
        holder.feelingText.setText(recordList.get(position).userFeeling);

        //수정화면으로 이동
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdaterecordActivity.class);
                intent.putExtra("userSituation", recordList.get(mPosition).userSituation);
                intent.putExtra("userFeeling", recordList.get(mPosition).userFeeling);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return this.recordList.size();
    }

    //리스트 저장
    public void setRecordList(List<Record> recordList) {

        this.recordList = recordList;
        notifyDataSetChanged();
    }

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
