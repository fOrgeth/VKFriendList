package com.th.forge.vkfriendlist.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class VKUser implements Parcelable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String photo;
    private Boolean deactivated;

    {
        id = 0;
        firstName = "";
        lastName = "";
        photo = "";
        deactivated = false;
    }

    public VKUser(Integer id, String firstName, String lastName, String photo, boolean deactivated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.deactivated = deactivated;
    }

    protected VKUser(Parcel parcel) {
        /*if (parcel.readByte() == 0) {
            id = null;
        } else {
            id = parcel.readInt();
        }*/
        id = parcel.readInt();
        firstName = parcel.readString();
        lastName = parcel.readString();
        photo = parcel.readString();
        byte tmpDeactivated = parcel.readByte();
        deactivated = tmpDeactivated != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(photo);
        parcel.writeByte((byte) (deactivated ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VKUser> CREATOR = new Creator<VKUser>() {
        @Override
        public VKUser createFromParcel(Parcel parcel) {
            return new VKUser(parcel);
        }

        @Override
        public VKUser[] newArray(int size) {
            return new VKUser[size];
        }

    };

    public static VKUser parse(JSONObject json) {
        return new VKUser(
                json.optInt("id", 0),
                json.optString("first_name", ""),
                json.optString("last_name", ""),
                json.optString("photo_100", ""),
                json.optBoolean("deactivated", false));
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }
}
