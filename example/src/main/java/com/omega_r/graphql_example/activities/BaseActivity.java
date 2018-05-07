package com.omega_r.graphql_example.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Nullable
    private ProgressDialog progressDialog;

    protected void showProgress() {
        if (progressDialog == null) {
            progressDialog = createProgressDialog();
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    protected void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private ProgressDialog createProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    protected void showErrorMessage(@Nullable String msg) {
        showToast(TextUtils.isEmpty(msg) ? "Some problem occurred" : msg);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
