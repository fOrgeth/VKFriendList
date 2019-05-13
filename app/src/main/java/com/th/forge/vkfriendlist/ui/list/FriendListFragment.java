package com.th.forge.vkfriendlist.ui.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.data.models.Friend;

import java.util.List;

public class FriendListFragment extends MvpAppCompatFragment implements FriendListView {

    private RecyclerView rvFriends;
    private TextView textError;
    private CircularProgressView circularProgressView;
    private FriendListAdapter adapter;

    @InjectPresenter
    public FriendListPresenter friendListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        rvFriends = rootView.findViewById(R.id.rv_friends);
        textError = rootView.findViewById(R.id.txt_list_error);
        circularProgressView = rootView.findViewById(R.id.cpv_list);
        friendListPresenter.loadFriends();
        adapter = new FriendListAdapter();
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        rvFriends.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void startLoading() {
        circularProgressView.setVisibility(View.VISIBLE);
        rvFriends.setVisibility(View.GONE);
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
    public void setupFriendsList(List<Friend> friendList) {
        rvFriends.setVisibility(View.VISIBLE);
        adapter.setupFriends(friendList);
//        Toast.makeText(getActivity(), friendList.get(0).getCity(), Toast.LENGTH_LONG).show();
    }

}
