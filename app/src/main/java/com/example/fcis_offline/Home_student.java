package com.example.fcis_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Home_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_student);

        final DBHelper db = new DBHelper(this);
        final int id=getIntent().getIntExtra("std",0);

        student s=db.searchstd(id);
        //Toast.makeText(Home_student.this,s.getUsername(),Toast.LENGTH_SHORT).show();


        Button show=findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Year y;
                RadioButton rb1=(RadioButton)findViewById(R.id.r1);
                RadioButton rb2=(RadioButton)findViewById(R.id.r2);
                RadioButton rb3=(RadioButton)findViewById(R.id.r3);
                RadioButton rb4=(RadioButton)findViewById(R.id.r4);
                RadioButton semester1=(RadioButton)findViewById(R.id.r5);
                RadioButton semester2=(RadioButton)findViewById(R.id.r6);

                if(rb1.isChecked()){
                    y=new Year("1");
                }
                else if(rb2.isChecked()){
                    y=new Year("2");
                }
                else if(rb3.isChecked()){
                    y=new Year("3");
                }
                else {
                    y=new Year("4");
                }

                Cursor cm=db.fetchsubject(""+y.getId());
                while (!cm.isAfterLast()&&cm!=null){
                    subject sub=new subject(cm.getString(0),cm.getString(1),cm.getString(2));
                    if(sub.term.equals("first")){
                        y.subfirst.add(sub);
                    }
                    else{
                        y.subsecond.add(sub);
                    }
                    //Toast.makeText(Home_student.this,cm.getString(0)+"     "+cm.getString(1)+",     "+cm.getString(2),Toast.LENGTH_LONG).show();
                    cm.moveToNext();
                }
                Intent iy = new Intent(getApplicationContext(),After_Home_student.class);

                if(semester1.isChecked())
                    iy.putExtra("array", (Serializable) y.subfirst);
                else
                    iy.putExtra("array",(Serializable) y.subsecond);
                iy.putExtra("std1",id);

                startActivity(iy);

            }


        });
        }
}