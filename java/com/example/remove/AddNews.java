package com.example.remove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remove.LitePalTest.News;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_news);

        EditText type = (EditText) findViewById (R.id.type_add);
        EditText title = (EditText) findViewById (R.id.title_add);
        EditText from = (EditText) findViewById (R.id.from_add);
        EditText content = (EditText) findViewById (R.id.context_add);
        EditText ig1 = (EditText) findViewById (R.id.ig1_add);
        EditText ig2 = (EditText) findViewById (R.id.ig2_add);
        EditText ig3 = (EditText) findViewById (R.id.ig3_add);
        Button add = (Button) findViewById(R.id.news_add);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ty = type.getText().toString();
                String ti = title.getText().toString();
                String fr = from.getText().toString();
                String co = content.getText().toString();
                String i1 = ig1.getText().toString();
                String i2 = ig2.getText().toString();
                String i3 = ig3.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("YY-M-d H:m");
                String timestamp = dateFormat.format(new Date());
                System.out.println(timestamp);

                News news = new News();
                news.setType(ty);
                news.setTitle(ti);
                news.setContext(co);
                news.setFrom(fr);
                news.setNewsdate(timestamp);
                news.setImage1(i1);
                news.setImage2(i2);
                news.setImage3(i3);
                news.save();
                Toast.makeText(AddNews.this, "发布成功", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });



    }
}