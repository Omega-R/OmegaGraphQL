package com.omega_r.graphql_example.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @Expose
    @SerializedName("data")
    private EventData eventData;

    public EventData getEventData() {
        return eventData;
    }

}
