package com.ut.tinyurl.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Component
public class ConvertBase10ToBase62ID {
    public static List<Integer> convert(BigInteger id) {
        List<Integer> remainders = new LinkedList<>();
//        System.out.println("outside while");
        while (id.compareTo(BigInteger.ZERO)>0){
//            System.out.println("inside while");
            int remainder = id.mod(BigInteger.valueOf(62)).intValue();
//            System.out.println("remainder: "+remainder);
//            System.out.println("big integer id: "+id);
            remainders.add(0,remainder);
            id = id.divide(BigInteger.valueOf(62));
        }
        return remainders;
    }
}
