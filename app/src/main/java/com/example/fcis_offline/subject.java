package com.example.fcis_offline;


import java.io.Serializable;
import java.util.ArrayList;

public class subject implements Serializable {
    public String name,term,discribtion;
    video video;
    public subject(String name, String term, String discribtion) {
        this.name = name;
        this.term = term;
        this.discribtion = discribtion;
        //video=new video();
    }

}