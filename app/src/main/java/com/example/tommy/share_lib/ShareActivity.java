package com.example.tommy.share_lib;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tommy.PoemActivity;
import com.example.tommy.R;

import java.io.File;
import java.util.ArrayList;
public class ShareActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mRlShareText, mRlShareQQ, mRlShareWechat, mRlShareMicroblog;
    private Context mContext;
    private ImageView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mContext = this;
        bindView();
    }
    private void bindView() {
        mRlShareText = (RelativeLayout) findViewById(R.id.rl_share_text);
        mRlShareQQ = (RelativeLayout) findViewById(R.id.rl_share_qq);
        mRlShareWechat = (RelativeLayout) findViewById(R.id.rl_share_wechat);
        mRlShareMicroblog = (RelativeLayout) findViewById(R.id.rl_share_microblog);
        btn = (ImageView) findViewById(R.id.return1);
        initEvent();
    }
    private void initEvent() {
        mRlShareText.setOnClickListener(new ShareText());
        mRlShareQQ.setOnClickListener(this);
        mRlShareWechat.setOnClickListener(this);
        mRlShareMicroblog.setOnClickListener(this);
        btn.setOnClickListener(this);
    }
    public class ShareText implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent msg = getIntent();
            String content = msg.getStringExtra("data");
            System.out.println(content);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "分享到"));
        }
    }
    @Override
    public void onClick(View v) {
        String pakName = "";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        switch (v.getId()) {
            case R.id.rl_share_qq:
                pakName = "com.qzone";
                break;
            case R.id.rl_share_wechat:
                pakName = "com.tencent.mm";
                break;
            case R.id.rl_share_microblog:
                pakName = "com.sina.weibo";
                break;
            case R.id.return1:
                ShareActivity.this.finish();
                break;
            default:
                break;
        }
    }
}