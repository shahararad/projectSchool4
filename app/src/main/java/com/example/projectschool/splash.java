package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

public class splash extends AppCompatActivity {
    public ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bar= findViewById(R.id.progressBar);
        new CountDownTimer(3000, 1000) { // מתאים זמן מחיה כאן


            @Override
            public void onTick(long millisUntilFinished) {
                // לא נפעל כאן
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                // מבצע פעולות כאשר המונה מסתיים
                startActivity(intent);
                finish(); // למניעת חזרה למסך הזה
            }
        }.start();
    }
}