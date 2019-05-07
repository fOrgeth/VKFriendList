package com.th.forge.vkfriendlist.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.presenters.LoginPresenter;
import com.th.forge.vkfriendlist.ui.activities.MainActivity;
import com.th.forge.vkfriendlist.ui.views.LoginView;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKServiceActivity;

import java.util.ArrayList;


public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    public LoginPresenter loginPresenter;
    private Button btnSignIn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        btnSignIn = rootView.findViewById(R.id.btn_login_enter);
        btnSignIn.setOnClickListener((view) -> loginVk());
        return rootView;
    }

    private void loginVk() {
        Intent intent = new Intent(getActivity(), VKServiceActivity.class);
        intent.putExtra("arg1", "Authorization");
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add(VKScope.FRIENDS);
        intent.putStringArrayListExtra("arg2",scopes);
        intent.putExtra("arg4",VKSdk.isCustomInitialize());
        startActivityForResult(intent,VKServiceActivity.VKServiceType.Authorization.getOuterCode());
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showError(int textResource) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showFriends() {
        MainActivity host = (MainActivity) getActivity();
        host.addFragment(new FriendListFragment());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!loginPresenter.login(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
