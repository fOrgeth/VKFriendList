package com.th.forge.vkfriendlist.ui.list;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.data.models.Friend;
import com.th.forge.vkfriendlist.data.models.ProfileInfo;
import com.th.forge.vkfriendlist.repository.friends.FriendListActionCallback;
import com.th.forge.vkfriendlist.repository.friends.FriendsRepository;
import com.th.forge.vkfriendlist.repository.profileinfo.ProfileInfoActionCallback;
import com.th.forge.vkfriendlist.repository.profileinfo.ProfileInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@InjectViewState
public class FriendListPresenter extends MvpPresenter<FriendListView> implements FriendListActionCallback, ProfileInfoActionCallback {

    private FriendsRepository friendsRepository;
    private ProfileInfoRepository profileInfoRepository;

    public FriendListPresenter() {
        friendsRepository = new FriendsRepository(this);
        profileInfoRepository = new ProfileInfoRepository(this);
    }

    public void loadData() {
        friendsRepository.loadFriends();
        profileInfoRepository.getProfileInfo();
    }

    public void showError(@StringRes int textResource) {
        getViewState().hideLoading();
        getViewState().showError(textResource);
    }

    @Override
    public void onError(int errorResource) {
        showError(errorResource);
    }

    @Override
    public void onProfileInfoLoaded(ProfileInfo profileInfo) {
        if (profileInfo != null) {
            getViewState().setupProfileInfo(profileInfo.getName(), profileInfo.getLastName());
        }
    }

    @Override
    public void onFriendsLoaded(List<Friend> friendList) {
        getViewState().hideLoading();
        if (friendList.size() == 0) {
            getViewState().showError(R.string.empty_friend_list_error);
        } else {
            getViewState().setupFriendsList(getRandomFriends(friendList));
        }
    }

    private List<Friend> getRandomFriends(List<Friend> friendList) {
        List<Friend> randomFriends = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            randomFriends.add(friendList.get(rnd.nextInt(friendList.size()-1)));
        }
        return randomFriends;
    }
}
