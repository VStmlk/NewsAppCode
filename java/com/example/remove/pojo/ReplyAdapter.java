package com.example.remove.pojo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remove.LitePalTest.Comment;
import com.example.remove.LitePalTest.Reply;
import com.example.remove.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder> {

    private List<Reply> replyList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView contentTextView;
        TextView timeTextView;

        String timestamp; // 添加此行，用于保存时间戳

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);

//            SimpleDateFormat dateFormat = new SimpleDateFormat("M-d H:m");
//            timestamp = dateFormat.format(new Date());

        }
    }

    public ReplyAdapter(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reply, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reply reply = replyList.get(position);
        holder.nameTextView.setText(reply.getUsername());
        holder.contentTextView.setText(reply.getContent());
        holder.timeTextView.setText(reply.getCreateTime());
        //holder.timeTextView.setText(holder.timestamp);
    }

    @Override
    public int getItemCount() {

        return replyList.size();
    }

}