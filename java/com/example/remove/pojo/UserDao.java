package com.example.remove.pojo;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {//提供表的增删改查方法
    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void update(User user);

    @Query("select * from user")
    List<User> find();

    @Query("select * from user where id = :id")
    List<User> findAllUserById(int id);

    //    模糊搜索
    @Query("select * from user where username like '%' || :name ||'%'")
    List<User>findAllUsersByName(String name);

}

