package com.example.remove;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.Collect_sum;
import com.example.remove.LitePalTest.Comment;
import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;
import com.example.remove.pojo.CommentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Message_pro extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    Boolean iconold;
    private boolean isLight;//无图
    //private static boolean isCollected = false;  // 当前收藏状态

    private boolean mIsThemeApplied = false;
    private List<Comment> commentList = new ArrayList<>();
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断主题是否已经应用
        if (!mIsThemeApplied) {
            // 从 SharedPreferences 中读取主题设置
            SharedPreferences prefs = getSharedPreferences("daynight", MODE_PRIVATE);
            int themeMode = prefs.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            // 应用主题设置
            getDelegate().setLocalNightMode(themeMode);
            getDelegate().applyDayNight();
            mIsThemeApplied = true;
        }

        // 初始化 SharedPreferences 对象
        mSharedPreferences = getSharedPreferences("MenuState", MODE_PRIVATE);

        //请注意，要使用相同的文件名（"my_prefs"）和键名（"key"）才能获取到存储的值
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);
        //Log.d("bbb",String.valueOf(userId));


        News_pro is = new News_pro();
        isLight = is.getisLight();

        if (isLight){
            setContentView(R.layout.message_item);

        }else {
            setContentView(R.layout.message_item_noimage);

        }



        TextView title = (TextView) findViewById(R.id.title_textview);
        TextView content = (TextView) findViewById(R.id.message_textview);
        TextView time = (TextView) findViewById(R.id.news_date);
        TextView from = (TextView) findViewById(R.id.news_from);
        ImageView ig1 = (ImageView) findViewById(R.id.image1);
        ImageView ig2 = (ImageView) findViewById(R.id.image2);
        ImageView ig3 = (ImageView) findViewById(R.id.image3);

        Intent intent = getIntent();
        int id_news = intent.getIntExtra("id",20);
        news = LitePal.find(News.class,id_news);
        title.setText(news.getTitle());
        content.setText(news.getContext());
        time.setText(news.getNewsdate());
        from.setText(news.getFrom());
        if (ig1 != null && ig2 != null && ig3 != null){
            Glide.with(ig1).load(news.getImage1()).into(ig1);
            Glide.with(ig2).load(news.getImage2()).into(ig2);
            Glide.with(ig3).load(news.getImage3()).into(ig3);
        }

        commentList = LitePal.where("newsid=?",Integer.toString(news.getId())).find(Comment.class);

        intiCom();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.commentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(this,commentList);
        recyclerView.setAdapter(adapter);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        // 得到收藏菜单项，根据 SharedPreferences 设置图标和文字
        MenuItem collectMenuItem = bottomNavigationView.getMenu().findItem(R.id.menu_collect);
        //判断新闻是否被收藏
        List<Collect_sum> iconold = LitePal.where("newsid=?",Integer.toString(news.getId())).find(Collect_sum.class);
        Boolean icon = iconold.size()!=0;
        if (icon) {
            //设定图标颜色
            BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_collect_light);
            drawable.setColorFilter(Color.parseColor("#FFD700"), PorterDuff.Mode.SRC_IN);

            collectMenuItem.setIcon(R.drawable.ic_collect_light);
            collectMenuItem.setTitle("已收藏");
        } else {
            collectMenuItem.setIcon(R.drawable.ic_collect);
            collectMenuItem.setTitle("收藏");
        }


        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (item.getItemId()) {
                case R.id.menu_comment://若报Constant expression required错，可将switch语句转换为if-else语句
                    showCommentDialog();
                    return true;

                    //新闻收藏
                case R.id.menu_collect:
                    //判断数据是否存在
                    List<Collect_sum> isCollected = LitePal.where("newsid=?",Integer.toString(news.getId())).find(Collect_sum.class);

                    if (isCollected.size()!=0){
                        // 取消收藏
                        LitePal.deleteAll(Collect_sum.class,"newsid = ?",Integer.toString(news.getId()));

                        item.setTitle("收藏");
                        item.setIcon(R.drawable.ic_collect);
                        Toast toast =Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT);
                        //Toast.makeText(getApplicationContext(), "已收藏", Toast.LENGTH_SHORT).show();
                        showMyToast(toast, 1*500);

                    } else {
                        // 收藏
                        Collect_sum sum = new Collect_sum();
                        sum.setNewsid(news.getId());
                        sum.setUserid(userId);
                        sum.save();

                        item.setTitle("已收藏");
                        //图标颜色---------------------------------待定
                       BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_collect_light);
                       drawable.setColorFilter(Color.parseColor("#FFD700"), PorterDuff.Mode.SRC_IN);
                       item.setIcon(drawable);
                       //item.setIcon(R.drawable.ic_collect_light);

                       Toast toast =Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT);
                       showMyToast(toast, 1*500);

                    }
                default:
                    return false;
            }

        });


//    private void showCommentDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.comment);
//
//        // 创建一个 EditText 控件，用于让用户输入评论内容
//        final EditText input = new EditText(this);
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);
//
//        // 为对话框添加“确定”按钮和“取消”按钮
//        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
//            String comment = input.getText().toString();
//            // 处理用户输入的评论
//        });
//        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());
//
//        // 显示评论对话框
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

}

    public void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }


    private void intiCom() {
        //在 intiCom() 方法中，您声明了一个新的 comment1List，并将值添加到其中，但这个列表只在方法内部可见，而不是在整个 Message 类中可见。
        //List<Comment1> comment1List = new ArrayList<>();
//        Comment comment1 = new Comment("John", "This is the first comment.");
//        Comment reply1 = new Comment("Tom", "Thanks for your comment.");
//        Comment reply2 = new Comment("Jerry", "I agree with you.");
//        comment1.addReply(reply1);
//        comment1.addReply(reply2);
//        Comment comment02 = new Comment("Mary", "This is the second comment.");
//        Comment comment03 = new Comment("Jack", "This is the three comment.");
//        commentList.add(comment1);
//        commentList.add(comment02);
//        commentList.add(comment03);

//        for(Comment1 item:comment01.getReplyList()){
//            System.out.println(item.getName()+"===================================");
//        }
    }

    private void showCommentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.comment);

        // 创建一个 EditText 控件，用于让用户输入评论内容
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // 为对话框添加“确定”按钮和“取消”按钮
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String usercomment = input.getText().toString();
            // 处理用户输入的评论

            SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);//与56行代码作用相同
            int userId = preferences.getInt("user_id", -1);

            SimpleDateFormat dateFormat = new SimpleDateFormat("YY-M-d H:m");
            String timestamp = dateFormat.format(new Date());
            System.out.println(timestamp);

            User user = LitePal.where("id=?",Integer.toString(userId)).find(User.class).get(0);

            Comment comment =new Comment();
            comment.setNewsid(news.getId());
            comment.setUserid(userId);
            comment.setUsername(user.getOthername());
            comment.setContent(usercomment);
            comment.setCreateTime(timestamp);
            comment.save();


            commentList = LitePal.where("newsid=?",Integer.toString(news.getId())).find(Comment.class);

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.commentRecyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            CommentAdapter adapter = new CommentAdapter(this,commentList);
            recyclerView.setAdapter(adapter);
            //commentList = LitePal.where("newsid=?",Integer.toString(news.getId())).find(Comment.class);
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        // 显示评论对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    }


