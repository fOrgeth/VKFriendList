package com.th.forge.vkfriendlist.models;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VKFriendsRequest extends VKRequest<List<VKUser>> {
    public VKFriendsRequest(@NotNull Integer uid) {
        super("friend.get");
        if (uid != 0) {
            addParam("user_id", uid);
        }
        addParam("fields", "photo_100");
    }

    @Override
    public List<VKUser> parse(@NotNull JSONObject r) throws Exception {
        JSONArray users = r.getJSONObject("response").getJSONArray("items");
        List<VKUser> result = new ArrayList<>();
        for (int i = 0; i < users.length(); i++) {
            result.add(VKUser.parse(users.getJSONObject(i)));
        }
        return result;
    }
}
