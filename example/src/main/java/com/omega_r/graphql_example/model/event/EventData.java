package com.omega_r.graphql_example.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventData {

    @Expose
    @SerializedName("event")
    private Event event;

    public Event getEvent() {
        return event;
    }
}
