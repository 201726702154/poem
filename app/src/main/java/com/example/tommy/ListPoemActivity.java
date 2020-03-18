package com.example.tommy;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.tommy.lib.CrawlerlUtil;
import com.example.tommy.lib.Poem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ListPoemActivity extends AppCompatActivity {
    private List<Data> itemsData;
    private String title;
    private String type;
    private void initView() {
        Intent intent = getIntent();
        TextView title_tv = findViewById(R.id.title);
        title = intent.getStringExtra("title");
        type = intent.getStringExtra("type");
        title_tv.setText(title);
    }
    private void initListData() {
        itemsData = new ArrayList<Data>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = CrawlerlUtil.getType(type);
                List<Poem> lists = CrawlerlUtil.searchByType(map.get(title));
                for (int i = 0; i < lists.size(); i++) {
                    CrawlerlUtil.poemShortcut(lists.get(i));
                    itemsData.add(new Data(lists.get(i).title, lists.get(i).author, lists.get(i).shortcut, lists.get(i).url));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter();
                    }
                });
            }
        }).start();
    }
    private void onBack() {
        ImageView imageView = findViewById(R.id.return1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListPoemActivity.this.finish();
            }
        });
    }
    private void setAdapter() {
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListPoemActivity.this));
        RecyclerMyListAdapter mAdapter = new RecyclerMyListAdapter(itemsData, ListPoemActivity.this, R.layout.list_item02);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(ListPoemActivity.this, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(ListPoemActivity.this, R.color.LineColor);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter.setOnItemClickListener(new RecyclerMyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int layout) {
                Intent intent = new Intent();
                intent.putExtra("positon", position);
                Gson json = new Gson();
                intent.putExtra("data", json.toJson(itemsData));
                intent.setClass(ListPoemActivity.this, PoemActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_poem);
        initView();
        initListData();
        onBack();
    }
}
