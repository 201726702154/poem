package com.example.tommy.registerlogin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tommy.R;
public class Resetpwd extends AppCompatActivity {
    private EditText mAccount;
    private EditText mPwd_old;
    private EditText mPwd_new;
    private EditText mPwdCheck;
    private Button mSureButton;
    private Button mCancelButton;
    private UserDataManager mUserDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
        initView();
        initEvent();
    }
    private void initView() {
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);
        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);
    }
    private void initEvent() {
        mSureButton.setOnClickListener(m_resetpwd_Listener);
        mCancelButton.setOnClickListener(m_resetpwd_Listener);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }
    View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resetpwd_btn_sure:
                    resetpwd_check();
                    break;
                case R.id.resetpwd_btn_cancel:
                    Intent intent_Resetpwd_to_Login = new Intent(Resetpwd.this, LoginActivity.class);
                    startActivity(intent_Resetpwd_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void resetpwd_check() {
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd_old = mPwd_old.getText().toString().trim();
            String userPwd_new = mPwd_new.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
            if (result == 1) {
                if (userPwd_new.equals(userPwdCheck) == false) {
                    Toast.makeText(this, getString(R.string.pwd_not_the_same), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    UserData mUser = new UserData(userName, userPwd_new);
                    mUserDataManager.openDataBase();
                    boolean flag = mUserDataManager.updateUserData(mUser);
                    if (flag == false) {
                        Toast.makeText(this, getString(R.string.resetpwd_fail), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, getString(R.string.resetpwd_success), Toast.LENGTH_SHORT).show();
                        mUser.pwdresetFlag = 1;
                        Intent intent_Register_to_Login = new Intent(Resetpwd.this, LoginActivity.class);
                        startActivity(intent_Register_to_Login);
                        finish();
                    }
                }
            } else if (result == 0) {
                Toast.makeText(this, getString(R.string.pwd_not_fit_user), Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        String userName = mAccount.getText().toString().trim();
        int count = mUserDataManager.findUserByName(userName);
        if (count <= 0) {
            Toast.makeText(this, getString(R.string.name_not_exist, userName), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_old.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_new.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_new_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}