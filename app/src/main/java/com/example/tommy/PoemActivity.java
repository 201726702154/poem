package com.example.tommy;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dabuff.speechevaluation.voicedemo.IseDemo;

import com.example.tommy.lib.CrawlerlUtil;
import com.example.tommy.lib.DBUtils;
import com.example.tommy.share_lib.ShareActivity;
import com.example.tommy.viewpaper.MyPagerAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.tommy.lib.CrawlerlUtil.getContent;
public class PoemActivity extends AppCompatActivity {
    private Data[] data;
    private MyPagerAdapter myPagerAdapter;
    private ViewPager vp;
    private int positon;
    private MediaPlayer mediaPlayer;
    private BottomNavigationView bottomNavigationView;
    private ImageView btn;
    private ImageView btn1;
    private ImageView landu;
    private ImageView fenxiang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_poem);
        initView();
        initData();
        setVp();
        onBarEvent();
        initEvent();
        onBack();
    }
    private void setVp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (positon >= 1 && data[positon - 1].poem_content.equals("")) {
                    final String content = getContent(data[positon - 1].poem_url);
                    data[positon - 1].poem_content = content;
                }
                if (data[positon].poem_content.equals("")) {
                    final String content1 = getContent(data[positon].poem_url);
                    data[positon].poem_content = content1;
                }
                final List<Data> list = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    list.add(data[i]);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myPagerAdapter = new MyPagerAdapter(PoemActivity.this, list, positon);
                        vp.setAdapter(myPagerAdapter);
                        vp.setCurrentItem(positon);
                        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int i, float v, int i1) {
                            }
                            @Override
                            public void onPageSelected(int i) {
                                mediaPlayer.reset();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if ("".equals(data[vp.getCurrentItem()].poem_cotent_sound)) {
                                            data[vp.getCurrentItem()].poem_cotent_sound = CrawlerlUtil.attrContentSound(data[vp.getCurrentItem()].poem_url);
                                        }
                                        try {
                                            if (!"".equals(data[vp.getCurrentItem()].poem_cotent_sound)) {

                                                if (btn.getVisibility() == View.GONE) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            btn.setVisibility(View.VISIBLE);
                                                        }
                                                    });
                                                }
                                                mediaPlayer.setDataSource(data[vp.getCurrentItem()].poem_cotent_sound);
                                                mediaPlayer.prepareAsync();
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        btn.setVisibility(View.GONE);
                                                    }
                                                });
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (data[vp.getCurrentItem()].flag == false) {
                                            Glide.with(PoemActivity.this).load(R.drawable.shoucan).into(btn1);
                                        } else {
                                            Glide.with(PoemActivity.this).load(R.drawable.yishoucan).into(btn1);
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onPageScrollStateChanged(int i) {
                                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(0).getItemId());
                            }
                        });
                    }
                });
            }
        }).start();
    }
    private void initView() {
        vp = findViewById(R.id.vp);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        landu = findViewById(R.id.landu);
        fenxiang = findViewById(R.id.fenxiang);
    }
    private void initData() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        data = gson.fromJson(intent.getStringExtra("data"), Data[].class);
        positon = intent.getIntExtra("positon", 0);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build();
        mediaPlayer.setAudioAttributes(attributes);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if ("".equals(data[positon].poem_cotent_sound)) {
                    data[positon].poem_cotent_sound = CrawlerlUtil.attrContentSound(data[positon].poem_url);
                }
                if (!"".equals(data[positon].poem_cotent_sound)) {
                    try {
                        mediaPlayer.setDataSource(data[positon].poem_cotent_sound);
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn.setVisibility(View.GONE);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (data[positon].flag == false) {
                            Glide.with(PoemActivity.this).load(R.drawable.shoucan).into(btn1);
                        } else {
                            Glide.with(PoemActivity.this).load(R.drawable.yishoucan).into(btn1);
                        }
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
                PoemActivity.this.finish();
            }
        });
    }
    private void onBarEvent() {
        final DBUtils dbUtils = new DBUtils(PoemActivity.this);
        dbUtils.createDB();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data[vp.getCurrentItem()].flag == false) {
                    dbUtils.add(data[vp.getCurrentItem()]);
                    Glide.with(PoemActivity.this).load(R.drawable.yishoucan).into(btn1);
                    data[vp.getCurrentItem()].flag = true;
                } else {
                    dbUtils.delete(data[vp.getCurrentItem()]);
                    Glide.with(PoemActivity.this).load(R.drawable.shoucan).into(btn1);
                    data[vp.getCurrentItem()].flag = false;
                }
                dbUtils.select();
                System.out.println("------------------------------------------------------------");
            }
        });
        landu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoemActivity.this, IseDemo.class);
                View view = myPagerAdapter.getPrimaryItem();
                TextView tv = view.findViewById(R.id.content);
                String data01 = data[vp.getCurrentItem()].poem_content;
                intent.putExtra("data", data01);
                intent.putExtra("title", data[vp.getCurrentItem()].poem_title);
                startActivity(intent);
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoemActivity.this, ShareActivity.class);
                View view = myPagerAdapter.getPrimaryItem();
                TextView tv = view.findViewById(R.id.content);
                String data = tv.getText().toString();
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
    }
    private void initEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                View view = myPagerAdapter.getPrimaryItem();
                final TextView tv = view.findViewById(R.id.content);
                switch (menuItem.getItemId()) {
                    case R.id.zhenwen:
                        if (data[vp.getCurrentItem()].poem_content.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    data[vp.getCurrentItem()].poem_content = CrawlerlUtil.getContent(data[vp.getCurrentItem()].poem_url);
                                    View view = myPagerAdapter.getPrimaryItem();
                                    final TextView tv = view.findViewById(R.id.content);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv.setText(data[vp.getCurrentItem()].poem_content);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            tv.setText(data[vp.getCurrentItem()].poem_content);
                        }
                        break;
                    case R.id.zhushi:
                        if (data[vp.getCurrentItem()].poem_zhushi.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    vp.getCurrentItem();
                                    data[vp.getCurrentItem()].poem_zhushi = CrawlerlUtil.getExplanation(data[vp.getCurrentItem()].poem_url);
                                    View view = myPagerAdapter.getPrimaryItem();
                                    final TextView tv = view.findViewById(R.id.content);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv.setText(data[vp.getCurrentItem()].poem_zhushi);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            tv.setText(data[vp.getCurrentItem()].poem_zhushi);
                        }
                        break;
                    case R.id.yiwen:
                        if (data[vp.getCurrentItem()].poem_yiwen.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    data[vp.getCurrentItem()].poem_yiwen = CrawlerlUtil.getTranslation(data[vp.getCurrentItem()].poem_url);
                                    View view = myPagerAdapter.getPrimaryItem();
                                    final TextView tv = view.findViewById(R.id.content);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv.setText(data[vp.getCurrentItem()].poem_yiwen);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            tv.setText(data[vp.getCurrentItem()].poem_yiwen);
                        }
                        break;
                    case R.id.zuozhe:
                        if (data[vp.getCurrentItem()].poem_author_explan.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    data[vp.getCurrentItem()].poem_author_explan = CrawlerlUtil.getAuthorExplan(data[vp.getCurrentItem()].poem_url);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv.setText(data[vp.getCurrentItem()].poem_author_explan);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            tv.setText(data[vp.getCurrentItem()].poem_author_explan);
                        }
                        break;
                }
                return true;
            }
        });
    }
}
