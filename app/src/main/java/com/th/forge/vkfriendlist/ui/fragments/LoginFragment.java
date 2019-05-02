package com.th.forge.vkfriendlist.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.views.LoginView;


public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showError(Integer textResource) {

    }

    @Override
    public void showFriends() {

    }
}
