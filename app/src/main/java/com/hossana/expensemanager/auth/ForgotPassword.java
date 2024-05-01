package com.hossana.expensemanager.auth;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.hossana.expensemanager.R;
import com.hossana.expensemanager.utils.LoadingIndicatorManager;

public class ForgotPassword extends AppCompatActivity {
    TextView login_link;
    MaterialButton retrieve_password_btn;
    ProgressBar forgotPass_loading_indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login_link = findViewById(R.id.login_link);
        retrieve_password_btn = findViewById(R.id.retrievePass_button);
        forgotPass_loading_indicator = findViewById(R.id.forgotPass_loading_indicator);

        login_link.setOnClickListener(v -> {
            finish();
        });

        retrieve_password_btn.setOnClickListener(v -> {
            LoadingIndicatorManager.showLoading(forgotPass_loading_indicator, retrieve_password_btn);
            login_link.setClickable(false);
            new Handler().postDelayed(() -> {
                LoadingIndicatorManager.hideLoading(forgotPass_loading_indicator, retrieve_password_btn, "Retrieve Password");
                finish();
                retrieve_password_btn.setClickable(true);
                login_link.setClickable(true);
            }, 5000);
        });
    }
}