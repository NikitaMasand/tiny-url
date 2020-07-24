package com.ut.tinyurl.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class IndexToCharTable {
    private static HashMap<Integer, Character> indexToCharTable;
    public IndexToCharTable(){
        initIndexToCharTable();
    }
    private void initIndexToCharTable(){
        //0->a, 1->b, ...26->A, 27->B, ...., 52->0,53->1....61->9
        indexToCharTable = new HashMap<>();
        char c = 'a';
        char C = 'A';
        char ic = '0';
        for(int i=0;i<26;i++){
            indexToCharTable.put(i,c);
            c++;
        }
        for(int i=26;i<52;i++){
            indexToCharTable.put(i,C);
            C++;
        }
        for(int i=53;i<62;i++){
            indexToCharTable.put(i,ic);
            ic++;
        }
    }

    public HashMap<Integer, Character> getIndexToCharTable() {
        return indexToCharTable;
    }
}
