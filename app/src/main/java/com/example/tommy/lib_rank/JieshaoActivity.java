package com.example.tommy.lib_rank;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tommy.FraMainActivity;
import com.example.tommy.ListPoemActivity;
import com.example.tommy.R;
public class JieshaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jieshao);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JieshaoActivity.this, FraMainActivity.class);
                startActivity(intent);
                JieshaoActivity.this.finish();
            }
        });
    }
}
