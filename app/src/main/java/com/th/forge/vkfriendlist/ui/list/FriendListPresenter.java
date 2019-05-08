package com.th.forge.vkfriendlist.ui.list;

import android.support.annotation.IntegerRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.data.models.Friend;
import com.th.forge.vkfriendlist.repository.FriendsRepository;
import com.th.forge.vkfriendlist.ui.list.FriendListView;

import java.util.List;

@InjectViewState
public class FriendListPresenter extends MvpPresenter<FriendListView> {
    public void loadFriends() {
        new FriendsRepository(this).loadFriends();
    }

    public void friendsLoaded(List<Friend> friendList) {
        getViewState().hideLoading();
        if (friendList.size() == 0) {

        } else {
            getViewState().setupFriendsList(friendList);
        }
    }

    public void showError(@IntegerRes int textResource) {

    }

    public void showError(String error) {
        getViewState().showError(error);
    }
}
