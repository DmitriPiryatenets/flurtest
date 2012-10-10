package com.social_api.facebook.beans;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Daniel Goncharov
 * Date: 28.08.12
 * Time: 11:36
 */
public class FacebookUser {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private long id;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
