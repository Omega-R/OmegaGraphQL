package com.omega_r.graphql_example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.omega_r.graphql_example.R;
import com.omega_r.graphql_example.app.ExampleApp;
import com.omega_r.graphql_example.model.event.Data;
import com.omega_r.graphql_example.model.event.Event;
import com.omega_r.libs.omegagraphql.GraphQlJsonRequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondExampleActivity extends BaseActivity implements Callback<Data> {

    private TextView messageLabel;

    public static Intent createIntent(Context context) {
        return new Intent(context, SecondExampleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_example);
        messageLabel = findViewById(R.id.label_message);
        request();
    }

    private void request() {
        showProgress();
        GraphQlJsonRequestBody requestBody = GraphQlJsonRequestBody.from(new Event.Query("5879ad8f6672e70036d58ba5"));
        ExampleApp.getRetrofitApi().requestEventData(requestBody).enqueue(this);
    }


    @Override
    public void onResponse(Call<Data> call, Response<Data> response) {
        hideProgress();
        if (response.isSuccessful() && response.body() != null) {
            Data body = response.body();
            assert body != null;
            messageLabel.setText(body.getEventData().getEvent().toString());
        } else {
            showErrorMessage("Response is not successful or response body is null");
        }
    }

    @Override
    public void onFailure(Call<Data> call, Throwable t) {
        hideProgress();
        showToast(t.getMessage());
    }
}
