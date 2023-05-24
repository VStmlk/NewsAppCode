package com.example.remove;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.remove.LitePalTest.News;
import com.example.remove.LitePalTest.User;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;

import java.util.List;

public class News_pro extends AppCompatActivity {
    //private List<News> newList = new ArrayList<>();
    SharedPreferences preferences;

    private static boolean isLight = true;//无图
    private DrawerLayout mDrawerLayout;
    //List<News> news = LitePal.findAll(News.class);
    List<News> news;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewsAdapter adapter ;

    @Override
    protected void onRestart() {
        super.onRestart();//onRestart()仅在返回此Activity时被调用，更加严格的控制了刷新数据的时机，避免出现数据错乱的情况。

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0); //获取头部视图
        TextView username_head = headerView.findViewById(R.id.username); //获取用户名
        TextView gmail_head = headerView.findViewById(R.id.gmail); //获取邮箱
        ImageView ig_head = headerView.findViewById(R.id.icon_image); //获取头像

        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);
        User headuser = LitePal.where("id=?",Integer.toString(userId)).find(User.class).get(0);
        username_head.setText(headuser.getOthername());
        gmail_head.setText(headuser.getGmail());
        Glide.with(this).load(headuser.getUserimage()).into(ig_head);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_pro);


        news = LitePal.findAll(News.class);//类似于默认
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("新闻"));
        tabLayout.addTab(tabLayout.newTab().setText("时政"));
        tabLayout.addTab(tabLayout.newTab().setText("经济"));
        tabLayout.addTab(tabLayout.newTab().setText("社会"));
        tabLayout.addTab(tabLayout.newTab().setText("法制"));
        tabLayout.addTab(tabLayout.newTab().setText("文娱"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("军事"));

        //新闻导航栏
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 当前选中的选项卡
                int position = tab.getPosition();
                // 处理选中后的逻辑
                switch(position) {
                    case 0:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "新闻", Toast.LENGTH_SHORT).show();
                        news = LitePal.findAll(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 1:
                        // 时政选项卡被选中
                        Toast.makeText(News_pro.this, "时政", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","时政").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);


                        break;
                    case 2:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "经济", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","经济").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 3:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "社会", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","社会").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 4:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "法治", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","法治").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 5:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "文娱", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","文娱").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 6:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "科技", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","科技").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                    case 7:
                        // 新闻选项卡被选中
                        Toast.makeText(News_pro.this, "军事", Toast.LENGTH_SHORT).show();
                        news = LitePal.where("type = ?","军事").find(News.class);

                        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                        layoutManager = new LinearLayoutManager(News_pro.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new NewsAdapter(News_pro.this,news);
                        recyclerView.setAdapter(adapter);

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(News_pro.this,news);
        recyclerView.setAdapter(adapter);








        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0); //获取头部视图
        TextView username_head = headerView.findViewById(R.id.username); //获取用户名
        TextView gmail_head = headerView.findViewById(R.id.gmail); //获取邮箱
        ImageView ig_head = headerView.findViewById(R.id.icon_image); //获取头像

        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);
        User headuser = LitePal.where("id=?",Integer.toString(userId)).find(User.class).get(0);
        username_head.setText(headuser.getOthername());

        //gmail_head.setText(headuser.getGmail());
        if (headuser.getGmail().equals("")){
            gmail_head.setText("NULL");//默认邮箱为NULL
        }else {
            gmail_head.setText(headuser.getGmail());
        }

        if (headuser.getUserimage()== null){
            ig_head.setImageResource(R.drawable.red);
        }else {
            Glide.with(this).load(headuser.getUserimage()).into(ig_head);
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.white_menu);//设置一个导航按钮图标
        }

        //NavigationView
        navView.setCheckedItem(R.id.nav_me);//将“个人”菜单项设置为默认选中
        //setNavigationItemSelectedListener设置一个菜单项选中事件的监听器
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override//
            public boolean onNavigationItemSelected(MenuItem item) {
                //相应的逻辑处理
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_me://若报Constant expression required错，可将switch语句转换为if-else语句
                        Toast.makeText(getApplicationContext(), "You clicked me", Toast.LENGTH_SHORT).show();
                        Intent me = new Intent(getApplicationContext(), Me.class);
                        startActivity(me);
                        return true;

                    case R.id.nav_collect:
                        Toast.makeText(getApplicationContext(), "You clicked collect", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(getApplicationContext(),Collect.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_noimage:
                        //isLight = true;
                        if (isLight==true){
                            isLight=false;
                            Toast.makeText(getApplicationContext(), "You clicked 无图", Toast.LENGTH_SHORT).show();
                            recreate();//该方法会销毁当前 Activity 并重新创建它，从而刷新界面。这个方法适用于需要重新加载组件之间的关系或应用程序数据模型的情况。
//                            Intent intent = new Intent(getApplicationContext(), News_pro.class);
//                            startActivity(intent);
//                            finish();

                        }else {
                            isLight=true;
                            Toast.makeText(getApplicationContext(), "You clicked 有图", Toast.LENGTH_SHORT).show();
                            recreate();
                        }
                        return true;

                    case R.id.nav_blacktheme:
                        // 切换主题模式
                        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                        if (mode == Configuration.UI_MODE_NIGHT_YES) {
                            // 当前为夜间模式，设置为日间模式
                            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                            // 当前为日间模式，设置为夜间模式
                            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }

                        // 将主题设置保存到 SharedPreferences 中
                        SharedPreferences.Editor editor = getSharedPreferences("daynight", MODE_PRIVATE).edit();
                        editor.putInt("theme", getDelegate().getLocalNightMode());
                        editor.apply();

                        // 更新主题设置
                        getDelegate().applyDayNight();

                        Toast.makeText(getApplicationContext(), "You clicked blacktheme", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });



    }



    public boolean getisLight() {
        return isLight;
    }



    //类似于获取toolbar,以便获取相当于的id
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.search:
                Intent intent = new Intent(this,search.class);
                startActivity(intent);

                break;

            case R.id.exit:
                System.exit(0);//退出应用
                break;
            default:
        }
        return true;
    }



}
