package com.omega_r.graphql_example.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.omega_r.graphql_example.R;
import com.omega_r.graphql_example.app.ExampleApp;
import com.omega_r.graphql_example.model.title.TitleData;
import com.omega_r.libs.omegagraphql.GraphQlJsonRequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_first).setOnClickListener(this);
        findViewById(R.id.button_second).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_first:
                startActivity(FirstExampleActivity.createIntent(this));
                break;
            case R.id.button_second:
                startActivity(SecondExampleActivity.createIntent(this));
                break;
        }
    }

}
