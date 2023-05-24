package com.example.remove.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//与数据库中的字段(OpenSqlHelper中的建表)一一对应
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="username")
    public String username;
    public String password;
    public int age;
}
