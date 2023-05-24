package com.example.remove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;

import org.litepal.LitePal;

import java.util.List;

public class Loading extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_loading);

        LitePal.getDatabase();

        ImageButton button_load = (ImageButton) findViewById(R.id.button_load);
        TextView button_register = (TextView) findViewById(R.id.button_register);
        TextView noloding = (TextView) findViewById(R.id.noloding);


        button_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = LitePal.findAll(User.class);
                EditText load_account = (EditText) findViewById(R.id.editText2);
                EditText load_password = (EditText) findViewById(R.id.editText4);

                for (User user : users) {
                    if (user.getUsername().equals(load_account.getText().toString()) && user.getPassword().equals(load_password.getText().toString())) {
                        Intent intent = new Intent(Loading.this, News_pro.class);

                        int userId = user.getId();//在用户登录成功后，获取用户的ID信息
                        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);//获取SharedPreferences对象：其中，user_info是SharedPreferences文件名，MODE_PRIVATE表示该SharedPreferences只能被当前应用程序读、写。
                        SharedPreferences.Editor editor = preferences.edit();//通过SharedPreferences对象获取SharedPreferences.Editor对象：
                        editor.putInt("user_id", userId);//通过Editor对象将用户ID信息保存到SharedPreferences中：
                        editor.apply();

                        Toast.makeText(Loading.this,"登入成功",Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Loading.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });



        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loading.this,Register.class);
                startActivity(intent);
            }
        });

        noloding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loading.this,Noloding.class);
                Toast.makeText(Loading.this,"游客登入",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        Button datatest = (Button) findViewById(R.id.datatest);
        datatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loading.this,DataTest.class);
                startActivity(intent);
            }
        });


        List<News> news = LitePal.findAll(News.class);

        for (News newss : news){
            System.out.println(newss.getTitle()+"+++++++++++++++++++++++++++++++++++++++");
        }

    }

}