package com.example.john.logintest;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

public class SplashScreen extends Activity {
    ProgressBar progressBar1;
    private static int x = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar1.getProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },x);
    }
}
