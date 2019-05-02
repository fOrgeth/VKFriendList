package com.th.forge.vkfriendlist.presenters;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.ui.views.LoginView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public boolean login(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                getViewState().showFriends();
            }

            @Override
            public void onError(VKError error) {
                getViewState().showError("VK login error");
            }
        })) {
            return false;
        }
        return true;
    }
}
