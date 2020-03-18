package com.example.tommy.share_lib;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tommy.R;
public class HereActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mRlShare;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hereactivity);
        mContext = this;
        bindView();
    }
    private void bindView() {
        mRlShare = (RelativeLayout) findViewById(R.id.rl_share);
        mRlShare.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        startActivity(new Intent(HereActivity.this, ShareActivity.class));
    }
}

