package com.example.tommy.viewpaper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.answer.AnalogyExaminationActivity;
import com.example.answer.TMainActivity;
import com.example.dabuff.speechevaluation.voicedemo.IseDemo;
import com.example.tommy.Data;
import com.example.tommy.R;

import com.example.tommy.lib.CrawlerlUtil;

import java.util.List;
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Data> mData;
    private TextView content;
    private TextView title;
    private TextView author;
    private View view;
    private int posi;
    private View mCurrentView;
    private Button button;
    public MyPagerAdapter(Context context, List<Data> list, int posi) {
        mContext = context;
        mData = list;
        this.posi = posi;
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        view = View.inflate(mContext, R.layout.activity_poem, null);
        title = (TextView) view.findViewById(R.id.title);
        author = (TextView) view.findViewById(R.id.author);
        content = (TextView) view.findViewById(R.id.content);
        button = view.findViewById(R.id.btn);
        title.setText(mData.get(position).poem_title);
        author.setText(mData.get(position).poem_author);
        if (!mData.get(position).poem_content.equals("")) {
            content.setText(mData.get(position).poem_content);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mData.get(position).poem_content = CrawlerlUtil.getContent(mData.get(position).poem_url);

                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            content.setText(mData.get(position).poem_content);
                        }
                    });
                }
            }).start();
        }
        container.addView(view);
        return view;
    }
    public View getView() {
        return view;
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View) object;
    }
    public View getPrimaryItem() {
        return mCurrentView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

