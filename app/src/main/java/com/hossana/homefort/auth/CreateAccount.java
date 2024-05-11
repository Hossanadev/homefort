package com.hossana.homefort.auth;

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
import com.hossana.homefort.R;
import com.hossana.homefort.utils.DarkModeChecker;
import com.hossana.homefort.utils.HideKeyboard;
import com.hossana.homefort.utils.IsValidEmail;
import com.hossana.homefort.utils.IsValidInput;
import com.hossana.homefort.utils.LoadingIndicatorManager;
import com.hossana.homefort.utils.ToastNotification;

public class CreateAccount extends AppCompatActivity {
    TextView login_link;
    MaterialButton createAccount_btn;
    ProgressBar createAccount_loading_indicator;
    EditText user_name, email, password, confirm_password;
    boolean isDarkMode;
    RelativeLayout create_account_screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create_account_screen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login_link = findViewById(R.id.login_link);
        createAccount_btn = findViewById(R.id.createAccount_button);
        createAccount_loading_indicator = findViewById(R.id.createAccount_loading_indicator);
        create_account_screen = findViewById(R.id.create_account_screen);

        login_link.setOnClickListener(v -> {
            finish();
        });

        isDarkMode = DarkModeChecker.isDarkModeEnabled(getResources().getConfiguration());
        user_name = findViewById(R.id.createAccount_userName);
        email = findViewById(R.id.createAccount_email);
        password = findViewById(R.id.createAccount_password);
        confirm_password = findViewById(R.id.createAccount_confirmPassword);

        if (!isDarkMode) {
            user_name.setBackgroundResource(R.drawable.textinput_bg_light);
            email.setBackgroundResource(R.drawable.textinput_bg_light);
            password.setBackgroundResource(R.drawable.textinput_bg_light);
            confirm_password.setBackgroundResource(R.drawable.textinput_bg_light);
            createAccount_loading_indicator.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            login_link.setBackgroundResource(R.drawable.textinput_bg_light);
        }

        create_account_screen.setOnClickListener(v -> {
            HideKeyboard.hideKeyboard(this);
            user_name.clearFocus();
            email.clearFocus();
            password.clearFocus();
            confirm_password.clearFocus();
            user_name.setError(null);
            email.setError(null);
            password.setError(null);
            confirm_password.setError(null);
            IsValidInput.isValid(getApplicationContext(), user_name, isDarkMode);
            IsValidInput.isValid(getApplicationContext(), email, isDarkMode);
            IsValidInput.isValid(getApplicationContext(), password, isDarkMode);
            IsValidInput.isValid(getApplicationContext(), confirm_password, isDarkMode);
        });

        createAccount_btn.setOnClickListener(v -> {
            String userNameValue = user_name.getText().toString().trim(),
                   emailValue = email.getText().toString().trim(),
                   passwordValue = password.getText().toString().trim(),
                   confirmPasswordValue = confirm_password.getText().toString().trim();
            if (!userNameValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), user_name, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), user_name, "Required", isDarkMode);
                return;
            }

            if (!emailValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), email, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), email, "Required", isDarkMode);
                return;
            }

            if (!IsValidEmail.isValidEmail(emailValue)){
                IsValidInput.isNotValid(getApplicationContext(), email, "Invalid Email", isDarkMode);
                return;
            }

            if (!passwordValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), password, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), password, "Required", isDarkMode);
                return;
            }

            if (!confirmPasswordValue.isEmpty()) {
                IsValidInput.isValid(getApplicationContext(), confirm_password, isDarkMode);
            } else {
                IsValidInput.isNotValid(getApplicationContext(), confirm_password,"Required", isDarkMode);
                return;
            }

            if (!confirmPasswordValue.equals(passwordValue)) {
                IsValidInput.isNotValid(getApplicationContext(), confirm_password,"Password Didn't Match", isDarkMode);
                return;
            }

            LoadingIndicatorManager.showLoading(createAccount_loading_indicator, createAccount_btn);
            login_link.setClickable(false);
            new Handler().postDelayed(() -> {
                ToastNotification.toastNotification(this, "Account created successfully", "success");
                LoadingIndicatorManager.hideLoading(createAccount_loading_indicator, createAccount_btn, "Create Account");
                finish();
                createAccount_btn.setClickable(true);
                login_link.setClickable(true);
            }, 5000);
        });
    }
}