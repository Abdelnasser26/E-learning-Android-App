package com.example.fcis_offline;



public class student extends Pearson{
    Year y;
    String email;
    private String id_stud ;
    public student( String username, String password, String id,String email) {
        super(username, password);
        this.id_stud = id;
        this.email=email;
    }

    public String getId_stud() {
        return id_stud;
    }

    public void setId_stud(String id_stud) {
        this.id_stud = id_stud;
    }
}
