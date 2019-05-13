package com.th.forge.vkfriendlist.ui.list;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.vkfriendlist.data.models.Friend;
import com.th.forge.vkfriendlist.data.models.ProfileInfo;
import com.th.forge.vkfriendlist.repository.friends.FriendListActionCallback;
import com.th.forge.vkfriendlist.repository.friends.FriendsRepository;
import com.th.forge.vkfriendlist.repository.profileinfo.ProfileInfoActionCallback;
import com.th.forge.vkfriendlist.repository.profileinfo.ProfileInfoRepository;

import java.util.List;

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

        } else {
            getViewState().setupFriendsList(friendList);
        }
    }
}
