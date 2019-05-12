package com.th.forge.vkfriendlist.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.list.FriendListFragment;
import com.th.forge.vkfriendlist.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity implements FragmentNavigationListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new LoginFragment());
//        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
//        Log.d(TAG, "fingerprints = " + fingerprints[0]);

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof LoginFragment) {
            LoginFragment loginFragment = (LoginFragment) fragment;
            loginFragment.setFragmentNavigatorListener(this);
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void showFriendList() {
        FriendListFragment friendListFragment = new FriendListFragment();
        addFragment(friendListFragment);
    }
}
