package com.th.forge.vkfriendlist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.FragmentNavigationListener;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKServiceActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    public LoginPresenter loginPresenter;
    private Button btnSignIn;
    private TextView textSignIn;
    private TextView textError;
    private CircularProgressView circularProgressView;

    private String[] scopes = {VKScope.FRIENDS};

    private FragmentNavigationListener fragmentNavigationCallback;

    public void setFragmentNavigatorListener(FragmentNavigationListener callback) {
        this.fragmentNavigationCallback = callback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        btnSignIn = rootView.findViewById(R.id.btn_login_enter);
        textSignIn = rootView.findViewById(R.id.txt_login_hello);
        textError = rootView.findViewById(R.id.txt_login_error);
        circularProgressView = rootView.findViewById(R.id.cpv_login);
        loginPresenter.onStart();
        return rootView;
    }

    public void loginVk() {
        Intent intent = new Intent(getActivity(), VKServiceActivity.class);
        intent.putExtra("arg1", "Authorization");
        ArrayList<String> scope = new ArrayList<>(Arrays.asList(scopes));
        intent.putStringArrayListExtra("arg2", scope);
        intent.putExtra("arg4", VKSdk.isCustomInitialize());
        startActivityForResult(intent, VKServiceActivity.VKServiceType.Authorization.getOuterCode());
    }

    @Override
    public void showSignInButton() {
        btnSignIn.setVisibility(View.VISIBLE);
        textSignIn.setVisibility(View.VISIBLE);
        btnSignIn.setOnClickListener((view) -> loginPresenter.onSignInClick());
    }

    @Override
    public void startLoading() {
        btnSignIn.setVisibility(View.GONE);
        textSignIn.setVisibility(View.GONE);
        circularProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        circularProgressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(int textResource) {
        textError.setVisibility(View.VISIBLE);
        textError.setText(textResource);
    }

    @Override
    public void showFriends() {
        fragmentNavigationCallback.showFriendList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!loginPresenter.login(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
