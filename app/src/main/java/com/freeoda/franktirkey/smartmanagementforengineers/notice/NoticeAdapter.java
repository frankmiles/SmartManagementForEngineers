package com.freeoda.franktirkey.smartmanagementforengineers.notice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;
import java.util.zip.Inflater;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.cViewHolder>{
    List<NoticeModel> list;
    Context context;
    private LayoutInflater mInflater;

    public NoticeAdapter(Context context,List<NoticeModel> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notice_card_model,parent,false);
        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, final int position) {
        holder.tv_notice_title.setText(list.get(position).title);
        holder.tv_notice_body.setText(list.get(position).body);
        holder.btn_notice_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = list.get(position).getVisit().toString();
                Toast.makeText(context,"Redirecting To Browser ",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent. ACTION_VIEW);
                intent. setData(Uri. parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder{
        TextView tv_notice_title, tv_notice_body;
        Button btn_notice_detail;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notice_title = itemView.findViewById(R.id.tv_notice_title);
            tv_notice_body = itemView.findViewById(R.id.tv_notice_body);
            btn_notice_detail = itemView.findViewById(R.id.btn_notice_detail);
        }
    }
}
