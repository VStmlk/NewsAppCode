package com.example.remove;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    //RecyclerView重用机制导致的问题-未解决
    private static boolean isLight;//无图

    private List<News> mNewsList;
    private AppCompatActivity activity;


    //FruitAdapter中也有一个构造函数，这个方法用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行
    public NewsAdapter(AppCompatActivity activity,List<News> newslist) {
        mNewsList = newslist;
        this.activity = activity;
    }

    //首先定义了一个内部类ViewHolder, ViewHolder要继承自RecyclerView.ViewHolder。
    // 然后ViewHolder的构造函数中要传入一个View参数，这个参数通常就是RecyclerView子项的最外层布局，
    // 那么我们就可以通过findViewById()方法来获取到布局中的ImageView和TextView的实例了
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        TextView data_news;

        public ViewHolder(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            data_news = (TextView) view.findViewById(R.id.data_news);

            News_pro is = new News_pro();
            isLight = is.getisLight();

        }

    }





    //由于FruitAdapter是继承自RecyclerView.Adapter的，那么就必须重写onCreateViewHolder()、onBindViewHolder()和getItemCount()这3个方法
    //onCreate-ViewHolder()方法是用于创建ViewHolder实例的，我们在这个方法中将fruit_item布局加载进来，然后创建一个ViewHolder实例，并把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Boolean is = isLight;
        if (is==true){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item_noimage, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

    }

    //onBindViewHolder()方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        //holder.fruitImage.setImageResource(news.getImage1());
        if (holder.fruitImage != null){
            Glide.with(holder.fruitImage).load(news.getImage1()).into(holder.fruitImage);
        }

        holder.fruitName.setText(news.getTitle());
        holder.data_news.setText(news.getNewsdate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Message_pro.class);
                intent.putExtra("id",news.getId());

                activity.startActivity(intent);

            }
        });
    }

    //getItemCount()方法就非常简单了，它用于告诉RecyclerView一共有多少子项，直接返回数据源的长度就可以了
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


}