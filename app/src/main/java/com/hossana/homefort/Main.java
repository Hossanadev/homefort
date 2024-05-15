package com.hossana.homefort;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hossana.homefort.utils.DarkModeChecker;
import com.hossana.homefort.utils.GreetingHandler;

public class Main extends AppCompatActivity {
    TextView greeting;
    BottomNavigationView bottom_navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setStatusBarColor(Color.parseColor("#008000"));
        setContentView(R.layout.main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        greeting = findViewById(R.id.greeting);
        bottom_navigation_view = findViewById(R.id.bottom_navigation_view);

        greeting.setText(GreetingHandler.getGreeting());

        if (!DarkModeChecker.isDarkModeEnabled(getResources().getConfiguration())) {
            bottom_navigation_view.setBackgroundResource(R.drawable.bottom_nav_bg_light);
        }
    }
}