package com.th.forge.vkfriendlist.presenters;

import android.support.annotation.IntegerRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.models.Friend;
import com.th.forge.vkfriendlist.ui.views.FriendListView;

import java.util.List;

@InjectViewState
public class FriendListPresenter extends MvpPresenter<FriendListView> {
    public void loadFriend() {

    }

    public void friendsLoaded(List<Friend> friendList) {
        getViewState().hideLoading();
        if (friendList.size() == 0) {

        } else {
            getViewState().setupFriendsList(friendList);
        }
    }

    public void showError(@IntegerRes Integer textResource) {

    }

    public void showError(String error) {

    }
}
