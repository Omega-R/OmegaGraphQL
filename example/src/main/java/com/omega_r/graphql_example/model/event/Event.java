package com.omega_r.graphql_example.model.event;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omega_r.libs.omegagraphql.QueryParams;
import com.omega_r.libs.omegagraphql.annotations.GraphQlQuery;

import java.lang.reflect.Field;

public class Event {

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("currency")
    private String currency;

    @Expose
    @SerializedName("title")
    private String title;

    @Override
    public String toString() {
        return "Event{\n" +
                "address='" + address + '\'' +
                ",\ncurrency='" + currency + '\'' +
                ",\ntitle='" + title + '\'' + "\n" +
                '}';
    }

    public static class Query implements QueryParams {

        @GraphQlQuery("id")
        private String id;

        public Query(String id) {
            this.id = id;
        }

        @Nullable
        @Override
        public Object getFieldQuery(Class<?> cls, String fieldName) {
            if (cls.equals(Event.class)) {
                return this;
            }
            return null;
        }

        @Override
        public boolean isHideField(Field field, String fieldName) {
            return false;
        }

        @Override
        public boolean isNeedQueryField() {
            return true;
        }

        @Override
        public Class getTargetClass() {
            return EventData.class;
        }
    }
}
