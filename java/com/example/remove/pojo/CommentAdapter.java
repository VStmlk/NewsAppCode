package com.example.remove.pojo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remove.LitePalTest.Collect_sum;
import com.example.remove.LitePalTest.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remove.LitePalTest.Comment;
import com.example.remove.LitePalTest.Reply;
import com.example.remove.R;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> commentList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private int userId;
        private int comid; // 添加这一行，用于保存评论ID
        private int uid; // 添加这一行，用于保存回复者ID
        TextView nameTextView;
        TextView contentTextView;
        TextView replyButton;//回复按钮
        RecyclerView replyRecyclerView;//回复

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            contentTextView = view.findViewById(R.id.contentTextView);
            replyRecyclerView = view.findViewById(R.id.replyRecyclerView);




            //回复的点击事件
            replyButton = view.findViewById(R.id.reply_bu);
            replyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理回复按钮的点击事件
                    // 显示回复对话框或启动新的回复界面
                    // 传递评论或回复的ID以标识所回复的评论或回复

//                    Reply reply = new Reply();
//                    reply.getCommentid();
//                    reply.getUserid();
//                    reply.getUsername();
//                    reply.getContent();
//                    reply.getCreateTime();
//
                    //将当前的ViewHolder对象作为参数传递给showReplyDialog()方法
                    showReplyDialog(ViewHolder.this);
                }
            });
        }

        private void showReplyDialog(final ViewHolder holder) {

            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());//holder.itemView.getContext()返回的是 RecyclerView 所在的上下文对象
            builder.setTitle("回复 ");

            // 创建一个 EditText 控件，用于让用户输入回复内容
            final EditText input = new EditText(holder.itemView.getContext());
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // 为对话框添加“确定”按钮和“取消”按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String recontent = input.getText().toString();
                    // 处理用户输入的回复
                    // 将回复添加到评论中
                //通过ID查表获取用户名称
                    List<User> userList = LitePal.where("id=?",Integer.toString(uid)).find(User.class);
                    Log.d("uid",Integer.toString(uid));
                    Log.d("查询结果", "记录数：" + userList.size()); // 添加此行输出查询到的记录数
                    if (userList.size() > 0) {
                        User user = userList.get(0);

                        String username = user.getOthername();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d H:m");
                        String timestamp = dateFormat.format(new Date());
                        System.out.println(timestamp); // 输出类似于 "2021-11-10 22:30:00" 的字符串

                        Reply reply = new Reply();
                        reply.setCommentid(comid);//评论ID
                        reply.setUserid(uid);//回复者ID
                        reply.setUsername(username);//回复者名字
                        reply.setContent(recontent);
                        reply.setCreateTime(timestamp);
                        reply.save();

                        List<Reply> replyList = LitePal.where("commentid=?",Integer.toString(comid)).find(Reply.class);

                        // 如果有回复，就设置回复列表的布局管理器，并显示出来
                        //未设置布局管理器会报如下错误 No layout manager attached; skipping layout
                        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
                        holder.replyRecyclerView.setLayoutManager(layoutManager);

                        ReplyAdapter replyAdapter = new ReplyAdapter(replyList);
                        holder.replyRecyclerView.setAdapter(replyAdapter);
                        holder.replyRecyclerView.setVisibility(View.VISIBLE);

                    }
                    //User user = LitePal.where("id=?",Integer.toString(userId)).find(User.class).get(0);
                    Log.d("1","userId = 0");
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            // 显示回复对话框
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }
    public CommentAdapter(Context context,List<Comment> commentList) {
        this.commentList = commentList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = commentList.get(position);//这一段相当于是一直在跟随你鼠标的位置来判断你所选择的数据
        holder.nameTextView.setText(comment.getUsername());
        holder.contentTextView.setText(comment.getContent());
        holder.comid = comment.getId(); // 在这里获取评论的ID

        SharedPreferences preferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);
        Log.d("qwer",Integer.toString(userId));

        holder.uid = userId;

        //这个方法中找不到comid,类似于comid是给这个方法之外的方法使用的（加了holder前缀之后）
        List<Reply> replyList = LitePal.where("commentid=?",Integer.toString(comment.getId())).find(Reply.class);
        //replyJump.Jump(replyList);
        if (replyList != null && replyList.size() > 0) {
            // 如果有回复，就设置回复列表的布局管理器，并显示出来
            //未设置布局管理器会报如下错误 No layout manager attached; skipping layout
            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
            holder.replyRecyclerView.setLayoutManager(layoutManager);

            ReplyAdapter replyAdapter = new ReplyAdapter(replyList);
            holder.replyRecyclerView.setAdapter(replyAdapter);
            holder.replyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            holder.replyRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


}
