package com.th.forge.vkfriendlist.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.models.Friend;
import com.th.forge.vkfriendlist.presenters.FriendListPresenter;
import com.th.forge.vkfriendlist.ui.adapters.FriendListAdapter;
import com.th.forge.vkfriendlist.ui.views.FriendListView;

import java.util.List;

public class FriendListFragment extends MvpAppCompatFragment implements FriendListView {

    private RecyclerView rvFriends;
    private FriendListAdapter adapter;

    @InjectPresenter
    public FriendListPresenter friendListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        rvFriends = rootView.findViewById(R.id.rv_friends);
        friendListPresenter.loadFriends();
        adapter = new FriendListAdapter();
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        rvFriends.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int textResource) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupFriendsList(List<Friend> friendList) {
        adapter.setupFriends(friendList);
//        Toast.makeText(getActivity(), friendList.get(0).getCity(), Toast.LENGTH_LONG).show();
    }

}
