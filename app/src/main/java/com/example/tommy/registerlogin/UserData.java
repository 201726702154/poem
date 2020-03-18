package com.example.tommy.registerlogin;
public class UserData {
    private String userName;
    private String userPwd;
    private int userId;
    public int pwdresetFlag=0;
    public String getUserName() {             //获取用户名
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {                //获取用户密码
        return userPwd;
    }
    public void setUserPwd(String userPwd) {     //输入用户密码
        this.userPwd = userPwd;
    }
    public int getUserId() {                   //获取用户ID号
        return userId;
    }
    public void setUserId(int userId) {       //设置用户ID号
        this.userId = userId;
    }
    public UserData(String userName, String userPwd) {
        super();
        this.userName = userName;
        this.userPwd = userPwd;
    }
}