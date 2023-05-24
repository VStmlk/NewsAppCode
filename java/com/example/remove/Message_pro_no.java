package com.example.remove;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.Collect_sum;
import com.example.remove.LitePalTest.Comment;
import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;
import com.example.remove.pojo.CommentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Message_pro_no extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    Boolean iconold;
    private boolean isLight;//无图
    //private static boolean isCollected = false;  // 当前收藏状态

    private boolean mIsThemeApplied = false;
    private List<Comment> commentList = new ArrayList<>();
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


        News_pro is = new News_pro();
        isLight = is.getisLight();

        if (isLight) {
            setContentView(R.layout.message_item_no);

        } else {
            setContentView(R.layout.message_item_noimage_no);

        }


        TextView title = (TextView) findViewById(R.id.title_textview);
        TextView content = (TextView) findViewById(R.id.message_textview);
        TextView time = (TextView) findViewById(R.id.news_date);
        TextView from = (TextView) findViewById(R.id.news_from);
        ImageView ig1 = (ImageView) findViewById(R.id.image1);
        ImageView ig2 = (ImageView) findViewById(R.id.image2);
        ImageView ig3 = (ImageView) findViewById(R.id.image3);

        Intent intent = getIntent();
        int id_news = intent.getIntExtra("id", 20);
        news = LitePal.find(News.class, id_news);
        title.setText(news.getTitle());
        content.setText(news.getContext());
        time.setText(news.getNewsdate());
        from.setText(news.getFrom());
        if (ig1 != null && ig2 != null && ig3 != null) {
            Glide.with(ig1).load(news.getImage1()).into(ig1);
            Glide.with(ig2).load(news.getImage2()).into(ig2);
            Glide.with(ig3).load(news.getImage3()).into(ig3);
        }

        commentList = LitePal.where("newsid=?", Integer.toString(news.getId())).find(Comment.class);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.commentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(this, commentList);
        recyclerView.setAdapter(adapter);


    }
}


