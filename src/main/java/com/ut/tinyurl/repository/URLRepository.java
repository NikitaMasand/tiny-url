package com.ut.tinyurl.repository;

import com.ut.tinyurl.model.URL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends MongoRepository<URL,String> {

}
