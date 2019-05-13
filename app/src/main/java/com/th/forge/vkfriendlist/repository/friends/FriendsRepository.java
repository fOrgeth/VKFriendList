package com.th.forge.vkfriendlist.repository.friends;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.data.models.Friend;
import com.th.forge.vkfriendlist.ui.list.FriendListPresenter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

public class FriendsRepository {
    private static final String TAG = FriendsRepository.class.getSimpleName();

    private FriendListActionCallback callback;

    public FriendsRepository(FriendListActionCallback callback) {
        this.callback = callback;
    }

    public void loadFriends() {
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.COUNT, 5,
                VKApiConst.FIELDS, "city, country, photo_100, online"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                List<Friend> friendList = new ArrayList<>();
                JsonParser jsonParser = new JsonParser();
                JsonObject parsedJson = jsonParser.parse(response.json.toString()).getAsJsonObject();
                Log.d(TAG,parsedJson.toString());
                JsonArray parsedArray = parsedJson.get("response").getAsJsonObject().getAsJsonArray("items");
                for (int i = 0; i < parsedArray.size(); i++) {
                    JsonObject jsonObject = parsedArray.get(i).getAsJsonObject();
                    Friend friend = new Friend();
                    if (jsonObject.get("city") != null) {
                        friend.setCity(jsonObject.get("city").getAsJsonObject().get("title").getAsString());
                    }
                    friend.setName(jsonObject.get("first_name").getAsString());
                    friend.setSurname(jsonObject.get("last_name").getAsString());
                    friend.setAvatar(jsonObject.get("photo_100").getAsString());
                    friend.setOnline(jsonObject.get("online").getAsInt() == 1);
                    friendList.add(friend);
                }
                callback.onFriendsLoaded(friendList);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                callback.onError(R.string.friends_loading_error);
            }
        });
    }
}
