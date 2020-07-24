package com.ut.tinyurl.utils;

import com.ut.tinyurl.model.Key;
import com.ut.tinyurl.resource.KeyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class AddKeysToRedis {
    @Autowired
    StringRedisTemplate redisTemplate;


    @Autowired
    KeyController keyController;

    String lastIdRedisgot;


    //everytime redis cache needs key, add 10 keys from tiny-url-offlinekeys db to redis
    //and delete them from the db.
    public void addKeysToRedis() throws IOException {
        System.out.println("add keys called");
        lastIdRedisgot = findLastIdRedisgot();
        System.out.println("last id: "+lastIdRedisgot);
        int counter = 10;
        BigInteger bigInteger = null;
        while (counter>0){
            Optional<Key> key = keyController.getKey(lastIdRedisgot);

            bigInteger = new BigInteger(lastIdRedisgot);
            bigInteger = bigInteger.add(BigInteger.ONE);
            lastIdRedisgot = String.valueOf(bigInteger);
            Key key1=null;
            if(key.isPresent()){
                System.out.println("key: "+key);
                System.out.println("is present");
                key1 = key.get();
            }
            if(key1!=null && !key1.getShortURL().contains("null") && key1.getShortURL().length()>0) {
                System.out.println("adding.."+key1.getShortURL());
                redisTemplate.opsForValue().set(lastIdRedisgot, key1.getShortURL());
                keyController.deleteKey(lastIdRedisgot);
                counter--;
            }

        }

        updateLastIdRedisgotFile(bigInteger);
    }

    public String findLastIdRedisgot() throws IOException {
        Resource resource = new ClassPathResource("static/last_id_redis_got.txt");
        InputStream inputStream = resource.getInputStream();
        String data = "";
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
            System.out.println("last read id: " + data);
        } catch (IOException e) {
            System.out.println("exception when reading id from last_id_redis_got file " + e.getMessage());
        }
        return data;
    }

    public void updateLastIdRedisgotFile(BigInteger id) throws IOException {
        Resource resource = new ClassPathResource("static/last_id_redis_got.txt");
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
