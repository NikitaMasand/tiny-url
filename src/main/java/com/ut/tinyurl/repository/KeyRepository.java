package com.ut.tinyurl.repository;

import com.ut.tinyurl.model.Key;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends MongoRepository<Key,String> {
}
