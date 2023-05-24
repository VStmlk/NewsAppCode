package com.example.remove;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remove.LitePalTest.News;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Noloding extends AppCompatActivity {
    private List<News> newsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noloding);

        // 查询数据库获取数据
        newsList = LitePal.findAll(News.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.loding_re);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NodingAdapter nodingAdapter = new NodingAdapter(this,newsList);
        recyclerView.setAdapter(nodingAdapter);



    }

}