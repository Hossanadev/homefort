package com.hossana.homefort.auth;

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
import com.hossana.homefort.R;
import com.hossana.homefort.utils.DarkModeChecker;
import com.hossana.homefort.utils.LoadingIndicatorManager;

public class CreateAccount extends AppCompatActivity {
    TextView login_link;
    MaterialButton createAccount_btn;
    ProgressBar createAccount_loading_indicator;
    EditText user_name, email, password;
    boolean isDarkMode;
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

        isDarkMode = DarkModeChecker.isDarkModeEnabled(getResources().getConfiguration());
        user_name = findViewById(R.id.createAccount_userName);
        email = findViewById(R.id.createAccount_email);
        password = findViewById(R.id.createAccount_password);

        if (!isDarkMode) {
            user_name.setBackgroundResource(R.drawable.textinput_bg_light);
            email.setBackgroundResource(R.drawable.textinput_bg_light);
            password.setBackgroundResource(R.drawable.textinput_bg_light);
            createAccount_loading_indicator.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }

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