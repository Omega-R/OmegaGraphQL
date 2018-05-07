package com.omega_r.graphql_example.model.title;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("title")
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

}
