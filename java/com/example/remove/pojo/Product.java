package com.example.remove.pojo;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String word;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
