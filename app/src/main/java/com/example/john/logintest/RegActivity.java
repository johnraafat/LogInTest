package com.example.john.logintest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.jar.Attributes;

public class RegActivity extends AppCompatActivity {
    private TextInputEditText editTextPass1;
    private Button button;
    private TextView textView5;
    private EditText editTextName;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
        }
        progressDialog=new ProgressDialog(this);
        editTextPass1 = (TextInputEditText) findViewById(R.id.editTextPass1);
        editTextName = (EditText) findViewById(R.id.editTextName);
        textView5 = (TextView) findViewById(R.id.textView5);
        button = (Button) findViewById(R.id.button);
    }

    public void Register(View view) {

        String name= editTextName.getText().toString().trim();
        String pass = editTextPass1.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter Your E-Mail And PassWord",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(name,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else {
                    textView5.setText("Sign Up Failed Please Try Again");
                }
            }
        });
    }
}
