package com.ut.tinyurl.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "URL")
public class URL {
    @Id
    private String shortURL;
    private String longURL;

    public URL(String shortURL, String longURL){
        this.shortURL=shortURL;
        this.longURL=longURL;
    }

    public String getShortURL(){
        return shortURL;
    }

    public String getLongURL(){
        return longURL;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
