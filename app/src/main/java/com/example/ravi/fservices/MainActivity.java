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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etemail, etpassword;
    private TextView registration, tvSignin;
    private Button btnSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        registration = (TextView) findViewById(R.id.Registration);
        etemail = (EditText) findViewById(R.id.Emails);
        etpassword = (EditText) findViewById(R.id.Passwords);
        btnSignup = (Button) findViewById(R.id.BtnSignup);
        tvSignin = (TextView) findViewById(R.id.TvSignin);
        btnSignup.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnSignup) {


            String email = etemail.getText().toString().trim();
            String pass = etpassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {

                Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(pass)) {

                Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.setMessage("please wait Registering...........");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    } else {
                        Toast.makeText(MainActivity.this, " Registration failure", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }

            if (v == tvSignin) {
                 startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }


        }

    }
