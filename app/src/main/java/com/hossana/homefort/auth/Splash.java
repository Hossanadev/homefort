package com.hossana.homefort.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hossana.homefort.R;

public class Splash extends AppCompatActivity {
    Animation splashTopAnim, splashBottomAnim;
    TextView splash_title, splash_slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        splashTopAnim = AnimationUtils.loadAnimation(this, R.anim.splash_top);
        splashBottomAnim = AnimationUtils.loadAnimation(this, R.anim.splash_bottom);

        splash_title = findViewById(R.id.splash_app_name);
        splash_slogan = findViewById(R.id.splash_slogan);

        splash_title.setAnimation(splashTopAnim);
        splash_slogan.setAnimation(splashBottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}