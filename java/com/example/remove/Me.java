package com.example.remove;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;

import org.litepal.LitePal;

import java.util.List;

public class Me extends AppCompatActivity {

    private boolean mIsThemeApplied = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);

        // 判断主题是否已经应用
        if (!mIsThemeApplied) {
            // 从 SharedPreferences 中读取主题设置
            SharedPreferences prefs = getSharedPreferences("daynight", MODE_PRIVATE);
            int themeMode = prefs.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            // 应用主题设置
            getDelegate().setLocalNightMode(themeMode);
            getDelegate().applyDayNight();
            mIsThemeApplied = true;
        }

        ImageView head = (ImageView) findViewById(R.id.icon_image);
        TextView name = (TextView) findViewById(R.id.username);
        TextView userid = (TextView) findViewById(R.id.userid);
        TextView gmail = (TextView) findViewById(R.id.usergmail);
        Button image = (Button) findViewById(R.id.setimage);
        Button setpass = (Button) findViewById(R.id.setpass);
        Button setname = (Button) findViewById(R.id.setname);
        Button setgmail = (Button) findViewById(R.id.setgmail);
        Button death = (Button) findViewById(R.id.death);


        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

//        User user = new User("待定","待定","待定","待定");
//        user = LitePal.where("id=?",Integer.toString(userId)).find(User.class).get(0);
//        name.setText( user.getOthername());
//        id.setText(user.getId());
//        gmail.setText(user.getGmail());

        // 查询数据库
        List<User> userList = LitePal.where("id=?", Integer.toString(userId)).find(User.class);

        if (userList.size() > 0) {
            // 如果查询结果不为空，则获取第一个用户对象
            User user = userList.get(0);
            // 给对应的TextView设置文本
            name.setText(user.getOthername());
            userid.setText(Integer.toString(user.getId()));

            //gmail.setText(user.getGmail());
            //equals("") 比较是否为空集（没数据）
            if (user.getGmail().equals("")){
                gmail.setText("NULL");//默认邮箱为NULL
            }else {
                gmail.setText(user.getGmail());
            }

            //Glide.with(this).load(user.getUserimage()).into(head);
            if (user.getUserimage()== null){
                head.setImageResource(R.drawable.red);//默认图
            }else {
                Glide.with(this).load(user.getUserimage()).into(head);
            }

        } else {
            // 如果查询结果为空，则给默认值
            name.setText("NULL");
            userid.setText("NULL");
            gmail.setText("NULL");

        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setig();
            }
        });

        setpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            password();
            }
        });

        setname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            uname();
            }
        });

        setgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ugmail();
            }
        });

        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yyyy",Integer.toString(userId));
                LitePal.deleteAll(User.class,"id=?",Integer.toString(userId));
                System.exit(0);//退出应用
            }
        });

    }

    private void setig() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新头像链接");

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String userig = input.getText().toString();
            // 处理用户输入的评论
            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
            int userId = preferences.getInt("user_id", -1);
            List<User> userList = LitePal.where("id=?", Integer.toString(userId)).find(User.class);
            User user = userList.get(0);
            user.setUserimage(userig);
            user.save();
            recreate();

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void password() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新密码");

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String userpass = input.getText().toString();
            // 处理用户输入的评论
            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
            int userId = preferences.getInt("user_id", -1);
            List<User> userList = LitePal.where("id=?", Integer.toString(userId)).find(User.class);
            User user = userList.get(0);
            user.setPassword(userpass);
            user.save();
            recreate();

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void uname() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新用户名");

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String username = input.getText().toString();
            // 处理用户输入的评论
            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
            int userId = preferences.getInt("user_id", -1);
            List<User> userList = LitePal.where("id=?", Integer.toString(userId)).find(User.class);
            User user = userList.get(0);
            user.setOthername(username);
            user.save();
            recreate();

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void ugmail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新邮箱");

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String usergmail = input.getText().toString();
            // 处理用户输入的评论
            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
            int userId = preferences.getInt("user_id", -1);
            List<User> userList = LitePal.where("id=?", Integer.toString(userId)).find(User.class);
            User user = userList.get(0);
            user.setGmail(usergmail);
            user.save();
            recreate();

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}