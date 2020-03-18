package com.example.tommy.registerlogin;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommy.FraMainActivity;
import com.example.tommy.R;
import com.example.tommy.lib_rank.JieshaoActivity;
import com.example.tommy.lib_rank.MyGradeActivity;
public class LoginActivity extends Activity {
    public int pwdresetFlag = 0;
    private EditText mAccount;
    private EditText mPwd;
    private Button mRegisterButton;
    private Button mLoginButton;
    private Button mCancleButton;
    private CheckBox mRememberCheck;
    private SharedPreferences login_sp;
    private String userNameValue, passwordValue;
    private View loginView;
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAccount = (EditText) findViewById(R.id.login_edit_account);
        mPwd = (EditText) findViewById(R.id.login_edit_pwd);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.login_success_view);
        loginSuccessShow = (TextView) findViewById(R.id.login_success_show);
        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);
        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);
        login_sp = getSharedPreferences("userInfo", 0);
        String name = login_sp.getString("USER_NAME", "");
        String pwd = login_sp.getString("PASSWORD", "");
        boolean choseRemember = login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin = login_sp.getBoolean("mAutologinCheck", false);
        if (choseRemember) {
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }
        mRegisterButton.setOnClickListener(mListener);
        mLoginButton.setOnClickListener(mListener);
        mChangepwdText.setOnClickListener(mListener);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }
    OnClickListener mListener = new OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_register:
                    Intent intent_Login_to_Register = new Intent(LoginActivity.this, Register.class);
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.login_btn_login:
                    login();
                    break;
                case R.id.login_text_change_pwd:
                    Intent intent_Login_to_reset = new Intent(LoginActivity.this, Resetpwd.class);
                    startActivity(intent_Login_to_reset);
                    finish();
                    break;
            }
        }
    };
    public void login() {
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            SharedPreferences.Editor editor = login_sp.edit();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if (result == 1) {
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);
                if (mRememberCheck.isChecked()) {
                    editor.putBoolean("mRememberCheck", true);
                } else {
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, JieshaoActivity.class);    //切换Login Activity至User Activity
                try {
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    System.out.println(e);
                }
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();//登录成功提示
            } else if (result == 0) {
                Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }
    public void cancel() {
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if (result == 1) {
                Toast.makeText(this, getString(R.string.cancel_success), Toast.LENGTH_SHORT).show();
                mPwd.setText("");
                mAccount.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            } else if (result == 0) {
                Toast.makeText(this, getString(R.string.cancel_fail), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}