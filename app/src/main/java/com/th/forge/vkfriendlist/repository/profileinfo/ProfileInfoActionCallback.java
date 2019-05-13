package com.th.forge.vkfriendlist.repository.profileinfo;

import android.support.annotation.StringRes;

import com.th.forge.vkfriendlist.data.models.ProfileInfo;

public interface ProfileInfoActionCallback {
    void onError(@StringRes int errorResource);

    void onProfileInfoLoaded(ProfileInfo profileInfo);
}
