package com.th.forge.vkfriendlist.ui.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.data.models.Friend;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendsViewHolder> {
    public static final String TAG = FriendListAdapter.class.getSimpleName();
    private List<Friend> sourceList;

    public FriendListAdapter() {
        sourceList = new ArrayList<>();
    }

    public void setupFriends(List<Friend> sourceList) {
        this.sourceList.addAll(sourceList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_friend, viewGroup, false);
        return new FriendsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder viewHolder, int i) {
        viewHolder.bind(sourceList.get(i));
    }

    @Override
    public int getItemCount() {
        return sourceList == null ? 0 : sourceList.size();
    }

    static class FriendsViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civAvatar;
        private TextView txtUserName;
        private TextView txtCity;

        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            civAvatar = itemView.findViewById(R.id.friend_civ_avatar);
            txtUserName = itemView.findViewById(R.id.friend_txt_username);
            txtCity = itemView.findViewById(R.id.friend_txt_city);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Friend friend) {
            String url = friend.getAvatar();
            Log.d(TAG, url);
            if (url != null) {
                Picasso.with(itemView.getContext()).load(url).into(civAvatar);
            }
            txtUserName.setText(friend.getName() + ' ' + friend.getSurname());
            txtCity.setText("City not set");
            if (friend.getCity() != null) {
                txtCity.setText(friend.getCity());
            }
        }
    }
}
