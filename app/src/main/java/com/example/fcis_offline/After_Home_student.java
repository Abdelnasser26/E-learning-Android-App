package com.example.fcis_offline;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class After_Home_student extends AppCompatActivity {

//    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_after__home_student);
        final DBHelper db = new DBHelper(this);
        final int id=getIntent().getIntExtra("std1",0);
        student stt=db.searchstd(id);
        Toast.makeText(After_Home_student.this,stt.getUsername(),Toast.LENGTH_SHORT).show();

        final ArrayList<subject> su= (ArrayList<subject>) getIntent().getExtras().getSerializable("array");
        final ListView view=(ListView)findViewById(R.id.subjectview);
        ArrayAdapter<String> ar=new ArrayAdapter<String>(After_Home_student.this,android.R.layout.simple_list_item_1);
        view.setAdapter(ar);
        int i=0;
        while (i<su.size()){
            ar.add(su.get(i).name);
            i++;
        }
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int k=i;
                subject s=su.get(i);
                Cursor cm=db.fetchvideo(s.name);
                while (!cm.isAfterLast()&&cm!=null){
                    s.video=new video(cm.getString(0),cm.getString(1));
                    Toast.makeText(After_Home_student.this,cm.getString(0)+"     "+cm.getString(1),Toast.LENGTH_LONG).show();
                    cm.moveToNext();
            }
                Intent iy  = new Intent(Intent.ACTION_VIEW, Uri.parse(s.video.url));
                startActivity(iy);
        }});


    }
}