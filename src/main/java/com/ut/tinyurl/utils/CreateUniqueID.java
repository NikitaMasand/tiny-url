package com.ut.tinyurl.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Component
public class CreateUniqueID {

    //get id from generate incrementing values
    //convert to base 62
    //use index to char table
    //return the resulting string
    private IndexToCharTable indexToCharTable;
    private HashMap<Integer, Character> indexToCharMap;
    public CreateUniqueID(){
        indexToCharTable = new IndexToCharTable();
        indexToCharMap = indexToCharTable.getIndexToCharTable();

    }
    public String createUniqueID(BigInteger id){
//        System.out.println("calling convert base 10 to 62");
        List<Integer> base62 = ConvertBase10ToBase62ID.convert(id);
        StringBuilder resCreate = new StringBuilder();

        for(int i=0;i<base62.size();i++){
            Character c = indexToCharMap.get(base62.get(i));
            resCreate.append(c);
        }
        return resCreate.toString();
    }
}
