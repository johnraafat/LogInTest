package com.example.john.logintest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogIn;
    private TextInputEditText editTextPass1;
    private EditText editTextName;
    private Button buttonSignUp;
    private Button buttonForgetPass;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        editTextPass1 = (TextInputEditText) findViewById(R.id.editTextPass1);
        editTextName = (EditText)findViewById(R.id.editTextName);
        buttonLogIn = (Button)findViewById(R.id.buttonLogIn);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        buttonForgetPass = (Button)findViewById(R.id.buttonForgetPass);
        textView2 = (TextView)findViewById(R.id.textView2);
    }

    public void ForgetPassword(View view) {
        editTextPass1.setText(null);
    }
    public void GoToSignUp(View view) {
        Intent intent=new Intent(this,RegActivity.class);
        startActivity(intent);
    }

    public void LogIn(View view) {
        String name = editTextName.getText().toString().trim();
        String pass = editTextPass1.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter Your E-Mail And PassWord",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                }else{
                    textView2.setText("Sign In Failed");
                    return;
                }
            }
        });
    }
}
