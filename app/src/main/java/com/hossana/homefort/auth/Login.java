package com.hossana.homefort.auth;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.hossana.homefort.Main;
import com.hossana.homefort.R;
import com.hossana.homefort.utils.DarkModeChecker;
import com.hossana.homefort.utils.HideKeyboard;
import com.hossana.homefort.utils.IsValidEmail;
import com.hossana.homefort.utils.IsValidInput;
import com.hossana.homefort.utils.LoadingIndicatorManager;

public class Login extends AppCompatActivity {
    EditText login_email, login_password;
    TextView forgotPass_link, create_account_link;
    MaterialButton login_button;
    ProgressBar loading_indicator;
    boolean isDarkMode;
    Intent intent;
    RelativeLayout login_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_screen), (v, insets) -> {
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
        login_screen = findViewById(R.id.login_screen);

        if (!isDarkMode) {
            login_email.setBackgroundResource(R.drawable.textinput_bg_light);
            login_password.setBackgroundResource(R.drawable.textinput_bg_light);
            loading_indicator.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            create_account_link.setBackgroundResource(R.drawable.textinput_bg_light);
            forgotPass_link.setBackgroundResource(R.drawable.textinput_bg_light);
        }

        login_screen.setOnClickListener(v -> {
            HideKeyboard.hideKeyboard(this);
            login_email.clearFocus();
            login_password.clearFocus();
            login_email.setError(null);
            login_password.setError(null);
            IsValidInput.isValid(getApplicationContext(), login_email, isDarkMode);
            IsValidInput.isValid(getApplicationContext(), login_password, isDarkMode);
        });

        login_button.setOnClickListener(v -> {
            String emailValue = login_email.getText().toString().trim(),
                   passwordValue = login_password.getText().toString().trim();

            if (!emailValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), login_email, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), login_email, "Required", isDarkMode);
                return;
            }

            if (!IsValidEmail.isValidEmail(emailValue)){
                IsValidInput.isNotValid(getApplicationContext(), login_email, "Invalid Email", isDarkMode);
                return;
            }

            if (!passwordValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), login_password, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), login_password, "Required", isDarkMode);
                return;
            }

            LoadingIndicatorManager.showLoading(loading_indicator, login_button);
            create_account_link.setClickable(false);
            forgotPass_link.setClickable(false);
            new Handler().postDelayed(() -> {
                LoadingIndicatorManager.hideLoading(loading_indicator, login_button, "Log In");
                intent = new Intent(Login.this, Main.class);
                startActivity(intent);
                login_button.setClickable(true);
                create_account_link.setClickable(true);
                forgotPass_link.setClickable(true);
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