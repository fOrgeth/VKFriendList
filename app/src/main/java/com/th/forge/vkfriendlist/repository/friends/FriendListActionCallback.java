package com.th.forge.vkfriendlist.repository.friends;

import android.support.annotation.StringRes;

import com.th.forge.vkfriendlist.data.models.Friend;

import java.util.List;

public interface FriendListActionCallback {
    void onError(@StringRes int errorResource);

    void onFriendsLoaded(List<Friend> friendList);
}
