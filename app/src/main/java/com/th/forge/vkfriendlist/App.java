package com.th.forge.vkfriendlist;

import android.app.Application;

import com.vk.api.sdk.VK;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VK.initialize(this);
    }
}
