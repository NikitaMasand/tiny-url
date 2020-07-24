package com.ut.tinyurl.utils;

import com.ut.tinyurl.model.Key;
import com.ut.tinyurl.resource.KeyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Component
public class GenerateOfflineKeys {


@Autowired
KeyController keyController;

@Autowired
GetKeyBase10FromUniqueID getKeyBase10FromUniqueID;

public void uniqueID() throws IOException {
    Resource resource = new ClassPathResource("static/last_id.txt");
    CreateUniqueID createUniqueID = new CreateUniqueID();
    InputStream inputStream = resource.getInputStream();
    String data = "";
    try {
        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
        data = new String(bdata, StandardCharsets.UTF_8);
        System.out.println("last read id: " + data);
    } catch (IOException e) {
        System.out.println("exception when reading id from last_id file " + e.getMessage());
    }
    BigInteger id = new BigInteger(data);
//    long id = Long.parseLong(data) + 1;
    for (int i = 0; i < 100; i++) {

        System.out.println("id: " + id);
//        System.out.println("calling create unique id");
        String stringid = createUniqueID.createUniqueID(id);
        System.out.println("unique id created.."+stringid);
//        String shorturlprefix = "www.ut.com/";
        BigInteger res = getKeyBase10FromUniqueID.getKeyFromStringID(stringid);
        System.out.println("get id again: "+res);
//        id++;
       id = id.add(BigInteger.ONE);
       keyController.saveKey(new Key(String.valueOf(id),stringid));
    }
//    System.out.println("writing id to text file: "+String.valueOf(id-1));
      System.out.println("writing id to text file: "+String.valueOf(id.subtract(BigInteger.ONE)));

    File file = resource.getFile();
    try{
        FileWriter fileWriter = new FileWriter(file,false);
//        fileWriter.write(String.valueOf(id-1));
        fileWriter.write(String.valueOf(id.subtract(BigInteger.ONE)));
        fileWriter.close();
        System.out.println("writing new id to last_id completed..");
    }
    catch (IOException e){
        System.out.println("exception when writing last id to file: "+e.getMessage());
    }
}
}
