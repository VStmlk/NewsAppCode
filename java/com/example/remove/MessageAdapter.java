package com.example.remove;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.News;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private News mMessagelist;


    //FruitAdapter中也有一个构造函数，这个方法用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行
    public MessageAdapter(News messagelist) {
        mMessagelist = messagelist;
    }

    //首先定义了一个内部类ViewHolder, ViewHolder要继承自RecyclerView.ViewHolder。
    // 然后ViewHolder的构造函数中要传入一个View参数，这个参数通常就是RecyclerView子项的最外层布局，
    // 那么我们就可以通过findViewById()方法来获取到布局中的ImageView和TextView的实例了
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image1;
        ImageView image2;
        ImageView image3;

        TextView title;
        TextView context;
        TextView news_from;
        TextView news_date;

        public ViewHolder(View view) {
            super(view);
            image1 = (ImageView) view.findViewById(R.id.image1);
            image2 = (ImageView) view.findViewById(R.id.image2);
            image3 = (ImageView) view.findViewById(R.id.image3);
            title = (TextView) view.findViewById(R.id.title_textview);
            context = (TextView) view.findViewById(R.id.message_textview);
            news_from = (TextView) view.findViewById(R.id.news_from);
            news_date = (TextView) view.findViewById(R.id.news_date);

        }
    }




    //由于FruitAdapter是继承自RecyclerView.Adapter的，那么就必须重写onCreateViewHolder()、onBindViewHolder()和getItemCount()这3个方法
    //onCreate-ViewHolder()方法是用于创建ViewHolder实例的，我们在这个方法中将fruit_item布局加载进来，然后创建一个ViewHolder实例，并把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //onBindViewHolder()方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News message = mMessagelist;
        Glide.with(holder.image1).load(message.getImage1()).into(holder.image1);
        Glide.with(holder.image2).load(message.getImage2()).into(holder.image2);
        Glide.with(holder.image3).load(message.getImage3()).into(holder.image3);

        holder.title.setText(message.getTitle());
        holder.context.setText(message.getContext());
        holder.news_date.setText(message.getNewsdate());
        holder.news_from.setText(message.getFrom());



    }

    //getItemCount()方法就非常简单了，它用于告诉RecyclerView一共有多少子项，直接返回数据源的长度就可以了
    @Override
    public int getItemCount() {
        return 1;
    }
}