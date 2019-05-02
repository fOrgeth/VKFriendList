package com.th.forge.vkfriendlist.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.models.Friend;
import com.th.forge.vkfriendlist.models.VKFriendsRequest;
import com.th.forge.vkfriendlist.ui.views.FriendListView;
import com.vk.api.sdk.VK;

import java.util.List;

public class FriendListFragment extends MvpAppCompatFragment implements FriendListView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        VK.execute(VKFriendsRequest(),);
        return rootView;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(Integer textResource) {

    }

    @Override
    public void setupFriendsList(List<Friend> friendList) {

    }

}
