package com.th.forge.vkfriendlist.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.ui.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
//        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
//        Log.d(TAG, "fingerprints = " + fingerprints[0]);

    }
}
