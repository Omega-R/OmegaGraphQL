package com.omega_r.graphql_example.model.title;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TitlePosts {

    @SerializedName("posts")
    private List<Title> mTitleList;

    public List<Title> getTitles() {
        return mTitleList;
    }

}
