package com.example.john.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        textViewWelcome=(TextView)findViewById(R.id.textViewWelcome);
        buttonLogout=(Button)findViewById(R.id.buttonLogOut);

    }

    public void LogOut(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
