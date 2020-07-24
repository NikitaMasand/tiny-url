package com.ut.tinyurl.resource;

import com.ut.tinyurl.model.Key;
import com.ut.tinyurl.repository.KeyRepository;
import com.ut.tinyurl.utils.AddKeysToRedis;
import com.ut.tinyurl.utils.GenerateOfflineKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class KeyController {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    GenerateOfflineKeys generateIncrementingValues;

    @Autowired
    AddKeysToRedis addKeysToRedis;

    @GetMapping("/")
    public String welcome(){
        return "welcome to tiny-url";
    }
//    @PostMapping("/addKey")
    public String saveKey(Key key){
        keyRepository.save(key);
        return "Added key with id: "+key.getId();
    }

//    @GetMapping(value="/findAllKeys")
    public List<Key> getKeys(){
        return keyRepository.findAll();
    }

//    @GetMapping("/find/{id}")
    public Optional<Key> getKey(String id){
        System.out.println("id"+id);
        return keyRepository.findById(id);
    }

//    @DeleteMapping("/delete/{id}")
    public String deleteKey(String id){
        System.out.println("deleting....");
        keyRepository.deleteById(id);
        return "Deleted key with id: "+id;
    }

    @GetMapping("/addDoc")
    public String addDoc() throws IOException {
        generateIncrementingValues.uniqueID();
        return "generated and 100 short keys to offline db";
    }

    @GetMapping("/addKeysToRedis")
    public String addKeysToRedis() throws IOException {
        addKeysToRedis.addKeysToRedis();
        return "added 10 keys from offline db to redis cache";
    }
}
