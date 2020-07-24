package com.ut.tinyurl.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CharToIndexTable {
    private static HashMap<Character, Integer> charToIndexTable;

    public CharToIndexTable(){
        initCharToIndexTable();
    }
    private void initCharToIndexTable(){
        charToIndexTable = new HashMap<>();
        //a->0, b->1, ...., A->26, B->27,....0->52, 1,->53
        char c = 'a';
        char C = 'A';
        char ic = '0';
        for(int i=0;i<26;i++){
            charToIndexTable.put(c,i);
            c++;
        }

        for(int i=26;i<52;i++){
            charToIndexTable.put(C,i);
            C++;
        }

        for(int i=52;i<62;i++){
            charToIndexTable.put(ic,i);
            ic++;
        }

    }

    public HashMap<Character,Integer> getCharToIndexTable(){
        return charToIndexTable;
    }
}
