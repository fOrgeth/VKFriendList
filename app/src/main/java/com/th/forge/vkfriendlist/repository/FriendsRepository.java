package com.th.forge.vkfriendlist.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.th.forge.vkfriendlist.models.Friend;
import com.th.forge.vkfriendlist.presenters.FriendListPresenter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

public class FriendsRepository {

    private FriendListPresenter friendListPresenter;

    public FriendsRepository(FriendListPresenter friendListPresenter) {
        this.friendListPresenter = friendListPresenter;
    }

    public void loadFriends() {
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.COUNT, 100,
                VKApiConst.FIELDS, "city, country, photo_100, online"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                List<Friend> friendList = new ArrayList<>();
                JsonParser jsonParser = new JsonParser();
                JsonObject parsedJson = jsonParser.parse(response.json.toString()).getAsJsonObject();
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
                friendListPresenter.friendsLoaded(friendList);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                friendListPresenter.showError("Load error");
            }
        });
    }
}
