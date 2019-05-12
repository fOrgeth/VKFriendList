package com.th.forge.vkfriendlist.ui.login;

import android.support.annotation.IntegerRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends MvpView {
    void startLoading();

    void endLoading();

    void loginVk();

    void showSignInButton();

    void showError(int textResource);

    void showFriends();
}
