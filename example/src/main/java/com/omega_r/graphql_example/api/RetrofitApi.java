package com.omega_r.graphql_example.api;

import com.omega_r.graphql_example.model.event.Data;
import com.omega_r.graphql_example.model.title.TitleData;
import com.omega_r.libs.omegagraphql.GraphQlJsonRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("https://1jzxrj179.lp.gql.zone/graphql")
    Call<TitleData> requestTitleData(@Body GraphQlJsonRequestBody queryObject);

    @POST("https://www.universe.com/graphql")
    Call<Data> requestEventData(@Body GraphQlJsonRequestBody queryObject);

}
