package com.th.forge.vkfriendlist.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.fragments.LoginFragment;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.utils.VKUtils;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new LoginFragment());
//        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
//        Log.d(TAG, "fingerprints = " + fingerprints[0]);

    }

    public void addFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
