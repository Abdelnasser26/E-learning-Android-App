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

public class MainActivity extends AppCompatActivity {
    EditText email, password, confirmpassword, username,id;
    Button button;
    TextView tvlogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //id = findViewById(R.id.ID);
       // year = findViewById(R.id.Year);
        email = findViewById(R.id.emaill);
        button = findViewById(R.id.buttonn);
        password = findViewById(R.id.pass);
        confirmpassword = findViewById(R.id.ccpass);
        username = findViewById(R.id.usern);
         tvlogin = findViewById(R.id.tvlogin);
        db = new DBHelper(this);
        db.data_sub();
        db.data_video();
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i); }});
     //*********************************** To Register ****************************

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em_user = email.getText().toString();
                String pass = password.getText().toString();
                String re_pass = confirmpassword.getText().toString();
                String user = username.getText().toString();
                //String idd = id.getText().toString();
               // String yearr = year.getText().toString();


                //check fields not empty
                if (em_user.equals("" )||pass.equals("")|| re_pass.equals("") ||user.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all the fields ..",Toast.LENGTH_SHORT).show();
                }
                // if NOT **
                else{
                    if (pass.equals(re_pass)){
                        boolean checkuser = db.checkuser(user);
                        if (checkuser == false){
                            boolean insert = db.insertion(user,pass,em_user);
                            if (insert == true){
                                Toast.makeText(MainActivity.this, "Successfully Registerion .." , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),login.class);
                                startActivity(i);
                                finish();
                            }
                            else { Toast.makeText(MainActivity.this, "Failed Registerion .." , Toast.LENGTH_SHORT).show(); }

                        }// else for check user
                        else { Toast.makeText(MainActivity.this, " User already exists ! Please sign in.." , Toast.LENGTH_SHORT).show(); }

                    }// else for repeated password
                    else { Toast.makeText(MainActivity.this, "Passwords not matching! Try again.." , Toast.LENGTH_SHORT).show(); }

                }

            }
        });









    }}//end main