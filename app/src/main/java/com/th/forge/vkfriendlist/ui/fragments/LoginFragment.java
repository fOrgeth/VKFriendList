package com.th.forge.vkfriendlist.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.activities.MainActivity;
import com.th.forge.vkfriendlist.ui.views.LoginView;
import com.vk.api.sdk.VK;


public class LoginFragment extends MvpAppCompatFragment implements LoginView {
    private Button btnSignIn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        VK.login(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        btnSignIn = rootView.findViewById(R.id.btn_login_enter);
        btnSignIn.setOnClickListener((view) -> VK.login(getActivity()));
        return rootView;
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
        AppCompatActivity host = (MainActivity) getActivity();
        ((MainActivity) host).addFragment(new FriendListFragment());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
