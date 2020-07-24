package com.ut.tinyurl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;


//useful for getting id from shortened url
//eg: shortened url is AB
//AB will correspond to 26,27 using char to index table and this is our base 62 format
//26*62+27*1 id in base 10 format

@Component
public class ConvertBase62ToBase10ID {

    @Autowired
    CharToIndexTable charToIndexTable;

    public BigInteger convert(List<Character> base62ArrInURL){
        HashMap<Character, Integer> charToIndexMap = charToIndexTable.getCharToIndexTable();
        int exp = base62ArrInURL.size()-1;
//        BigInteger exp = new BigInteger(String.valueOf(base62ArrInURL.size()-1));
        BigInteger sixtytwo = new BigInteger(String.valueOf(62));
        BigInteger id = new BigInteger(String.valueOf(BigInteger.ZERO));

        for(int i=0;i<base62ArrInURL.size();i++){
            int mapval = charToIndexMap.get(base62ArrInURL.get(i));
            BigInteger mapvalbig = new BigInteger(String.valueOf(mapval));
            BigInteger powerBig = sixtytwo.pow(exp);
            id = id.add(mapvalbig.multiply(powerBig));
//            id += mapval*Math.pow(62,exp);
            exp--;
        }
        return id;
    }
}
