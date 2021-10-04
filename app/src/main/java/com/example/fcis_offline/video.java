package com.example.fcis_offline;

import java.io.Serializable;

public class video implements Serializable {

    String title,url;

    public video(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
