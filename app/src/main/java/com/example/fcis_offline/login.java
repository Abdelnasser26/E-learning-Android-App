package com.example.fcis_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText emaill, passwordd;
    Button button;
    TextView tvSignUp;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        emaill = findViewById(R.id.email1);
        passwordd = findViewById(R.id.pass1);
        button = findViewById(R.id.buttoon);
        tvSignUp = findViewById(R.id.tvSignUpSecond);
        db = new DBHelper(this);




        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish(); }});
    //*******************************************************

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = emaill.getText().toString();
                String paass = passwordd.getText().toString();

                //check if fields empty
                if (user.equals("")||paass.equals("") ){
                    Toast.makeText(login.this,"Please enter the fields",Toast.LENGTH_SHORT).show();
                }
                // end if checker

                else { //**
                        int checker = db.checkPassword(user, paass);

                        if (checker !=-1) {
                            Intent i = new Intent(login.this, Home_student.class);
                            Toast.makeText(login.this, " Login in Successfully ", Toast.LENGTH_SHORT).show();
                            i.putExtra("std",checker);
                            startActivity(i);
                            finish();
                        } else { Toast.makeText(login.this, "Failed Login in", Toast.LENGTH_SHORT).show(); }
                    }
                //end else **
        }});

    }}//end main login