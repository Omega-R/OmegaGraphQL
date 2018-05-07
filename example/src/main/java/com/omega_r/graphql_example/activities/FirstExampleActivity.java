package com.omega_r.graphql_example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.omega_r.graphql_example.R;
import com.omega_r.graphql_example.adapters.TitlesAdapter;
import com.omega_r.graphql_example.app.ExampleApp;
import com.omega_r.graphql_example.model.title.Title;
import com.omega_r.graphql_example.model.title.TitleData;
import com.omega_r.graphql_example.model.title.TitlePosts;
import com.omega_r.libs.omegagraphql.GraphQlJsonRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstExampleActivity extends BaseActivity implements Callback<TitleData> {

    public static Intent createIntent(Context context) {
        return new Intent(context, FirstExampleActivity.class);
    }

    private final TitlesAdapter adapter = new TitlesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_example);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        requestTitles();
    }

    private void requestTitles() {
        showProgress();
        GraphQlJsonRequestBody requestBody = GraphQlJsonRequestBody.from(new TitleData.Query());
        ExampleApp.getRetrofitApi().requestTitleData(requestBody).enqueue(this);
    }

    @Override
    public void onResponse(Call<TitleData> call, Response<TitleData> response) {
        hideProgress();
        TitleData body = response.body();
        if (response.isSuccessful() && body != null) {
            TitlePosts titlePosts = body.getTitlePosts();
            if (titlePosts != null) {
                adapter.setList(titlePosts.getTitles());
            } else {
                showErrorMessage("Titles list is null");
            }
        } else {
            showErrorMessage("Response is not successful or response body is null");
        }
    }

    @Override
    public void onFailure(Call<TitleData> call, Throwable t) {
        hideProgress();
        showToast(t.getMessage());
    }

}
