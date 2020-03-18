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
import com.example.tommy.lib.DBUtils;
import com.example.tommy.lib.Poem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class CollectionActivity extends AppCompatActivity {
    private List<Data> itemsData;
    private String title;
    private String type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initListData();
        onBack();
    }
    private void initListData() {
        final DBUtils dbUtils = new DBUtils(CollectionActivity.this);
        dbUtils.createDB();
        itemsData = new ArrayList<Data>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Poem> lists = dbUtils.select();
                for (int i = 0; i < lists.size(); i++) {
                    CrawlerlUtil.poemShortcut(lists.get(i));
                    itemsData.add(new Data(lists.get(i).title, lists.get(i).author, lists.get(i).shortcut, lists.get(i).url).setFlagTrue());
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
                CollectionActivity.this.finish();
            }
        });
    }
    private void setAdapter() {
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionActivity.this));
        RecyclerMyListAdapter mAdapter = new RecyclerMyListAdapter(itemsData, CollectionActivity.this, R.layout.list_item02);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(CollectionActivity.this, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(CollectionActivity.this, R.color.LineColor);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter.setOnItemClickListener(new RecyclerMyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int layout) {
                Intent intent = new Intent();
                intent.putExtra("positon", position);
                Gson json = new Gson();
                intent.putExtra("data", json.toJson(itemsData));
                intent.setClass(CollectionActivity.this, PoemActivity.class);
                startActivity(intent);
            }
        });
    }
}
