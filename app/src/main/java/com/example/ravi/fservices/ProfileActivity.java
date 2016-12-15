package com.example.ravi.fservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_profile);
        button= (Button) findViewById(R.id.logout);
        textView= (TextView) findViewById(R.id.textemail);
        button.setOnClickListener(this);
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        textView.setText("welcome"+firebaseUser.getEmail());

    }

    @Override
    public void onClick(View v) {

               firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }
}
