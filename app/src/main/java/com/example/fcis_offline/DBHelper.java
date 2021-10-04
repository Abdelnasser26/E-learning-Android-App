package com.example.fcis_offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "student_db";


    public DBHelper(Context context)
    {
        super(context, DBName, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(" CREATE TABLE users(username TEXT,password TEXT,id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT) ");

        MyDB.execSQL(" CREATE TABLE subject (name TEXT primary key,term TEXT,discribtion TEXT,year TEXT)");
        MyDB.execSQL(" CREATE TABLE video (title TEXT,url TEXT,name_subject TEXT UNIQUE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase My_DB, int i, int i1) {
        My_DB.execSQL(" DROP TABLE IF EXISTS users ");

        My_DB.execSQL(" DROP TABLE IF EXISTS subject ");
        My_DB.execSQL(" DROP TABLE IF EXISTS video ");
        onCreate(My_DB);

    }

    public boolean ins_video(String title , String url ,String name_subject){
        SQLiteDatabase dbx = getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("title",title);
        CV.put("url",url);
        CV.put("name_subject",name_subject);
        long result = dbx.insert("video",null,CV);
        dbx.close();
        Log.d("Saved video !", "saved video info to DB");
        return result != -1;
    }
    public boolean ins_subject(String name , String term ,String year ){
        SQLiteDatabase dbm = getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("name",name);
        CV.put("term",term);
        CV.put("year",year);
        long result = dbm.insert("subject",null,CV);
        dbm.close();
        Log.d("Saved subject !", "saved subject info to DB");
        return result != -1;
    }


    // insert regisgertion of student in DataBase
    public boolean insertion(String user , String pass,String email){
        SQLiteDatabase dbj = getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("username",user);
        CV.put("password",pass);
        //CV.put("id",id);
        CV.put("email",email);
        long result = dbj.insert("users",null,CV);
        Log.d("Saved!", "saved to DB");
        dbj.close();
        return result != -1;
    }
    //**************************************************************************************************
    //check if student username is founded
    public boolean checkuser(String userr){
        SQLiteDatabase dbo = this.getReadableDatabase();
        Cursor cursor = dbo.query("users", new String[]{"username"} , "username =?", new String[]{userr}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        dbo.close();

        if (count > 0)
            return true;
        else
            return false;
    }
//********************************************************************************************

    //Check login stud
    public int checkPassword(String user , String pass){
        SQLiteDatabase dbs = getReadableDatabase();
        Cursor cursor = dbs.query("users", new String[]{"id"} ,"username like ?" + " and " + "password like ?",new String[]{user, pass},null,null,null);
        int count = cursor.getCount();
        //cursor.close();
        dbs.close();
        if(cursor!=null)
            cursor.moveToFirst();

        if (count > 0) {
            int y = Integer.parseInt(cursor.getString(0).toString());
            return y;
        }
        else
            return -1;
    }
    //*******************************************************************************************
    public student searchstd(int id){
        SQLiteDatabase dby = this.getReadableDatabase();
        Cursor cursor = dby.query("users", new String[]{"username","id","password","email"}
        ,"id like ?",new String[]{String.valueOf(id)},null,null,null);

        int count = cursor.getCount();
        dby.close();
        if(cursor!=null)
            cursor.moveToFirst();
        if (count > 0) {
            student s=new student(cursor.getString(0)
                                  ,cursor.getString(1)
                                  ,cursor.getString(2)
                                  ,cursor.getString(3));

            cursor.close();
            return s;
        }
        else {
            cursor.close();
            return null;
        }
    }

    //*******************************************************************************************
    public Cursor fetchsubject(String year){

        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursort = dbr.query("subject", new String[]{"name","term","discribtion"} ,"year like ?",new String[]{String.valueOf(year)},null,null,null);
        int g=cursort.getCount();
        dbr.close();
        if(cursort!=null)
            cursort.moveToFirst();
        return  cursort;
    }

    public Cursor fetchvideo(String name){

        SQLiteDatabase dbp = this.getReadableDatabase();
        Cursor cursort = dbp.query("video", new String[]{"title","url"} ,"name_subject like ?",new String[]{String.valueOf(name)},null,null,null);
        int g=cursort.getCount();
        dbp.close();
        if(cursort!=null)
            cursort.moveToFirst();
        return  cursort;
    }
    public void data_sub(){
        //****************************** First Year *****************************************
        ins_subject("Electronics","first","1");
        ins_subject("English","first","1");
        ins_subject("Managment","first","1");
        ins_subject("Math 1","first","1");
        ins_subject("Introduction To Computer","first","1");
        ins_subject("Physics 1","first","1");
        //***********
        ins_subject("Physics 2","second","1");
        ins_subject("Electronics 2","second","1");
        ins_subject("Structre Programing","second","1");
        ins_subject("Human Rights","second","1");
        ins_subject("English 2","second","1");
        ins_subject("Tecnical Writing","second","1");
        //***************************** Second Year *******************************************
        ins_subject("Logic Dwsign","first","2");
        ins_subject("Report Writing","first","2");
        ins_subject("Math 3","first","2");
        ins_subject("Data Structres","first","2");
        ins_subject("Probability","first","2");
        ins_subject("Introduction To IS","first","2");
        //********************
        ins_subject("File Organization","second","2");
        ins_subject("Ethics","second","2");
        ins_subject("Math 4","second","2");
        ins_subject("Statistical Analysis","second","2");
        ins_subject("Opretions Research","second","2");
        ins_subject("Object Oriented Programing","second","2");
        //***************************** Third Year *******************************************
        ins_subject("Algorithms","first","3");
        ins_subject("Assembly","first","3");
        ins_subject("Software","first","3");
        ins_subject("Numerical","first","3");
        ins_subject("Automate","first","3");
        ins_subject("Prolog","first","3");
        //*******************
        ins_subject("Operating System","second","3");
        ins_subject("Architecure","second","3");
        ins_subject("Artificial Intelligence","second","3");
        ins_subject("Graphics","second","3");
        ins_subject("System analysis","second","3");
        ins_subject("Datab ase","second","3");
        //***************************** Forth Year *******************************************
        ins_subject("Applied Math","first","4");
        ins_subject("Digital Image","first","4");
        ins_subject("Network","first","4");
        ins_subject("Computer Engineering","first","4");
        ins_subject("Computer Programming","first","4");
        ins_subject("Software Development","first","4");
        //*******************
        ins_subject("Software Systems","second","4");
        ins_subject("Data Managment","second","4");
        ins_subject("Desgin Databases","second","4");
        ins_subject("Parallel Programing","second","4");
        ins_subject("Web Development","second","4");
        ins_subject("Memory Systems","second","4");
        //************************************************************************

    }
    public void data_video(){
        //****************************** First Year *****************************************
        ins_video("Electronics","https://www.youtube.com/watch?v=gp8mBFXgip0","Electronics");
        ins_video("English","https://www.youtube.com/watch?v=-DP1i2ZU9gk","English");
        ins_video("Managment","https://www.youtube.com/watch?v=rL8X2mlNHPM","Managment");
        ins_video("Math 1","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Math 1");
        ins_video("Introduction To Computer","https://www.youtube.com/watch?v=RBSGKlAvoiM","Introduction To Computer");
        ins_video("Physics 1","https://www.youtube.com/watch?v=KOKnWaLiL8w","Physics 1");
        //***********
        ins_video("Physics 2","https://www.youtube.com/watch?v=rL8X2mlNHPM","Physics 2");
        ins_video("Electronics 2","https://www.youtube.com/watch?v=gp8mBFXgip0","Electronics 2");
        ins_video("Structre Programing","https://www.youtube.com/watch?v=RBSGKlAvoiM","Structre Programing");
        ins_video("Human Rights","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Human Rights");
        ins_video("English 2","https://www.youtube.com/watch?v=KOKnWaLiL8w","English 2");
        ins_video("Tecnical Writing","https://www.youtube.com/watch?v=rL8X2mlNHPM","Tecnical Writing");
        //***************************** Second Year *******************************************
        ins_video("Logic Dwsign","https://www.youtube.com/watch?v=gp8mBFXgip0","Logic Dwsign");
        ins_video("Report Writing","https://www.youtube.com/watch?v=-DP1i2ZU9gk","Report Writing");
        ins_video("Math 3","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Math 3");
        ins_video("Data Structres","https://www.youtube.com/watch?v=rL8X2mlNHPM","Data Structres");
        ins_video("Probability","https://www.youtube.com/watch?v=KOKnWaLiL8w","Probability");
        ins_video("Introduction To IS","https://www.youtube.com/watch?v=RBSGKlAvoiM","Introduction To IS");
        //********************
        ins_video("File Organization","https://www.youtube.com/watch?v=rL8X2mlNHPM","File Organization");
        ins_video("Ethics","https://www.youtube.com/watch?v=RBSGKlAvoiM","Ethics");
        ins_video("Math 4","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Math 4");
        ins_video("Statistical Analysis","https://www.youtube.com/watch?v=KOKnWaLiL8w","Statistical Analysis");
        ins_video("Opretions Research","https://www.youtube.com/watch?v=gp8mBFXgip0","Opretions Research");
        ins_video("Object Oriented Programing","https://www.youtube.com/watch?v=-DP1i2ZU9gk","Object Oriented Programing");
        //***************************** Third Year *******************************************
        ins_video("Algorithms","https://www.youtube.com/watch?v=rL8X2mlNHPM","Algorithms");
        ins_video("Assembly","https://www.youtube.com/watch?v=gp8mBFXgip0","Assembly");
        ins_video("Software","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Software");
        ins_video("Numerical","https://www.youtube.com/watch?v=KOKnWaLiL8w","Numerical");
        ins_video("Automate","https://www.youtube.com/watch?v=RBSGKlAvoiM","Automate");
        ins_video("Prolog","https://www.youtube.com/watch?v=-DP1i2ZU9gk","Prolog");
        //*******************
        ins_video("Operating System","https://www.youtube.com/watch?v=RBSGKlAvoiM","Operating System");
        ins_video("Architecure","https://www.youtube.com/watch?v=KOKnWaLiL8w","Architecure");
        ins_video("Artificial Intelligence","https://www.youtube.com/watch?v=L3LMbpZIKhQ","3");
        ins_video("Graphics","https://www.youtube.com/watch?v=rL8X2mlNHPM","Artificial Intelligence");
        ins_video("System analysis","https://www.youtube.com/watch?v=gp8mBFXgip0","System analysis");
        ins_video("Datab ase","https://www.youtube.com/watch?v=-DP1i2ZU9gk","Datab ase");
        //***************************** Forth Year *******************************************
        ins_video("Applied Math","https://www.youtube.com/watch?v=rL8X2mlNHPM","Applied Math");
        ins_video("Digital Image","https://www.youtube.com/watch?v=gp8mBFXgip0","Digital Image");
        ins_video("Network","https://www.youtube.com/watch?v=RBSGKlAvoiM","Network");
        ins_video("Computer Engineering","https://www.youtube.com/watch?v=rL8X2mlNHPM","Computer Engineering");
        ins_video("Computer Programming","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Computer Programming");
        ins_video("Software Development","https://www.youtube.com/watch?v=KOKnWaLiL8w","Software Development");
        //*******************
        ins_video("Software Systems","https://www.youtube.com/watch?v=RBSGKlAvoiM","Software Systems");
        ins_video("Data Managment","https://www.youtube.com/watch?v=rL8X2mlNHPM","Data Managment");
        ins_video("Desgin Databases","https://www.youtube.com/watch?v=gp8mBFXgip0","Desgin Databases");
        ins_video("Parallel Programing","https://www.youtube.com/watch?v=-DP1i2ZU9gk","Parallel Programing");
        ins_video("Web Development","https://www.youtube.com/watch?v=KOKnWaLiL8w","Web Development");
        ins_video("Memory Systems","https://www.youtube.com/watch?v=L3LMbpZIKhQ","Memory Systems");
        //************************************************************************

    }
}
