package com.example.ours;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button register=(Button)findViewById(R.id.register);
        Button log_in=(Button)findViewById(R.id.log_in);
        final EditText Username=(EditText)findViewById(R.id.username);
        final EditText Password=(EditText)findViewById(R.id.password);

        //注册操作
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(Username.getText().toString());// 根据输入设置用户名
                user.setPassword(Password.getText().toString());// 根据输入设置密码
                //设置邮箱
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                            Toast.makeText(LogIn.this,"注册成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LogIn.this, MainActivity.class);
                            intent.putExtra("username",Username.getText().toString()); //向下一个活动传递用户名
                            startActivity(intent); //GS：可跳转到登陆界面？
                            LogIn.this.finish();
                        } else {
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                            Toast.makeText(LogIn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //登录操作
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser.logInInBackground(Username.getText().toString(),Password.getText().toString(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            LogIn.this.finish();
                            Intent intent=new Intent(LogIn.this, MainActivity.class);
                            intent.putExtra("username",Username.getText().toString()); //向下一个活动传递用户名
                            startActivity(intent);
                        } else {

                            Toast.makeText(LogIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
