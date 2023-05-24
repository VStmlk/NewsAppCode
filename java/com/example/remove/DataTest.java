package com.example.remove;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remove.DatabaseTest.MyDatabaseHelper;
import com.example.remove.LitePalTest.News;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataTest extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    String url = "https://i0.hdslb.com/bfs/article/b8d5a4b024686cc01524f07a16a7ecd9cc308c5c.jpg@1048w_!web-dynamic.webp";

    // 获取当前时间
    Date now = new Date();

    // 格式化时间
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = dateFormat.format(now);

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_test);
//        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dbHelper.getWritableDatabase();
                LitePal.getDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DataTest.this,AddNews.class);
                startActivity(intent);
//                News news = new News();
//                news.setTitle("焦点访谈丨新征程上奋斗者 民生：养老路上“助老”人");
//                news.setContext("千老人有一个更加幸福的晚年。");
//                news.setFrom("轩云阁");
//                news.setNewsdate(currentTime);
//                news.setImage1("https://p4.img.cctvpic.com/photoworkspace/contentimg/2023/05/02/2023050221470351851.jpg");
//                news.setImage2("https://p4.img.cctvpic.com/photoworkspace/contentimg/2023/05/02/2023050221475934557.jpg");
//                news.setImage3("https://p3.img.cctvpic.com/photoworkspace/contentimg/2023/05/02/2023050221485449449.jpg");
//                news.save();
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                News news = new News("abc","sdfsdafsadf","22-22-11","unkonw",url,url,url);
                News news = new News();
                news.setTitle("【新媒体走基层看检察】“检察蓝”绘新篇章 公益诉讼守护 “最美海岛”");
                news.setNewsdate("2023年04月25日 22:25:01");
                news.setImage1("https://p2.img.cctvpic.com/photoworkspace/contentimg/2023/04/25/2023042522240415807.jpg");
                news.updateAll("price = ?","43.96");

//                //设置为默认值
//                news.setToDefault("price");
//                news.updateAll();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LitePal.deleteAll(News.class);
//                //删除方法一
               LitePal.delete(News.class,2);
//                //删除方法二
//                LitePal.deleteAll(News.class, "id > ?","1");
            }
        });

        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<News> list = LitePal.findAll(News.class);
                for (News news: list) {
                    Log.d("MainActivity", "News id is " + news.getId());
                    Log.d("MainActivity", "News author is " + news.getTitle());
                    Log.d("MainActivity", "News price is " + news.getNewsdate());
                    Log.d("MainActivity", "News press is " + news.getContext());
                }
            }
        });

        Button start_load = (Button) findViewById(R.id.start_load);
        start_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataTest.this, Loading.class);
                startActivity(intent);
            }
        });
    }
}
