package com.example.ours;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMMessageManager;

/**
 * Created by pc on 2017/6/11.
 */

public class Whisper extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"B48UfizjJ7ojbCC9szNxQFuk-gzGzoHsz","9VicmWxu1V7uTgCUsq2Prah8");
        AVOSCloud.setDebugLogEnabled(true);
        AVIMMessageManager.registerDefaultMessageHandler(new MainActivity.CustomMessageHandler());



    }




}


