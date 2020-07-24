package com.ut.tinyurl.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Key")
public class Key {

    @Id
    private String id;
    private String shortURL;


    public Key(String id, String shortURL){
        this.id = id;
        this.shortURL = shortURL;
    }
    public String getShortURL(){
        return shortURL;
    }

    public String getId(){
        return id;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
