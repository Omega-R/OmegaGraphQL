package com.omega_r.graphql_example.model.title;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.omega_r.libs.omegagraphql.QueryParams;

import java.lang.reflect.Field;
import java.util.List;

public class TitleData {

    @SerializedName("data")
    private TitlePosts mTitlePosts;

    public TitlePosts getTitlePosts() {
        return mTitlePosts;
    }

    public static class Query implements QueryParams {

        @Nullable
        @Override
        public Object getFieldQuery(Class<?> cls, String fieldName) {
            return null;
        }

        @Override
        public boolean isHideField(Field field, String fieldName) {
            return false;
        }

        @Override
        public boolean isNeedQueryField() {
            return false;
        }

        @Override
        public Class getTargetClass() {
            return TitlePosts.class;
        }
    }

}
