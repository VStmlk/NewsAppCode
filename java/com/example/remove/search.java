package com.example.remove;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    private List<News> newsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // 查询数据库获取数据
        newsList = LitePal.findAll(News.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_re);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CollectAdapter collectAdapter = new CollectAdapter(this,newsList);
        recyclerView.setAdapter(collectAdapter);

        // 显示搜索对话框
        searchpro();

    }

    private void searchpro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("搜索关键字");

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String searchtext = input.getText().toString();
            // 处理用户输入的评论
            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
            int userId = preferences.getInt("user_id", -1);

            //模糊搜索
            List<News> searchResult =LitePal.where("title like ?  or context like ?", "%"+ searchtext + "%","%"+  searchtext + "%").find(News.class);
            newsList.clear();  // 清空原有数据
            newsList.addAll(searchResult); // 添加搜索结果数据

            // 初始化适配器
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_re);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            CollectAdapter collectAdapter = new CollectAdapter(this,newsList);
            recyclerView.getAdapter().notifyDataSetChanged();  // 更新 RecyclerView 的数据

           // recreate();

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}