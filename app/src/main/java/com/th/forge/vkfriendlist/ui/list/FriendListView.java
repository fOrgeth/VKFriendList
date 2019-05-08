package com.th.forge.vkfriendlist.ui.list;

import android.support.annotation.IntegerRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.th.forge.vkfriendlist.data.models.Friend;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FriendListView extends MvpView {
    void startLoading();

    void hideLoading();

    void showError(@IntegerRes int textResource);

    void showError(String error);

    void setupFriendsList(List<Friend> friendList);
}
