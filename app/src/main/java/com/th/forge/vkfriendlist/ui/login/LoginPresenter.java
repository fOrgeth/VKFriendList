package com.th.forge.vkfriendlist.ui.login;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.R;
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
                getViewState().hideLoading();
                getViewState().showFriends();
            }

            @Override
            public void onError(VKError error) {
                getViewState().hideLoading();
                getViewState().showError(R.string.txt_login_error);
            }
        })) {
            return false;
        }
        return true;
    }

    public void onStart() {
        if (VKSdk.isLoggedIn()) {
            getViewState().showFriends();
        } else {
            getViewState().showSignInButton();
        }
    }

    public void onSignInClick() {
        getViewState().startLoading();
        getViewState().loginVk();
    }
}
