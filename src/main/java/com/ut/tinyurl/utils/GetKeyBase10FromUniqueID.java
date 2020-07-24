package com.ut.tinyurl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetKeyBase10FromUniqueID {

    @Autowired
    ConvertBase62ToBase10ID convertBase62ToBase10ID;

    public BigInteger getKeyFromStringID(String shorturl){
        List<Character> base62IDs = new ArrayList<>();
        for(int i=0;i<shorturl.length();i++){
            base62IDs.add(shorturl.charAt(i));
//            System.out.println(base62IDs.get(i));
        }
        BigInteger key = convertBase62ToBase10ID.convert(base62IDs);
        return key;
    }

}
