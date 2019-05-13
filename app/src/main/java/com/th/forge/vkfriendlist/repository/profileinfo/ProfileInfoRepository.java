package com.th.forge.vkfriendlist.repository.profileinfo;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.th.forge.vkfriendlist.R;
import com.th.forge.vkfriendlist.data.models.ProfileInfo;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

public class ProfileInfoRepository {
    private static final String TAG = ProfileInfoRepository.class.getSimpleName();
    private ProfileInfoActionCallback callback;

    public ProfileInfoRepository(ProfileInfoActionCallback callback) {
        this.callback = callback;
    }

    public void getProfileInfo() {
        VKRequest request = new VKRequest("account.getProfileInfo",
                VKParameters.from(VKApiConst.FIELDS, "first_name,last_name"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                ProfileInfo profileInfo = new ProfileInfo();
                JsonParser jsonParser = new JsonParser();
                JsonObject parsedJson = jsonParser.parse(response.json.toString()).getAsJsonObject();
                Log.d(TAG, parsedJson.toString());
                JsonObject jsonObject = parsedJson.getAsJsonObject("response");
                profileInfo.setName(jsonObject.get("first_name").getAsString());
                profileInfo.setLastName(jsonObject.get("last_name").getAsString());
                callback.onProfileInfoLoaded(profileInfo);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                callback.onError(R.string.friends_loading_error);
            }
        });
    }
}
