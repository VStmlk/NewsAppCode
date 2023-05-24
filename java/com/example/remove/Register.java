package com.example.remove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.remove.LitePalTest.User;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        EditText newaccont = (EditText) findViewById(R.id.newaccont);
        EditText newpassword = (EditText) findViewById(R.id.newpassword);
        EditText name = (EditText) findViewById(R.id.username);
        EditText gmail = (EditText) findViewById(R.id.usergmail);

        Button ok_register = (Button) findViewById(R.id.ok_register);
        ok_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(newaccont.getText().toString(),newpassword.getText().toString(),name.getText().toString(),gmail.getText().toString());
                user.save();
                Intent intent = new Intent(Register.this, Loading.class);
                startActivity(intent);
                finish();
            }
        });
    }
}