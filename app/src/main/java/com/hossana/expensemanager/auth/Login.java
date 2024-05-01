package com.hossana.expensemanager.auth;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.hossana.expensemanager.Main;
import com.hossana.expensemanager.R;
import com.hossana.expensemanager.utils.DarkModeChecker;
import com.hossana.expensemanager.utils.LoadingIndicatorManager;

public class Login extends AppCompatActivity {
    EditText login_email, login_password;
    TextView forgotPass_link, create_account_link;
    MaterialButton login_button;
    ProgressBar loading_indicator;
    boolean isDarkMode;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        isDarkMode = DarkModeChecker.isDarkModeEnabled(getResources().getConfiguration());

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        loading_indicator = findViewById(R.id.loading_indicator);
        forgotPass_link = findViewById(R.id.forgotPass_link);
        create_account_link = findViewById(R.id.create_account_link);

        if (!isDarkMode) {
            login_email.setBackgroundResource(R.drawable.textinput_bg_light);
            login_password.setBackgroundResource(R.drawable.textinput_bg_light);
            loading_indicator.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }

        login_button.setOnClickListener(v -> {
            LoadingIndicatorManager.showLoading(loading_indicator, login_button);
            new Handler().postDelayed(() -> {
                LoadingIndicatorManager.hideLoading(loading_indicator, login_button, "Log In");
                intent = new Intent(Login.this, Main.class);
                startActivity(intent);
                login_button.setClickable(true);
                finish();
            }, 5000);
        });

        forgotPass_link.setOnClickListener(v -> {
            intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        });

        create_account_link.setOnClickListener(v -> {
            intent = new Intent(Login.this, CreateAccount.class);
            startActivity(intent);
        });
    }
}