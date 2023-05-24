package com.example.remove;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remove.LitePalTest.Collect_sum;
import com.example.remove.LitePalTest.News;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Collect extends AppCompatActivity {
    //private List<News> newsList = new ArrayList<>();
    private List<News> newsList = new ArrayList<News>();
    private List<Collect_sum> collect_sums;
    private boolean mIsThemeApplied = false;

    @Override
    protected void onRestart() {
        super.onRestart();//onRestart()仅在返回此Activity时被调用，更加严格的控制了刷新数据的时机，避免出现数据错乱的情况。
        refreshData();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_main);


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
//
//
//        SharedPreferences prefs = getSharedPreferences("daynight", MODE_PRIVATE);
//        int themeMode = prefs.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//
//        getDelegate().setLocalNightMode(themeMode);
//        recreate();


        //获取用户ID
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        //从收藏表里筛选出用户的收藏
        collect_sums = LitePal.select("newsid").where("userid=?",Integer.toString(userId)).find(Collect_sum.class);

        //遍历用户的收藏表，从中获取每个收藏对应的新闻id，再通过新闻id在新闻表中搜索相对应的新闻
        for(Collect_sum collect_sum : collect_sums){

            //避免重复数据
            List<News> tempNewsList = LitePal.where("id=?", Integer.toString(collect_sum.getNewsid())).find(News.class);
            if(tempNewsList != null && !tempNewsList.isEmpty()){
                News tempNews = tempNewsList.get(0);
                if(!newsList.contains(tempNews)){
                    newsList.add(tempNews);
                }
            }
            //Litepal.where返回的是一个表，get(0)使得数据表的数据一个个传进去
            //newsList.add(LitePal.where("id=?",Integer.toString(collect_sum.getNewsid())).find(News.class).get(0));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.collect_re);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CollectAdapter collectAdapter = new CollectAdapter(Collect.this,newsList);
        recyclerView.setAdapter(collectAdapter);
    }

    private void refreshData() {
        //获取用户ID
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        //从收藏表里筛选出用户的收藏
        collect_sums = LitePal.select("newsid").where("userid=?",Integer.toString(userId)).find(Collect_sum.class);

        //遍历用户的收藏表，从中获取每个收藏对应的新闻id，再通过新闻id在新闻表中搜索相对应的新闻
        //清空newsList
        newsList.clear();
        for(Collect_sum collect_sum : collect_sums){

            //避免重复数据
            List<News> tempNewsList = LitePal.where("id=?", Integer.toString(collect_sum.getNewsid())).find(News.class);
            if(tempNewsList != null && !tempNewsList.isEmpty()){
                News tempNews = tempNewsList.get(0);
                if(!newsList.contains(tempNews)){
                    newsList.add(tempNews);
                }
            }
        }

        // 刷新RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.collect_re);
        CollectAdapter collectAdapter = new CollectAdapter(Collect.this,newsList);
        recyclerView.setAdapter(collectAdapter);
    }


}
