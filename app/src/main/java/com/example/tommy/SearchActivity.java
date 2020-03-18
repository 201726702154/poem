package com.example.tommy;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tommy.lib.CrawlerlUtil;
import com.example.tommy.lib.Poem;

import java.util.ArrayList;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    private List<Data> biaoti;
    private List<Data> zuozhe;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SearchView searchView;
    private List<String> titles = new ArrayList<String>();
    private FraMyFragment myFragment01;
    private FraMyFragment myFragment02;
    private void initListData(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Poem> lists = CrawlerlUtil.search("title", query);
                if (biaoti == null) {
                    biaoti = new ArrayList<>();
                }
                biaoti.clear();
                for (int i = 0; i < lists.size(); i++) {
                    CrawlerlUtil.poemShortcut(lists.get(i));
                    System.out.println(lists.get(i));
                    biaoti.add(new Data(lists.get(i).title, lists.get(i).author, lists.get(i).shortcut, lists.get(i).url));
                }
                if (zuozhe == null) {
                    zuozhe = new ArrayList<>();
                }
                zuozhe.clear();
                List<Poem> lists01 = CrawlerlUtil.search("author", query);
                for (int i = 0; i < lists01.size(); i++) {
                    CrawlerlUtil.poemShortcut(lists01.get(i));
                    System.out.println(lists01.get(i));
                    zuozhe.add(new Data(lists01.get(i).title, lists01.get(i).author, lists01.get(i).shortcut, lists01.get(i).url));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(biaoti);
                        initFraData();
                    }
                });
            }
        }).start();
    }
    private void intData() {
        titles.add("标题");
        titles.add("作者");
    }
    private void initView() {
        searchView = findViewById(R.id.sv);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
    }
    private void initFraData() {
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<Fragment>();
        myFragment01 = new FraMyFragment(R.layout.list_item02).initData(biaoti);
        myFragment02 = new FraMyFragment(R.layout.list_item02).initData(zuozhe);
        fragments.add(myFragment01);
        fragments.add(myFragment02);
        FraMyFragmentAdapter fragmentPager = new FraMyFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void initEvent() {
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setQueryHint("请输入标题/作者");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                initListData(query);
                return false;
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    Toast.makeText(SearchActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                    mTabLayout.removeAllTabs();
                    mViewPager.removeAllViews();
                }
                return true;
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intData();
        initView();
        initEvent();
        onBack();
    }
    private void onBack() {
        ImageView imageView = findViewById(R.id.return1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
    }
}

