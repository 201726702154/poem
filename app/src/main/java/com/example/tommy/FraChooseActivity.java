package com.example.tommy;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommy.lib.CrawlerlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FraChooseActivity extends AppCompatActivity {
    private List<Data> dynasty;
    private List<Data> author;
    private List<Data> type;
    private List<Data> shiji;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> titles = new ArrayList<String>();
    private void intData() {
        TextView textView = findViewById(R.id.title);
        textView.setText("分类");
        titles.add("朝代");
        titles.add("作者");
        titles.add("类型");
        titles.add("形式");
        initListData();
    }
    private void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
    }
    private void initListData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                author = new ArrayList<Data>();
                dynasty = new ArrayList<Data>() {{
                    add(new Data("先秦", "朝代"));
                    add(new Data("两汉", "朝代"));
                    add(new Data("魏晋", "朝代"));
                    add(new Data("南北朝", "朝代"));
                    add(new Data("隋代", "朝代"));
                    add(new Data("唐代", "朝代"));
                    add(new Data("五代", "朝代"));
                    add(new Data("宋代", "朝代"));
                    add(new Data("金朝", "朝代"));
                    add(new Data("元代", "朝代"));
                    add(new Data("明代", "朝代"));
                    add(new Data("清代", "朝代"));
                    add(new Data("近现代", "朝代"));
                }};
                type = new ArrayList<Data>() {{
                    add(new Data("写景", "类型"));
                    add(new Data("咏物", "类型"));
                    add(new Data("春天", "类型"));
                    add(new Data("夏天", "类型"));
                    add(new Data("秋天", "类型"));
                    add(new Data("冬天", "类型"));
                    add(new Data("写雨", "类型"));
                    add(new Data("写雪", "类型"));
                    add(new Data("写风", "类型"));
                    add(new Data("写花", "类型"));
                    add(new Data("梅花", "类型"));
                    add(new Data("荷花", "类型"));
                    add(new Data("菊花", "类型"));
                    add(new Data("柳树", "类型"));
                    add(new Data("月亮", "类型"));
                    add(new Data("山水", "类型"));
                    add(new Data("写山", "类型"));
                    add(new Data("写水", "类型"));
                    add(new Data("长江", "类型"));
                    add(new Data("黄河", "类型"));
                    add(new Data("儿童", "类型"));
                    add(new Data("写鸟", "类型"));
                    add(new Data("写马", "类型"));
                    add(new Data("田园", "类型"));
                    add(new Data("边塞", "类型"));
                    add(new Data("地名", "类型"));
                    add(new Data("抒情", "类型"));
                    add(new Data("爱国", "类型"));
                    add(new Data("离别", "类型"));
                    add(new Data("送别", "类型"));
                    add(new Data("思乡", "类型"));
                    add(new Data("思念", "类型"));
                    add(new Data("爱情", "类型"));
                    add(new Data("励志", "类型"));
                    add(new Data("哲理", "类型"));
                    add(new Data("闺怨", "类型"));
                    add(new Data("悼亡", "类型"));
                    add(new Data("写人", "类型"));
                    add(new Data("老师", "类型"));
                    add(new Data("母亲", "类型"));
                    add(new Data("友情", "类型"));
                    add(new Data("战争", "类型"));
                    add(new Data("读书", "类型"));
                    add(new Data("惜时", "类型"));
                    add(new Data("婉约", "类型"));
                    add(new Data("豪放", "类型"));
                    add(new Data("诗经", "类型"));
                    add(new Data("民谣", "类型"));
                    add(new Data("节日", "类型"));
                    add(new Data("春节", "类型"));
                    add(new Data("元宵节", "类型"));
                    add(new Data("寒食节", "类型"));
                    add(new Data("清明节", "类型"));
                    add(new Data("端午节", "类型"));
                    add(new Data("七夕节", "类型"));
                    add(new Data("中秋节", "类型"));
                    add(new Data("重阳节", "类型"));
                    add(new Data("忧国忧民", "类型"));
                    add(new Data("咏史怀古", "类型"));
                }};
                shiji = new ArrayList<Data>() {{
                    add(new Data("诗", "形式"));
                    add(new Data("词", "形式"));
                    add(new Data("曲", "形式"));
                    add(new Data("文言文", "形式"));
                }};
                List<String> list = CrawlerlUtil.getAuthor();
                for (int i = 0; i < list.size(); i++) {
                    author.add(new Data(list.get(i), "作者"));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initEvent();
                    }
                });
            }
        }).start();
    }
    private void initEvent() {
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new FraMyFragment(R.layout.list_choose_main).initData(dynasty));
        fragments.add(new FraMyFragment(R.layout.list_choose_main).initData(author));
        fragments.add(new FraMyFragment(R.layout.list_choose_main).initData(type));
        fragments.add(new FraMyFragment(R.layout.list_choose_main).initData(shiji));
        FraMyFragmentAdapter fragmentPager = new FraMyFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initView();
        intData();
        onBack();
    }
    private void onBack() {
        ImageView imageView = findViewById(R.id.return1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FraChooseActivity.this.finish();
            }
        });
    }
}
