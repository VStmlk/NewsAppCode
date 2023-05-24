package com.example.remove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.remove.pojo.Product;

public class QQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);

//        String name = getIntent().getStringExtra("name");
//        String word = getIntent().getStringExtra("word");
        Product product = (Product)getIntent().getSerializableExtra("product");

        TextView textname = findViewById(R.id.name2);
        TextView textword = findViewById(R.id.word2);

        textname.setText(product.getName());
        textword.setText(product.getWord());

        Button button = findViewById(R.id.rmove_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("word",textword.getText().toString());
                setResult(1,intent);
                finish();
//                Intent intent = new Intent(QQ.this,Loading.class);
//                startActivity(intent);
            }
        });

    }
}