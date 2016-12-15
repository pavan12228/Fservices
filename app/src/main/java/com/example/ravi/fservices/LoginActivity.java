package com.example.ravi.fservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvlogin,tvsignup;
    private EditText emails,passwords;
    private Button btnsignin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        tvlogin= (TextView) findViewById(R.id.tvLogin);
        tvsignup= (TextView) findViewById(R.id.tvSignup);
        emails= (EditText) findViewById(R.id.editEmail);
        passwords= (EditText) findViewById(R.id.editTextPassword);
        btnsignin= (Button) findViewById(R.id.btnSignin);
        btnsignin.setOnClickListener(this);
        tvsignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

             if(v==btnsignin){
               String email=emails.getText().toString().trim();
               String password=passwords.getText().toString().trim();
                 if(TextUtils.isEmpty(email))
                 {
                     Toast.makeText(this, "please enter email ", Toast.LENGTH_SHORT).show();
                     return;
                 }

                   if(TextUtils.isEmpty(password)){

                       Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
                       return;
                   }
                   progressDialog.setMessage("logging in to Your account");
                  progressDialog.show();
                 firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                         if(task.isSuccessful()){

                             finish();
                           startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                         }




                     }
                 });


             }

              if(v==tvsignup){


                  startActivity(new Intent(getApplicationContext(),MainActivity.class));

              }





    }
}
