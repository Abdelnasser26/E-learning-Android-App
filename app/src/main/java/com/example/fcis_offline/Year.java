package com.example.fcis_offline;
import java.util.ArrayList;

public class Year {
    private String id;
    public ArrayList<subject> subfirst;
    public ArrayList<subject> subsecond;
    public Year(String id) {
        this.id = id;
        this.subfirst = new ArrayList<subject>();
        this.subsecond = new ArrayList<subject>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}