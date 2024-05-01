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

public class CreateAccount extends AppCompatActivity {
    TextView login_link;
    MaterialButton createAccount_btn;
    ProgressBar createAccount_loading_indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login_link = findViewById(R.id.login_link);
        createAccount_btn = findViewById(R.id.createAccount_button);
        createAccount_loading_indicator = findViewById(R.id.createAccount_loading_indicator);

        login_link.setOnClickListener(v -> {
            finish();
        });

        createAccount_btn.setOnClickListener(v -> {
            LoadingIndicatorManager.showLoading(createAccount_loading_indicator, createAccount_btn);
            login_link.setClickable(false);
            new Handler().postDelayed(() -> {
                LoadingIndicatorManager.hideLoading(createAccount_loading_indicator, createAccount_btn, "Create Account");
                finish();
                createAccount_btn.setClickable(true);
                login_link.setClickable(true);
            }, 5000);
        });
    }
}